package com.coachapp.backend.application.command.team;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddCategoryCommand {
    String teamId;
    String name;
}
