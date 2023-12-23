package com.backend.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
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
        this.message = msg.substring(0, Math.min(msg.length(), 200));
    }
}