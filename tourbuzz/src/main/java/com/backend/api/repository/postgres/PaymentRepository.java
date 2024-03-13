package com.backend.api.repository.postgres;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentRepository {
    String getReferenceId(String email, String paymentType);
}
