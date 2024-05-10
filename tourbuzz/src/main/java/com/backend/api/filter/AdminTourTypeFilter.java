package com.backend.api.filter;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AdminTourTypeFilter {
    private String name = "";
    private int pageNo = 1;
    private int pageSize = 20;
}
