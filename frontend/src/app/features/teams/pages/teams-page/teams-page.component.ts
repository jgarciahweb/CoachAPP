import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { Team } from '../../models/team';
import { TeamsService } from '../../services/teams.service';

@Component({
  selector: 'app-teams-page',
  imports: [FormsModule],
  standalone: true,
  templateUrl: './teams-page.component.html',
})
export class TeamsPage implements OnInit {
  private service = inject(TeamsService);

  teams: Team[] = [];

  ngOnInit(): void {
    this.loadTeams();
  }

  searchQuery = '';

  get filteredTeams() {
    if (!this.searchQuery.trim()) return this.teams;
    return this.teams.filter((t) =>
      t.name.toLowerCase().includes(this.searchQuery.toLowerCase())
    );
  }

  loadTeams(): void {
    this.service.getTeams().subscribe((teams) => {
      this.teams = teams;
    });
  }
}
