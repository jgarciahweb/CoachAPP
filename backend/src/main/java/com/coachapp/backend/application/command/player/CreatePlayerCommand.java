package com.coachapp.backend.application.command.player;

import com.coachapp.backend.domain.model.PositionEnum;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreatePlayerCommand {

    private String categoryId;
    private String firstName;
    private String lastName;
    private Integer dorsal;
    private PositionEnum position;

}
