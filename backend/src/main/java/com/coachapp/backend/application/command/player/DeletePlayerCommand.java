package com.coachapp.backend.application.command.player;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DeletePlayerCommand {

    String playerId;

}
