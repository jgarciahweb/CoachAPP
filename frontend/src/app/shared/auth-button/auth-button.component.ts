import { Component, OnInit, HostListener, ElementRef } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService, AuthUser } from '../../core/services/auth.service';

@Component({
  selector: 'app-auth-button',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './auth-button.component.html',
})
export class AuthButtonComponent implements OnInit {
  user: AuthUser | null = null;
  dropdownOpen = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private elRef: ElementRef
  ) {}

  ngOnInit() {
    this.user = this.authService.getCurrentUser();
  }

  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

  // Cierra el dropdown al hacer click fuera
  @HostListener('document:click', ['$event'])
  onClickOutside(event: MouseEvent) {
    if (!this.elRef.nativeElement.contains(event.target)) {
      this.dropdownOpen = false;
    }
  }

  logout() {
    this.authService.logout();
    this.user = null;
    this.dropdownOpen = false;
  }
}
