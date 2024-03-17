package com.backend.api.repository.postgres;

import com.backend.api.request.BookCreateReq;
import com.backend.api.request.PaymentCreateReq;
import com.backend.api.response.PaymentRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentRepository {
    String getInvoiceNo(String email);

    int registerPaymentIdWithInvoice(String invoiceNo, String paymentId, double paymentAmount);

    int updatePaymentWithPaymentId(PaymentRes paymentRes);

    String getPaymentIdByInvoiceNo(String invoiceNo);

    String getUserEmailByInvoiceNo(String invoiceNo);

    double getPaymentAmountByInvoiceNo(String invoiceNo);

    int updatePaymentTypeByInvoiceNo(String paymentType, String invoiceNo);
}
