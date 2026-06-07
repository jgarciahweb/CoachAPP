import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ToastService} from "./toast.service";

@Component({
  selector: 'app-toast',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './toast.component.html',
})
export class ToastComponent {
  toastService = inject(ToastService);
}
