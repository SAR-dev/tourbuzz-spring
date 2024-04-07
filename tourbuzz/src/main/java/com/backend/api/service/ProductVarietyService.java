package com.backend.api.service;

import com.backend.api.repository.postgres.ProductVarietyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductVarietyService {
    private final ProductVarietyRepository productVarietyRepository;
}
