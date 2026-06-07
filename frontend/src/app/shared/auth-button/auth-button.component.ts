import { Component, HostListener, ElementRef, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import {AuthService} from "../../core/services/auth.service";

@Component({
  selector: 'app-auth-button',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './auth-button.component.html',
})
export class AuthButtonComponent {
  authService  = inject(AuthService);
  dropdownOpen = false;

  constructor(private elRef: ElementRef) {}

  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

  @HostListener('document:click', ['$event'])
  onClickOutside(event: MouseEvent) {
    if (!this.elRef.nativeElement.contains(event.target)) {
      this.dropdownOpen = false;
    }
  }

  logout() {
    this.authService.logout();
    this.dropdownOpen = false;
  }
}
