package com.backend.api.request;

import lombok.Data;

@Data
public class SocketMessageReq {
    private String name;
    private String message;
}
