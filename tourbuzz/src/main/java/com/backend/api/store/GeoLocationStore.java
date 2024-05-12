package com.backend.api.store;

import com.backend.api.request.DeviceLogReq;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua_parser.Client;
import ua_parser.Parser;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class GeoLocationStore {
    private final Parser uaParser = new Parser();
    @Autowired
    private ApplicationContext ctx;
    @Autowired
    HttpServletRequest httpServletRequest;
    private static final String UNKNOWN = "UNKNOWN";
    private static final String DEVICE_DETAILS_HEADER = "user-agent";
    private static final String DEVICE_IP_HEADER = "x-forwarded-for";
    private static final String GEOLOCATION_FILE_DIR = "classpath:GeoLite2-City.mmdb";
    private DatabaseReader databaseReader;

    @PostConstruct
    public void init() throws IOException {
        Resource resource = ctx.getResource(GEOLOCATION_FILE_DIR);
        File file = resource.getFile();
        this.databaseReader = new DatabaseReader.Builder(file).build();
    }

    @Transactional
    public DeviceLogReq getDeviceDetails(String email) throws IOException, GeoIp2Exception {
        String ip = extractIp();
        String location = getIpLocation(ip);
        String deviceDetails = getDeviceDetails();
        return DeviceLogReq.builder()
                .email(email)
                .ip(ip)
                .location(location)
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

    private String getIpLocation(String ip) throws IOException, GeoIp2Exception {
        String location = UNKNOWN;
        InetAddress ipAddress = InetAddress.getByName(ip);

        try {
            CityResponse cityResponse = databaseReader.city(ipAddress);
            if (nonNull(cityResponse) &&
                    nonNull(cityResponse.getCity()) &&
                    !StringUtils.isEmpty(cityResponse.getCity().getName())) {

                location = cityResponse.getCity().getName();
            }
            return location;
        } catch (AddressNotFoundException e){
            return location;
        }
    }
}
