package com.backend.api.repository.postgres;

import com.backend.api.entity.TicketEntity;
import com.backend.api.request.BookCreateReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TicketRepository {
    List<TicketEntity> getTicketsByUser(String email);

    int createTicket(String email, @Param("data") BookCreateReq bookCreateReq);

    void createTicketPaymentWithInvoice(int ticketsId, String invoiceNo);

    int updateTicketAmountByInvoiceNo(int ticketAmount, String invoiceNo);
}
