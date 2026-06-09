import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, AbstractControl, ValidationErrors } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../core/services/auth.service';
import {ToastService} from "../../shared/toast/toast.service";

type ActiveSection = 'editar' | 'password' | 'rol' | 'avatar';

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
  private fb           = inject(FormBuilder);
  private authService  = inject(AuthService);
  private toastService = inject(ToastService);

  activeSection: ActiveSection = 'editar';
  user = this.authService.currentUser();
  isEditLoading   = false;
  isAvatarLoading = false;

  // Preview de la imagen seleccionada
  avatarPreview: string | null = null;
  selectedFile: File | null = null;

  editForm: FormGroup = this.fb.group({
    firstName: [this.user?.firstName ?? '', [Validators.required, Validators.minLength(2)]],
    lastName:  [this.user?.lastName  ?? '', [Validators.required, Validators.minLength(2)]],
    email:     [this.user?.email     ?? '', [Validators.required, Validators.email]],
  });

  passwordForm: FormGroup = this.fb.group(
    {
      currentPassword: ['', [Validators.required, Validators.minLength(6)]],
      newPassword:     ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
    },
    { validators: passwordMatchValidator }
  );

  rolForm: FormGroup = this.fb.group({
    subject: ['', [Validators.required, Validators.minLength(5)]],
    message: ['', [Validators.required, Validators.minLength(20)]],
  });

  isInvalid(form: FormGroup, field: string): boolean {
    const control = form.get(field);
    return !!(control?.invalid && control?.touched);
  }

  getError(form: FormGroup, field: string): string {
    const control = form.get(field);
    if (control?.errors?.['required'])  return 'Este campo es obligatorio';
    if (control?.errors?.['email'])     return 'El email no es válido';
    if (control?.errors?.['minlength']) return `Mínimo ${control.errors['minlength'].requiredLength} caracteres`;
    if (field === 'confirmPassword' && this.passwordForm.errors?.['passwordMismatch']) {
      return 'Las contraseñas no coinciden';
    }
    return '';
  }

  isConfirmInvalid(): boolean {
    const control = this.passwordForm.get('confirmPassword');
    return !!(control?.touched && (control?.errors || this.passwordForm.errors?.['passwordMismatch']));
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    const file  = input.files?.[0];
    if (!file) return;

    // Validar tipo y tamaño (max 2MB)
    if (!file.type.startsWith('image/')) {
      this.toastService.error('Solo se permiten imágenes');
      return;
    }
    if (file.size > 2 * 1024 * 1024) {
      this.toastService.error('La imagen no puede superar 2MB');
      return;
    }

    this.selectedFile = file;

    // Preview
    const reader = new FileReader();
    reader.onload = (e) => this.avatarPreview = e.target?.result as string;
    reader.readAsDataURL(file);
  }

  onAvatarSubmit(): void {
    if (!this.selectedFile) {
      this.toastService.error('Selecciona una imagen primero');
      return;
    }

    this.isAvatarLoading = true;

    const formData = new FormData();
    formData.append('file', this.selectedFile);

    this.authService.updateAvatar(formData).subscribe({
      next: () => {
        this.user = this.authService.currentUser();
        this.avatarPreview = null;
        this.selectedFile  = null;
        this.toastService.success('Avatar actualizado correctamente');
        this.isAvatarLoading = false;
      },
      error: (err) => {
        this.toastService.error(err.error?.message ?? 'Error al actualizar el avatar');
        this.isAvatarLoading = false;
      },
    });
  }

  removePreview(): void {
    this.avatarPreview = null;
    this.selectedFile  = null;
  }

  onEditSubmit(): void {
    if (this.editForm.invalid) {
      this.editForm.markAllAsTouched();
      return;
    }
    this.isEditLoading = true;
    this.authService.updateProfile(this.editForm.value).subscribe({
      next: () => {
        this.user = this.authService.currentUser();
        this.toastService.success('Datos actualizados correctamente');
        this.isEditLoading = false;
      },
      error: (err) => {
        this.toastService.error(err.error?.message ?? 'Error al actualizar los datos');
        this.isEditLoading = false;
      },
    });
  }

  onPasswordSubmit(): void {
    if (this.passwordForm.invalid) { this.passwordForm.markAllAsTouched(); return; }
    // TODO
  }

  onRolSubmit(): void {
    if (this.rolForm.invalid) { this.rolForm.markAllAsTouched(); return; }
    // TODO
  }

  get initials(): string {
    return (this.user?.firstName?.charAt(0) ?? '').toUpperCase();
  }
}
