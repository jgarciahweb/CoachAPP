import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

interface JwtPayload {
  name?: string;
  avatar?: string;
  exp?: number;
}

@Component({
  selector: 'app-auth-button',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './auth-button.component.html',
  styleUrl: './auth-button.component.css',
})
export class AuthButtonComponent {
  user: JwtPayload | null = null;

  ngOnInit() {
    this.loadUser();
  }

  private loadUser() {
    const token = localStorage.getItem('jwt');
    if (!token) return;

    try {
      const payload = token.split('.')[1];
      const decoded: JwtPayload = JSON.parse(atob(payload));

      // Comprobar expiración
      if (decoded.exp && decoded.exp * 1000 < Date.now()) {
        localStorage.removeItem('jwt');
        return;
      }

      this.user = decoded;
    } catch {
      localStorage.removeItem('jwt');
    }
  }

  logout() {
    localStorage.removeItem('jwt');
    this.user = null;
  }
}
