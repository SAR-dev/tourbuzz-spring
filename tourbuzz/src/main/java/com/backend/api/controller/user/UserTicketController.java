package com.backend.api.controller.user;

import com.backend.api.entity.TicketEntity;
import com.backend.api.service.TicketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/tickets")
public class UserTicketController {
    private final TicketService ticketService;

    @Tag(name = "Get Tickets")
    @GetMapping("")
    public List<TicketEntity> getTicketsByUser() {
        return ticketService.getTicketsByUser();
    }
}
