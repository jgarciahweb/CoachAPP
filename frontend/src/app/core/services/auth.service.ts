import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import {environment} from "../../../enviroment";



export interface LoginRequest {
  email: string;
  password: string;
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
  role: string;
}

@Injectable({ providedIn: 'root' })
export class AuthService {

  private readonly JWT_KEY = 'jwt';
  private readonly apiUrl = environment.apiUrl;

  constructor(private http: HttpClient, private router: Router) {}

  // ─── Login ───────────────────────────────────────────────
  login(credentials: LoginRequest): Observable<LoginResponse> {
    return this.http
      .post<LoginResponse>(`${this.apiUrl}/auth/login`, credentials)
      .pipe(
        tap(response => {
          localStorage.setItem(this.JWT_KEY, response.token);
        })
      );
  }

  // ─── Logout ──────────────────────────────────────────────
  logout(): void {
    localStorage.removeItem(this.JWT_KEY);
    this.router.navigate(['/login']);
  }

  // ─── Token ───────────────────────────────────────────────
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

  // ─── Usuario actual ───────────────────────────────────────
  getCurrentUser(): AuthUser | null {
    const token = this.getToken();
    if (!token || this.isTokenExpired(token)) return null;

    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return {
        email:     payload.email,
        firstName: payload.firstName,
        role:      payload.role,
      };
    } catch {
      return null;
    }
  }
}
