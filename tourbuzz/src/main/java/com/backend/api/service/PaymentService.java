package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.PaymentEntity;
import com.backend.api.entity.TicketPaymentEntity;
import com.backend.api.enums.PaymentType;
import com.backend.api.repository.postgres.PaymentRepository;
import com.backend.api.response.PaymentRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AuthService authService;

    public void registerPaymentIdWithInvoice(String invoiceNo, String paymentId, double paymentAmount) throws Exception {
        int count = paymentRepository.registerPaymentIdWithInvoice(invoiceNo, paymentId, paymentAmount);
        if(count != 1){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }

    public void updatePaymentWithPaymentId(PaymentRes paymentRes) throws Exception {
        int count = paymentRepository.updatePaymentWithPaymentId(paymentRes);
        if(count != 1){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }

    public String getUserEmailByInvoiceNo(String invoiceNo){
        PaymentEntity payment = paymentRepository.getPaymentByInvoiceNo(invoiceNo);
        return payment.getUser().getEmail();
    }

    public String getPaymentIdByInvoiceNo(String invoiceNo) throws Exception {
        String loggedInEmail = authService.getLoggedInEmail();
        String invoicedEmail = getUserEmailByInvoiceNo(invoiceNo);
        if(!loggedInEmail.equals(invoicedEmail)){
            throw new Exception(ErrorMessages.UNAUTHORIZED);
        }
        PaymentEntity payment = paymentRepository.getPaymentByInvoiceNo(invoiceNo);
        return payment.getPayment_id();
    }

    public double getPaymentAmountByInvoiceNo(String invoiceNo){
        return paymentRepository.getPaymentAmountByInvoiceNo(invoiceNo);
    }

    public PaymentEntity getCompletedPaymentByPaymentId(String paymentId, PaymentType paymentType){
        return paymentRepository.getCompletedPaymentByPaymentId(paymentId, paymentType.name());
    }

    public void updatePaymentTypeByInvoiceNo(PaymentType paymentType, String invoiceNo) throws Exception {
        int count = paymentRepository.updatePaymentTypeByInvoiceNo(paymentType.name(), invoiceNo);
        if(count != 1){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }

    public void deletePayment(int id) {
        paymentRepository.deletePayment(id);
    }

    public String getInvoiceNo(String email){
        return paymentRepository.getInvoiceNo(email);
    }

    public void createTicketPaymentByInvoiceNo(int ticketsId, String invoiceNo){
        paymentRepository.createTicketPaymentByInvoiceNo(ticketsId, invoiceNo);
    }

    public TicketPaymentEntity getTicketPaymentByInvoiceNo(String invoiceNo){
        return paymentRepository.getTicketPaymentByInvoiceNo(invoiceNo);
    }

    public PaymentEntity getPaymentByInvoiceNo(String invoiceNo){
        return paymentRepository.getPaymentByInvoiceNo(invoiceNo);
    }
}
