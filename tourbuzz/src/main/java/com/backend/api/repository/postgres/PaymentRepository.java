package com.backend.api.repository.postgres;

import com.backend.api.response.PaymentRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentRepository {
    String getInvoiceNo(String email, String paymentType);

    int registerPaymentIdWithInvoice(String invoiceNo, String paymentId, double paymentAmount);

    int completePaymentWithPaymentId(PaymentRes paymentRes);
}
