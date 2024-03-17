package com.backend.api.service;

import com.backend.api.entity.TicketEntity;
import com.backend.api.repository.postgres.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final AuthService authService;

    public List<TicketEntity> getTicketsByUser(){
        String email = authService.getLoggedInEmail();
        return ticketRepository.getTicketsByUser(email);
    }
}
