package com.backend.api.service;

import com.backend.api.entity.TicketEntity;
import com.backend.api.repository.postgres.TicketRepository;
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
    private final BkashService bkashService;

    public List<TicketEntity> getTicketsByUser(){
        String email = authService.getLoggedInEmail();
        List<TicketEntity> tickets = ticketRepository.getTicketsByUser(email);
        List<CompletableFuture<Void>> futures = tickets.stream()
                .filter(ticket -> !ticket.getPayment().isCompleted())
                .map(ticket -> CompletableFuture.runAsync(() -> {
                    try {
                        bkashService.queryPaymentByInvoiceNo(ticket.getPayment().getInvoice_no());
                    } catch (Exception ignored) {
                    }
                }))
                .toList();

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allOf.join();
        return tickets;
    }
}
