import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';

interface NavItem {
  label: string;
  path: string;
  exact?: boolean;
}

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent {
  menuOpen = false;

  navItems: NavItem[] = [
    {
      label: 'Equipos',
      path: '/teams'
    },
    {
      label: 'Partidos',
      path: '/matches'
    },
    {
      label: 'Asistencia',
      path: '/asistencia'
    },
    {
      label: 'Estadísticas',
      path: '/estadisticas'
    },
    {
      label: 'Alineaciones',
      path: '/alineaciones'
    },
  ];

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }
}
