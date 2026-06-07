import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Team } from '../models/team';

@Injectable({
  providedIn: 'root'
})
export class TeamsService {

  private http = inject(HttpClient);

  private api = 'http://localhost:8080/api';

  private getAuthHeaders(): HttpHeaders {

    const token = localStorage.getItem('jwt');

    return new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  }

  getTeams(): Observable<Team[]> {

    return this.http.get<Team[]>(
      `${this.api}/teams`,
      {
        headers: this.getAuthHeaders()
      }
    );
  }

  createTeam(
    name: string
  ): Observable<Team> {

    return this.http.post<Team>(
      `${this.api}/teams`,
      { name },
      {
        headers: this.getAuthHeaders()
      }
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
      },
      {
        headers: this.getAuthHeaders()
      }
    );
  }
}
