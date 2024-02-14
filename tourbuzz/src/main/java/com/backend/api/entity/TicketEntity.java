package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TicketEntity {
    private int id;
    private int packages_id;
    private int users_id;
    private int paid_amount;
    private int ticket_amount;
    private boolean completed;
    private String payment_method;
    private String payment_id;
    private Timestamp created_at;
    private Timestamp updated_at;
}
