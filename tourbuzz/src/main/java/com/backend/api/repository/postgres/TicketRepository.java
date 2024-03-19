package com.backend.api.repository.postgres;

import com.backend.api.entity.TicketEntity;
import com.backend.api.entity.TicketPaymentEntity;
import com.backend.api.request.BookCreateReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TicketRepository {
    List<TicketEntity> getTickets();

    List<TicketEntity> getTicketsByUser(String email);

    TicketEntity getTicketByInvoiceNo(String invoiceNo);

    TicketEntity getTicketById(int id);

    int createTicket(String email, @Param("data") BookCreateReq bookCreateReq);

    int updateTicketAmountByInvoiceNo(int ticketAmount, String invoiceNo);

    void deleteTicket(int id);

    void deleteTicketPaymentByInvoiceNo(String invoiceNo);

    void createTicketFile(int ticketsId, int filesId);
}
