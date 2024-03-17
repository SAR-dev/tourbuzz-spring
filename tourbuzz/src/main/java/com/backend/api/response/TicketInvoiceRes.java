package com.backend.api.response;

import lombok.Data;

@Data
public class TicketInvoiceRes {
    private int ticket_id;
    private String invoice_no;
}
