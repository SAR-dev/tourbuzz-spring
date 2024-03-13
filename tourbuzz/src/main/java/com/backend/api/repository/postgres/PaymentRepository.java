package com.backend.api.repository.postgres;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentRepository {
    String getInvoiceNo(String email, String paymentType);

    int registerPaymentIdWithInvoice(String invoiceNo, String paymentId);
}
