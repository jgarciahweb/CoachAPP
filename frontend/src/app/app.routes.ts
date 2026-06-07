import { Routes } from '@angular/router';
import {authGuard} from "./core/guards/auth.guards";

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () =>
      import('./features/auth/login.component').then(m => m.LoginComponent),
  },
  {
    path: 'register',
    loadComponent: () =>
      import('./features/auth/register.component').then(m => m.RegisterComponent),
  },
  {
    path: 'profile',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./features/profile/profile.component').then(m => m.ProfileComponent),
  },
  {
    path: 'teams',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./features/teams/pages/teams-page/teams-page.component').then(m => m.TeamsPage),
  },
  { path: '',   redirectTo: 'teams', pathMatch: 'full' },
  { path: '**', redirectTo: 'teams' },
];
