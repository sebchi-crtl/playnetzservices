package com.playnetz.device.userLcation;

import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.nonNull;

public class UserLocation {
    private String extractIp(HttpServletRequest request){
        String clientIp;

        String clientXForwardedForIp = request
                .getHeader("x-forwarded-for");

        if(nonNull(clientXForwardedForIp))
            clientIp = parseXForwardedHeader(clientXForwardedForIp);
        else
            clientIp = request.getRemoteAddr();

        return clientIp;
    }

    private String parseXForwardedHeader(String clientXForwardedForIp) {
        return clientXForwardedForIp;
    }
}
