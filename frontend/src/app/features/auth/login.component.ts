import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import {AuthService} from "../../core/services/auth.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterLink],
  templateUrl: './login.component.html',
})
export class LoginComponent {
  loginForm: FormGroup;
  isLoading = false;
  showPassword = false;
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService
  ) {
    this.loginForm = this.fb.group({
      email:    ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  isFieldInvalid(field: string): boolean {
    const control = this.loginForm.get(field);
    return !!(control?.invalid && control?.touched);
  }

  getFieldError(field: string): string {
    const control = this.loginForm.get(field);
    if (control?.errors?.['required'])  return 'Este campo es obligatorio';
    if (control?.errors?.['email'])     return 'El email no es válido';
    if (control?.errors?.['minlength']) return 'Mínimo 6 caracteres';
    return '';
  }

  onSubmit(): void {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';

    this.authService.login(this.loginForm.value).subscribe({
      next: () => this.router.navigate(['/teams']),
      error: (err) => {
        this.errorMessage = err.error?.message ?? 'Credenciales incorrectas';
        this.isLoading = false;
      },
      complete: () => (this.isLoading = false),
    });
  }
}
