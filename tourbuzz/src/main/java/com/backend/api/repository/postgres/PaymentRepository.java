package com.backend.api.repository.postgres;

import com.backend.api.response.PaymentRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentRepository {
    String getInvoiceNo(String email, String paymentType);

    int registerPaymentIdWithInvoice(String invoiceNo, String paymentId, double paymentAmount);

    int updatePaymentWithPaymentId(PaymentRes paymentRes);

    String getPaymentIdByInvoiceNo(String invoiceNo);

    String getUserEmailByInvoiceNo(String invoiceNo);
}
