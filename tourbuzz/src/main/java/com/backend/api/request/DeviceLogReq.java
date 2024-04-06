package com.backend.api.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceLogReq {
    private String email;
    private String ip;
    private String device;
    private String location;
}
