package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.PaymentEntity;
import com.backend.api.entity.TicketEntity;
import com.backend.api.entity.TicketPaymentEntity;
import com.backend.api.repository.postgres.TicketRepository;
import com.backend.api.request.BookCancelReq;
import com.backend.api.request.BookCreateReq;
import com.backend.api.request.admin.FileCreateReq;
import com.backend.api.response.TourPackageBookedRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final PaymentService paymentService;
    private final PackageService packageService;
    private final AuthService authService;
    private final FileService fileService;

    public List<TicketEntity> getTickets(){
        return ticketRepository.getTickets();
    }

    @Transactional
    public void bookInvoiceNo(BookCreateReq bookCreateReq) throws Exception {
        String email = authService.getLoggedInEmail();
        String invoiceNo = paymentService.getInvoiceNo(email);
        validateTicketCount(bookCreateReq.getPackagesId(), bookCreateReq.getTicketAmount());
        int ticketsId = createTicket(email, bookCreateReq);
        paymentService.createTicketPaymentByInvoiceNo(ticketsId, invoiceNo);
    }

    public void validateTicketCount(int packagesId, int ticketAmount) throws Exception {
        TourPackageBookedRes packageEntity = packageService.getPackageById(packagesId);
        int sold = packageEntity.getTicketSold();
        int max = packageEntity.getTourPackage().getThreshold();
        if(sold >= max){
            throw new Exception(ErrorMessages.TICKET_SOLD_OUT);
        }
        if(ticketAmount > (max-sold)){
            throw new Exception(ErrorMessages.MAX_TICKET_EXCEEDS);
        }
    }

    @Transactional
    public void cancelInvoiceNo(BookCancelReq bookCancelReq) throws Exception {
        String email = authService.getLoggedInEmail();
        String invoicedEmail = paymentService.getUserEmailByInvoiceNo(bookCancelReq.getInvoiceNo());
        if(!invoicedEmail.equals(email)) {
            throw new Exception(ErrorMessages.UNAUTHORIZED);
        }
        PaymentEntity payment = paymentService.getPaymentByInvoiceNo(bookCancelReq.getInvoiceNo());
        if(payment.isCompleted()) {
            throw new Exception(ErrorMessages.PAYMENT_COMPLETED);
        }
        TicketPaymentEntity ticketPayment = paymentService.getTicketPaymentByInvoiceNo(bookCancelReq.getInvoiceNo());
        deleteTicketPaymentByInvoiceNo(bookCancelReq.getInvoiceNo());
        deleteTicketByInvoiceNo(ticketPayment.getTicketsId());
        paymentService.deletePayment(ticketPayment.getPaymentsId());
    }

    public List<TicketEntity> getTicketsByUser(){
        String email = authService.getLoggedInEmail();
        return ticketRepository.getTicketsByUser(email);
    }

    public int createTicket(String email, BookCreateReq bookCreateReq){
        return ticketRepository.createTicket(email, bookCreateReq);
    }

    public void updateTicketAmountByInvoiceNo(int ticketAmount, String invoiceNo) throws Exception {
        int count = ticketRepository.updateTicketAmountByInvoiceNo(ticketAmount, invoiceNo);
        if(count != 1){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }

    public void deleteTicketByInvoiceNo(int id) {
        ticketRepository.deleteTicket(id);
    }

    public void deleteTicketPaymentByInvoiceNo(String invoiceNo) {
        ticketRepository.deleteTicketPaymentByInvoiceNo(invoiceNo);
    }

    public TicketEntity getTicketByInvoiceNo(String invoiceNo) throws Exception {
        String email = authService.getLoggedInEmail();
        String invoicedEmail = paymentService.getUserEmailByInvoiceNo(invoiceNo);
        if(!invoicedEmail.equals(email)) {
            throw new Exception(ErrorMessages.UNAUTHORIZED);
        }
        TicketEntity ticketEntity = ticketRepository.getTicketByInvoiceNo(invoiceNo);
        if(Objects.isNull(ticketEntity)) {
            throw new Exception(ErrorMessages.NOT_FOUND);
        }
        return ticketEntity;
    }

    @Transactional
    public void createTicketFile(int ticketsId, FileCreateReq fileCreateReq) throws Exception {
        TicketEntity ticket = getTicketById(ticketsId);
        if(!ticket.getPayment().isCompleted()) {
            throw new Exception(ErrorMessages.PAYMENT_NOT_COMPLETED);
        }
        int filesId = fileService.createFile(fileCreateReq);
        ticketRepository.createTicketFile(ticketsId, filesId);
    }

    public TicketEntity getTicketById(int id){
        return ticketRepository.getTicketById(id);
    }
}
