import { Routes } from '@angular/router';
import { TeamsPage } from './features/teams/pages/teams-page/teams-page.component';
import { LoginComponent } from './features/auth/login.component';

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'teams',
    pathMatch: 'full'
  },
  { path: 'login', component: LoginComponent },
  {
    path: 'teams',
    component: TeamsPage
  }
];
