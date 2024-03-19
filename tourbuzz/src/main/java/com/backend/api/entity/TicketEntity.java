package com.backend.api.entity;

import com.backend.api.response.TourPackageBookedRes;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class TicketEntity {
    private int id;
    private int packages_id;
    private int users_id;
    private int ticket_amount;
    private String note;
    private Timestamp created_at;
    private Timestamp updated_at;

    private PaymentEntity payment;
    private TourPackageBookedRes booked_package;
    private List<FileEntity> files;
}
