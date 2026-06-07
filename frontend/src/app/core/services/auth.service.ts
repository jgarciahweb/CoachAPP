import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import {environment} from "../../../enviroment";

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  firstName: string;
  lastName:  string;
  email:     string;
  password:  string;
}

export interface LoginResponse {
  token: string;
  role: string;
  email: string;
  firstName: string;
}

export interface AuthUser {
  email: string;
  firstName: string;
  lastName: string;
  role: string;
  avatarUrl?: string;
}

export interface UpdateProfileResponse {
  user: {
    id: string;
    email: string;
    firstName: string;
    lastName: string;
    role: string;
  };
  token: string;
}

@Injectable({ providedIn: 'root' })
export class AuthService {

  private readonly JWT_KEY = 'jwt';
  private readonly apiUrl = environment.apiUrl;

  // Signal reactivo del usuario actual
  currentUser = signal<AuthUser | null>(this.getUserFromToken());

  constructor(private http: HttpClient, private router: Router) {}

  login(credentials: LoginRequest): Observable<LoginResponse> {
    return this.http
      .post<LoginResponse>(`${this.apiUrl}/auth/login`, credentials)
      .pipe(
        tap(response => {
          localStorage.setItem(this.JWT_KEY, response.token);
          this.currentUser.set(this.getUserFromToken());
        })
      );
  }

  register(data: RegisterRequest): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/auth/register`, data);
  }

  logout(): void {
    localStorage.removeItem(this.JWT_KEY);
    this.currentUser.set(null);
    this.router.navigate(['/login']);
  }

  updateProfile(data: { firstName: string; lastName: string; email: string }): Observable<UpdateProfileResponse> {
    return this.http.put<UpdateProfileResponse>(`${this.apiUrl}/users/profile`, data, {
      headers: { Authorization: `Bearer ${this.getToken()}` }
    }).pipe(
      tap(response => {
        localStorage.setItem(this.JWT_KEY, response.token);
        this.currentUser.set(this.getUserFromToken());
      })
    );
  }

  getToken(): string | null {
    return localStorage.getItem(this.JWT_KEY);
  }

  isLoggedIn(): boolean {
    const token = this.getToken();
    if (!token) return false;
    return !this.isTokenExpired(token);
  }

  private isTokenExpired(token: string): boolean {
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.exp * 1000 < Date.now();
    } catch {
      return true;
    }
  }

  private getUserFromToken(): AuthUser | null {
    const token = this.getToken();
    if (!token || this.isTokenExpired(token)) return null;
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return {
        email:     payload.email,
        firstName: payload.firstName,
        lastName:  payload.lastName,
        role:      payload.role,
        avatarUrl: `${environment.minioUrl}/${payload.avatarUrl}` ?? null,
      };
    } catch {
      return null;
    }
  }

  // Mantener por compatibilidad
  getCurrentUser(): AuthUser | null {
    return this.currentUser();
  }
}
