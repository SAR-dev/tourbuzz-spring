package com.backend.api.response;

import com.backend.api.entity.DivisionEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DistrictWithDivisionRes {
    private int id;
    private String name;

    private DivisionEntity division;
}
