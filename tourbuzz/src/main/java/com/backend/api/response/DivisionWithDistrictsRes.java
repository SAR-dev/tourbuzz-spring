package com.backend.api.response;

import com.backend.api.entity.DistrictEntity;
import com.backend.api.entity.DivisionEntity;
import lombok.Data;

import java.util.List;

@Data
public class DivisionWithDistrictsRes {
    private DivisionEntity division;
    private List<DistrictEntity> districts;
}
