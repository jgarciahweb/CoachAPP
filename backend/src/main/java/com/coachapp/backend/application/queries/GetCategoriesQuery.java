package com.coachapp.backend.application.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCategoriesQuery {
    private String teamId;
}
