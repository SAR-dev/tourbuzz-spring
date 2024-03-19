package com.backend.api.controller.admin;

import com.backend.api.entity.ArticleEntity;
import com.backend.api.entity.TicketEntity;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.AuthService;
import com.backend.api.service.PaginationService;
import com.backend.api.service.TicketService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public PaginatedRes getTickets(
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(pageNo, pageSize);
        List<TicketEntity> ticketEntities = ticketService.getTickets();
        Page<TicketEntity> pageInfo = (Page<TicketEntity>) ticketEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }
}
