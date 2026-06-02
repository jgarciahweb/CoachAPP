import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Team } from '../models/team';

@Injectable({
  providedIn: 'root'
})
export class TeamsService {

  private http = inject(HttpClient);

  private api =
    'http://localhost:8080/api';

  getTeams(): Observable<Team[]> {

    return this.http.get<Team[]>(
      `${this.api}/teams`
    );
  }

  createTeam(
    name: string
  ): Observable<Team> {

    return this.http.post<Team>(
      `${this.api}/teams`,
      { name }
    );
  }

  addCategory(
    teamId: string,
    name: string
  ): Observable<Team> {

    return this.http.post<Team>(
      `${this.api}/categories`,
      {
        teamId,
        name
      }
    );
  }
}
