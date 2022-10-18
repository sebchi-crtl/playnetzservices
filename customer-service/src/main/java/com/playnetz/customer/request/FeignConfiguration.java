package com.playnetz.customer.request;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;

public class FeignConfiguration {

    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(
            @Value("${service.security.secure-key-username}") String username,
        @Value("${service.security.secure-key-password}") String password
    )
    {
        return new BasicAuthRequestInterceptor(username, password);
    }
}
