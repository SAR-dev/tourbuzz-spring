package com.backend.api.entity;

import lombok.Data;

@Data
public class DistrictEntity {
    private int id;
    private String name;
    private int divisions_id;

    private DivisionEntity division;
}
