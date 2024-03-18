package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.TicketEntity;
import com.backend.api.entity.TicketPaymentEntity;
import com.backend.api.repository.postgres.TicketRepository;
import com.backend.api.request.BookCancelReq;
import com.backend.api.request.BookCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final PaymentService paymentService;
    private final AuthService authService;

    @Transactional
    public void bookInvoiceNo(BookCreateReq bookCreateReq) {
        String email = authService.getLoggedInEmail();
        String invoiceNo = paymentService.getInvoiceNo(email);
        int ticketsId = createTicket(email, bookCreateReq);
        createTicketPaymentWithInvoice(ticketsId, invoiceNo);
    }

    @Transactional
    public void cancelInvoiceNo(BookCancelReq bookCancelReq) throws Exception {
        String email = authService.getLoggedInEmail();
        String invoicedEmail = paymentService.getPaymentEmailByInvoiceNo(bookCancelReq.getInvoiceNo());
        if(!invoicedEmail.equals(email)) throw new Exception(ErrorMessages.UNAUTHORIZED);
        boolean completed = paymentService.checkIfInvoiceIsCompleted(bookCancelReq.getInvoiceNo());
        if(completed) throw new Exception(ErrorMessages.PAYMENT_COMPLETED);
        TicketPaymentEntity ticketPayment = getTicketPaymentByInvoiceNo(bookCancelReq.getInvoiceNo());
        deleteTicketPaymentByInvoiceNo(bookCancelReq.getInvoiceNo());
        deleteTicketByInvoiceNo(ticketPayment.getTickets_id());
        paymentService.deletePayment(ticketPayment.getPayments_id());
    }

    public List<TicketEntity> getTicketsByUser(){
        String email = authService.getLoggedInEmail();
        return ticketRepository.getTicketsByUser(email);
    }

    public int createTicket(String email, BookCreateReq bookCreateReq){
        return ticketRepository.createTicket(email, bookCreateReq);
    }

    public void createTicketPaymentWithInvoice(int ticketsId, String invoiceNo){
        ticketRepository.createTicketPaymentWithInvoice(ticketsId, invoiceNo);
    }

    public void updateTicketAmountByInvoiceNo(int ticketAmount, String invoiceNo) throws Exception {
        boolean updated = ticketRepository.updateTicketAmountByInvoiceNo(ticketAmount, invoiceNo) == 1;
        if(!updated){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }

    public void deleteTicketByInvoiceNo(int id) throws Exception {
        boolean deleted = ticketRepository.deleteTicket(id) == 1;
        if(!deleted){
            throw new Exception(ErrorMessages.DELETE_FAILED);
        }
    }

    public TicketPaymentEntity getTicketPaymentByInvoiceNo(String invoiceNo){
        return ticketRepository.getTicketPaymentByInvoiceNo(invoiceNo);
    }

    public void deleteTicketPaymentByInvoiceNo(String invoiceNo) throws Exception {
        boolean deleted = ticketRepository.deleteTicketPaymentByInvoiceNo(invoiceNo) == 1;
        if(!deleted){
            throw new Exception(ErrorMessages.DELETE_FAILED);
        }
    }
}
