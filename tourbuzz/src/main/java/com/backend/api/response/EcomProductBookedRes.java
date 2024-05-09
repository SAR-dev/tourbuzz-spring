package com.backend.api.response;

import com.backend.api.entity.ProductEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EcomProductBookedRes {
    private ProductEntity product;
    private int productSold;
}
