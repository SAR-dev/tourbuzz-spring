package com.backend.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ErrorInfo {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private int status;
    private String error;
    private String trace;
    private String message;

    public ErrorInfo(Exception ex, HttpStatus httpStatus, String msg) {
        this.timestamp = new Date();
        this.status = httpStatus.value();
        this.error = httpStatus.name();
        this.trace = ex.getMessage();
        this.message = msg;
    }
}