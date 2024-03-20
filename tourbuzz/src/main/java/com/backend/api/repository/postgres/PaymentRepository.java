package com.backend.api.repository.postgres;

import com.backend.api.entity.PaymentEntity;
import com.backend.api.entity.TicketPaymentEntity;
import com.backend.api.response.PaymentRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentRepository {
    int registerPaymentIdWithInvoice(String invoiceNo, String paymentId, double paymentAmount);

    int updatePaymentWithPaymentId(PaymentRes paymentRes);

    double getPaymentAmountByInvoiceNo(String invoiceNo);

    int updatePaymentTypeByInvoiceNo(String paymentType, String invoiceNo);

    void createTicketPaymentByInvoiceNo(int ticketsId, String invoiceNo);

    TicketPaymentEntity getTicketPaymentByInvoiceNo(String invoiceNo);

    PaymentEntity getPaymentByInvoiceNo(String invoiceNo);

    PaymentEntity getCompletedPaymentByPaymentId(String paymentId, String paymentType);

    void deletePayment(int id);

    String getInvoiceNo(String email);
}
