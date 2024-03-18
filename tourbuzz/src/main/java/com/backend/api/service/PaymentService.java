package com.backend.api.service;

import com.backend.api.ErrorMessages;
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

    public boolean checkIfInvoiceIsCompleted(String invoiceNo){
        return paymentRepository.checkIfInvoiceIsCompleted(invoiceNo);
    }

    public String getPaymentEmailByInvoiceNo(String invoiceNo){
        return paymentRepository.getPaymentEmailByInvoiceNo(invoiceNo);
    }

    public void registerPaymentIdWithInvoice(String invoiceNo, String paymentId, double paymentAmount) throws Exception {
        boolean updated = paymentRepository.registerPaymentIdWithInvoice(invoiceNo, paymentId, paymentAmount) == 1;
        if(!updated){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }

    public void updatePaymentWithPaymentId(PaymentRes paymentRes) throws Exception {
        boolean updated = paymentRepository.updatePaymentWithPaymentId(paymentRes) == 1;
        if(!updated){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }

    public String getUserEmailByInvoiceNo(String invoiceNo){
        return paymentRepository.getUserEmailByInvoiceNo(invoiceNo);
    }



    public String getPaymentIdByInvoiceNo(String invoiceNo) throws Exception {
        String loggedInEmail = authService.getLoggedInEmail();
        String invoicedEmail = getUserEmailByInvoiceNo(invoiceNo);
        if(!loggedInEmail.equals(invoicedEmail)){
            throw new Exception(ErrorMessages.UNAUTHORIZED);
        }
        return paymentRepository.getPaymentIdByInvoiceNo(invoiceNo);
    }

    public double getPaymentAmountByInvoiceNo(String invoiceNo){
        return paymentRepository.getPaymentAmountByInvoiceNo(invoiceNo);
    }

    public void updatePaymentTypeByInvoiceNo(PaymentType paymentType, String invoiceNo) throws Exception {
        boolean updated = paymentRepository.updatePaymentTypeByInvoiceNo(paymentType.name(), invoiceNo) == 1;
        if(!updated){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }

    public void deletePayment(int id) throws Exception {
        boolean deleted = paymentRepository.deletePayment(id) == 1;
        if(!deleted) throw new Exception(ErrorMessages.DELETE_FAILED);
    }

    public String getInvoiceNo(String email){
        return paymentRepository.getInvoiceNo(email);
    }
}
