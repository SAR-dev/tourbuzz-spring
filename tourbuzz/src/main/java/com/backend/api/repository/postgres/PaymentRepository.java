package com.backend.api.repository.postgres;

import com.backend.api.request.InvoiceCreateReq;
import com.backend.api.response.PaymentRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentRepository {
    String getInvoiceNo(String email, String paymentType);

    int registerPaymentIdWithInvoice(String invoiceNo, String paymentId, double paymentAmount);

    int updatePaymentWithPaymentId(PaymentRes paymentRes);

    String getPaymentIdByInvoiceNo(String invoiceNo);

    String getUserEmailByInvoiceNo(String invoiceNo);

    int createTicket(String email, @Param("data") InvoiceCreateReq invoiceCreateReq);

    void createTicketPaymentWithInvoice(int ticketsId, String invoiceNo);

    double getPaymentAmountByInvoiceNo(String invoiceNo);
}
