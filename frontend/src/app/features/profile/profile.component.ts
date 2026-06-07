import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, AbstractControl, ValidationErrors } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../core/services/auth.service';

type ActiveSection = 'editar' | 'password' | 'rol';

function passwordMatchValidator(control: AbstractControl): ValidationErrors | null {
  const newPass    = control.get('newPassword')?.value;
  const confirmPass = control.get('confirmPassword')?.value;
  return newPass === confirmPass ? null : { passwordMismatch: true };
}

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './profile.component.html',
})
export class ProfileComponent {
  private fb          = inject(FormBuilder);
  private authService = inject(AuthService);

  activeSection: ActiveSection = 'editar';
  user = this.authService.currentUser();

  // ── Editar datos ─────────────────────────────────────────
  editForm: FormGroup = this.fb.group({
    firstName: [this.user?.firstName ?? '', [Validators.required, Validators.minLength(2)]],
    lastName:  [this.user?.lastName ?? '',  [Validators.required, Validators.minLength(2)]],
    email:     [this.user?.email ?? '', [Validators.required, Validators.email]],
  });

  // ── Cambiar contraseña ────────────────────────────────────
  passwordForm: FormGroup = this.fb.group(
    {
      currentPassword: ['', [Validators.required, Validators.minLength(6)]],
      newPassword:     ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
    },
    { validators: passwordMatchValidator }
  );

  // ── Solicitar cambio de rol ───────────────────────────────
  rolForm: FormGroup = this.fb.group({
    subject: ['', [Validators.required, Validators.minLength(5)]],
    message: ['', [Validators.required, Validators.minLength(20)]],
  });

  // ── Helpers ───────────────────────────────────────────────
  isInvalid(form: FormGroup, field: string): boolean {
    const control = form.get(field);
    return !!(control?.invalid && control?.touched);
  }

  getError(form: FormGroup, field: string): string {
    const control = form.get(field);
    if (control?.errors?.['required'])   return 'Este campo es obligatorio';
    if (control?.errors?.['email'])      return 'El email no es válido';
    if (control?.errors?.['minlength'])  return `Mínimo ${control.errors['minlength'].requiredLength} caracteres`;
    if (field === 'confirmPassword' && form.errors?.['passwordMismatch']) return 'Las contraseñas no coinciden';
    return '';
  }

  isConfirmInvalid(): boolean {
    const control = this.passwordForm.get('confirmPassword');
    return !!(control?.touched && (control?.errors || this.passwordForm.errors?.['passwordMismatch']));
  }

  onEditSubmit(): void {
    if (this.editForm.invalid) { this.editForm.markAllAsTouched(); return; }
    // TODO: llamar al servicio
    console.log('Editar datos:', this.editForm.value);
  }

  onPasswordSubmit(): void {
    if (this.passwordForm.invalid) { this.passwordForm.markAllAsTouched(); return; }
    // TODO: llamar al servicio
    console.log('Cambiar contraseña:', this.passwordForm.value);
  }

  onRolSubmit(): void {
    if (this.rolForm.invalid) { this.rolForm.markAllAsTouched(); return; }
    // TODO: llamar al servicio
    console.log('Solicitar rol:', this.rolForm.value);
  }

  get initials(): string {
    return (this.user?.firstName?.charAt(0) ?? '').toUpperCase();
  }
}
