package com.backend.api.controller.admin;

import com.backend.api.entity.ArticleEntity;
import com.backend.api.entity.TicketEntity;
import com.backend.api.filter.AdminTicketFilter;
import com.backend.api.request.admin.FileCreateReq;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.AuthService;
import com.backend.api.service.PaginationService;
import com.backend.api.service.TicketService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/tickets")
public class AdminTicketController {
    private final TicketService ticketService;
    private final AuthService authService;
    private final PaginationService paginationService;

    @Tag(name = "Get Ticket List")
    @GetMapping("")
    public PaginatedRes getTickets(@Valid AdminTicketFilter filter) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(filter.getPageNo(), filter.getPageSize());
        List<TicketEntity> ticketEntities = ticketService.getTickets();
        Page<TicketEntity> pageInfo = (Page<TicketEntity>) ticketEntities;
        return paginationService.getPaginatedData(pageInfo, filter.getPageNo(), filter.getPageSize());
    }

    @Tag(name = "Add Ticket File")
    @PostMapping("/files/{id}")
    public void addTicketFile(
            @RequestParam FileCreateReq fileCreateReq,
            @PathVariable int id
    ) throws Exception {
        authService.verifyAdminToken();
        ticketService.createTicketFile(id, fileCreateReq);
    }
}
