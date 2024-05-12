package com.backend.api.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MagicLinkMailData {
    private String email;
    private String key;
    private String token;
    private String ip;
    private String device;
    private String location;
}
