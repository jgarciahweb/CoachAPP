package com.coachapp.backend.application.command.player;

import com.coachapp.backend.domain.model.PositionEnum;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreatePlayerCommand {

    String categoryId;
    String firstName;
    String lastName;
    Integer dorsal;
    PositionEnum position;

}
