import {
  Component,
  OnInit,
  inject
} from '@angular/core';

import { Team } from '../../models/team';
import { TeamsService } from '../../services/teams.service';

@Component({
  selector: 'app-teams-page',
  standalone: true,
  templateUrl: './teams-page.component.html'
})
export class TeamsPage
  implements OnInit {

  private service =
    inject(TeamsService);

  teams: Team[] = [];

  ngOnInit(): void {

    this.loadTeams();
  }

  loadTeams(): void {

    this.service
      .getTeams()
      .subscribe(teams => {
        this.teams = teams;
      });
  }
}
