package com.coachapp.backend.application.command.team;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateCategoryCommand {
    String teamId;
    String categoryId;
    String name;
}
