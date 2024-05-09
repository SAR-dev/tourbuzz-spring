package com.backend.api.response;

import com.backend.api.entity.DistrictEntity;
import com.backend.api.entity.DivisionEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DivisionWithDistrictsRes {
    private DivisionEntity division;
    private List<DistrictEntity> districts;
}
