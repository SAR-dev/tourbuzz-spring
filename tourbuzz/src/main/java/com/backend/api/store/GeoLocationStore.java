package com.backend.api.store;

import com.backend.api.request.DeviceLogReq;
import com.backend.api.service.AuthService;
import com.backend.api.service.UserService;
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
    private final UserService userService;

    private final Parser uaParser = new Parser();
    @Autowired
    private ApplicationContext ctx;
    @Autowired
    HttpServletRequest httpServletRequest;
    private static final String UNKNOWN = "UNKNOWN";
    private DatabaseReader databaseReader;

    @PostConstruct
    public void init() throws IOException {
        Resource resource = ctx.getResource("classpath:GeoLite2-City.mmdb");
        File file = resource.getFile();
        this.databaseReader = new DatabaseReader.Builder(file).build();
    }

    @Transactional
    public DeviceLogReq registerDeviceLog(String email) throws IOException, GeoIp2Exception {
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
        String clientXForwardedForIp = httpServletRequest
                .getHeader("x-forwarded-for");
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
        String userAgent = httpServletRequest
                .getHeader("user-agent");
        String deviceDetails = UNKNOWN;

        Client client = uaParser.parse(userAgent);
        if (nonNull(client)) {
            deviceDetails = client.userAgent.family + " " + client.userAgent.major + "." + client.userAgent.minor +
                    " - " + client.os.family + " " + client.os.major + "." + client.os.minor;
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
