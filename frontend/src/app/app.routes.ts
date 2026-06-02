import { Routes } from '@angular/router';
import { TeamsPage } from './features/teams/pages/teams-page/teams-page.component';

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'teams',
    pathMatch: 'full'
  },

  {
    path: 'teams',
    component: TeamsPage
  }
];
