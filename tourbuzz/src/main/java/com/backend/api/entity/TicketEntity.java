package com.backend.api.entity;

import com.backend.api.response.TourPackageBookedRes;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TicketEntity {
    private int id;
    private int packagesId;
    private int usersId;
    private int ticketAmount;
    private String note;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private PaymentEntity payment;
    private TourPackageBookedRes bookedPackage;
    private List<FileEntity> files;
}
