package com.backend.api.store;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
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
    private DatabaseReader databaseReader;

    @PostConstruct
    public void init() throws IOException {
        Resource resource = ctx.getResource("classpath:GeoLite2-City.mmdb");
        File file = resource.getFile();
        this.databaseReader = new DatabaseReader.Builder(file).build();
    }

    public void verifyDevice() throws IOException, GeoIp2Exception {
        String ip = extractIp(httpServletRequest);
        String location = getIpLocation(ip);

        String deviceDetails = getDeviceDetails(httpServletRequest.getHeader("user-agent"));

//      Get user and check and verify to do whatever needed

    }

    private String extractIp(HttpServletRequest request) {
        String clientIp;
        String clientXForwardedForIp = request
                .getHeader("x-forwarded-for");
        if (nonNull(clientXForwardedForIp)) {
            clientIp = parseXForwardedHeader(clientXForwardedForIp);
        } else {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }

    private String parseXForwardedHeader(String header) {
        return header.split(" *, *")[0];
    }

    private String getDeviceDetails(String userAgent) {
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

        CityResponse cityResponse = databaseReader.city(ipAddress);
        if (nonNull(cityResponse) &&
                nonNull(cityResponse.getCity()) &&
                !StringUtils.isEmpty(cityResponse.getCity().getName())) {

            location = cityResponse.getCity().getName();
        }

        return location;
    }
}
