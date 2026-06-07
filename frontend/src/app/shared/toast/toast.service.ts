import { Injectable, signal } from '@angular/core';
import {Toast, ToastType} from "./toast.model";


@Injectable({ providedIn: 'root' })
export class ToastService {
  toast = signal<Toast | null>(null);
  private timer: any;

  show(message: string, type: ToastType, duration = 3000) {
    clearTimeout(this.timer);
    this.toast.set({ message, type });
    this.timer = setTimeout(() => this.toast.set(null), duration);
  }

  success(message: string) { this.show(message, 'success'); }
  error(message: string)   { this.show(message, 'error'); }
  dismiss()                { clearTimeout(this.timer); this.toast.set(null); }
}
