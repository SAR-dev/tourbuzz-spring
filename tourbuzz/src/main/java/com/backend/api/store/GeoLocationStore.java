package com.backend.api.store;

import com.backend.api.request.DeviceLogReq;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua_parser.Client;
import ua_parser.Parser;

import java.io.IOException;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class GeoLocationStore {
    private final Parser uaParser = new Parser();

    @Autowired
    HttpServletRequest httpServletRequest;
    private static final String UNKNOWN = "UNKNOWN";
    private static final String DEVICE_DETAILS_HEADER = "user-agent";
    private static final String DEVICE_IP_HEADER = "x-forwarded-for";

    @Transactional
    public DeviceLogReq getDeviceDetails(String email) throws IOException, GeoIp2Exception {
        String ip = extractIp();
        String deviceDetails = getDeviceDetails();
        return DeviceLogReq.builder()
                .email(email)
                .ip(ip)
                .device(deviceDetails)
                .build();
    }

    private String extractIp() {
        String clientIp;
        String clientXForwardedForIp = httpServletRequest.getHeader(DEVICE_IP_HEADER);
        if (nonNull(clientXForwardedForIp)) {
            clientIp = parseXForwardedHeader(clientXForwardedForIp);
        } else {
            clientIp = httpServletRequest.getRemoteAddr();
        }
        return clientIp;
    }

    private String parseXForwardedHeader(String header) {
        return header.split(" *, *")[0];
    }

    private String getDeviceDetails() {
        String userAgent = httpServletRequest.getHeader(DEVICE_DETAILS_HEADER);
        String deviceDetails = UNKNOWN;

        Client client = uaParser.parse(userAgent);
        if (nonNull(client)) {
            String osDetails = client.os.family + " " + client.os.major;
            if (nonNull(client.os.minor)) osDetails += "." + client.os.minor;
            String clientDetails = client.userAgent.family + " " + client.userAgent.major;
            if (nonNull(client.userAgent.minor)) clientDetails += "." + client.userAgent.minor;
            deviceDetails = clientDetails + " - " + osDetails;
        }
        return deviceDetails;
    }
}
