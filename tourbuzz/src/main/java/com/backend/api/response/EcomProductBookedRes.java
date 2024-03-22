package com.backend.api.response;

import com.backend.api.entity.PackageEntity;
import com.backend.api.entity.ProductEntity;
import com.backend.api.entity.TourShortEntity;
import lombok.Data;

@Data
public class EcomProductBookedRes {
    private ProductEntity product;
    private int product_sold;
}
