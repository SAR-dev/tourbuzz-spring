package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.TicketEntity;
import com.backend.api.repository.postgres.TicketRepository;
import com.backend.api.request.BookCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final AuthService authService;
//    private final BkashService bkashService;

    public List<TicketEntity> getTicketsByUser(){
        String email = authService.getLoggedInEmail();
//        List<TicketEntity> tickets = ticketRepository.getTicketsByUser(email);
//        List<CompletableFuture<Void>> futures = tickets.stream()
//                .filter(ticket -> !ticket.getPayment().isCompleted())
//                .map(ticket -> CompletableFuture.runAsync(() -> {
//                    try {
//                        bkashService.queryPaymentByInvoiceNo(ticket.getPayment().getInvoice_no());
//                    } catch (Exception ignored) {
//                    }
//                }))
//                .toList();
//
//        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
//        allOf.join();
//        return tickets;
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
}
