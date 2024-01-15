package com.backend.api.response;

import com.backend.api.entity.DivisionEntity;
import lombok.Data;

@Data
public class DistrictWithDivisionRes {
    private int id;
    private String name;

    private DivisionEntity division;
}
