package com.backend.api.repository.postgres;

import com.backend.api.response.PaymentRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentRepository {
    boolean checkIfInvoiceIsCompleted(String invoiceNo);

    String getPaymentEmailByInvoiceNo(String invoiceNo);

    int deletePayment(int id);

    String getInvoiceNo(String email);

    int registerPaymentIdWithInvoice(String invoiceNo, String paymentId, double paymentAmount);

    int updatePaymentWithPaymentId(PaymentRes paymentRes);

    String getPaymentIdByInvoiceNo(String invoiceNo);

    String getUserEmailByInvoiceNo(String invoiceNo);

    double getPaymentAmountByInvoiceNo(String invoiceNo);

    int updatePaymentTypeByInvoiceNo(String paymentType, String invoiceNo);
}
