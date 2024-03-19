package com.backend.api.controller.user;

import com.backend.api.entity.TicketEntity;
import com.backend.api.request.BookCancelReq;
import com.backend.api.request.BookCreateReq;
import com.backend.api.service.PaymentService;
import com.backend.api.service.TicketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @Tag(name = "Book Invoice No")
    @PostMapping("/book")
    public void bookInvoiceNo(@RequestBody BookCreateReq bookCreateReq) throws Exception {
        ticketService.bookInvoiceNo(bookCreateReq);
    }

    @Tag(name = "Cancel Invoice No")
    @PostMapping("/cancel")
    public void cancelInvoiceNo(@RequestBody BookCancelReq bookCancelReq) throws Exception {
        ticketService.cancelInvoiceNo(bookCancelReq);
    }

    @Tag(name = "Get Ticket By Invoice No")
    @GetMapping("/{invoiceNo}")
    public TicketEntity getTicketByInvoiceNo(@PathVariable String invoiceNo) throws Exception {
        return ticketService.getTicketByInvoiceNo(invoiceNo);
    }
}
