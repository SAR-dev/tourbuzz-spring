package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.enums.PaymentType;
import com.backend.api.repository.postgres.PaymentRepository;
import com.backend.api.request.InvoiceCreateReq;
import com.backend.api.response.PaymentRes;
import com.backend.api.response.TicketInvoiceRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AuthService authService;

    @Transactional
    public String getInvoiceNo(PaymentType paymentType, InvoiceCreateReq invoiceCreateReq) throws Exception {
        String email = authService.getLoggedInEmail();
        TicketInvoiceRes ticketInvoiceRes = getPendingTicketId(invoiceCreateReq.getPackagesId(), email);
        if(!Objects.isNull(ticketInvoiceRes)) {
            updateTicket(invoiceCreateReq.getTicketAmount(), ticketInvoiceRes.getTicket_id());
            return ticketInvoiceRes.getInvoice_no();
        } else {
            String invoiceNo = paymentRepository.getInvoiceNo(email, paymentType.name());
            int ticketsId = createTicket(email, invoiceCreateReq);
            createTicketPaymentWithInvoice(ticketsId, invoiceNo);
            return invoiceNo;
        }
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

    public int createTicket(String email, InvoiceCreateReq invoiceCreateReq){
        return paymentRepository.createTicket(email, invoiceCreateReq);
    }

    public String getPaymentIdByInvoiceNo(String invoiceNo) throws Exception {
        String loggedInEmail = authService.getLoggedInEmail();
        String invoicedEmail = getUserEmailByInvoiceNo(invoiceNo);
        if(!loggedInEmail.equals(invoicedEmail)){
            throw new Exception(ErrorMessages.UNAUTHORIZED);
        }
        return paymentRepository.getPaymentIdByInvoiceNo(invoiceNo);
    }

    public void createTicketPaymentWithInvoice(int ticketsId, String invoiceNo){
        paymentRepository.createTicketPaymentWithInvoice(ticketsId, invoiceNo);
    }

    public double getPaymentAmountByInvoiceNo(String invoiceNo){
        return paymentRepository.getPaymentAmountByInvoiceNo(invoiceNo);
    }

    public TicketInvoiceRes getPendingTicketId(int packagesId, String email){
        return paymentRepository.getPendingTicketId(packagesId, email);
    }

    public void updateTicket(int ticketAmount, int id) throws Exception {
        boolean updated = paymentRepository.updateTicket(ticketAmount, id) == 1;
        if(!updated) {
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }
}
