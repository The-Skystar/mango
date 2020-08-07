package com.tss.mangoauth.config;

import lombok.Data;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
@Data
public class ClientParameter {
    /**
     * oauth2 client id
     */
    @NonNull
    private String clientId = "client-app";
    /**
     * oauth2 client secret
     */
    @NonNull
    private String clientSecret = "mangobubble";
    /**
     * oauth2 client grant types
     * default value is "password,refresh_token"
     */
    @NonNull
    private String[] grantTypes = new String[]{"password", "refresh_token"};
    /**
     * oauth2 client scope
     * default value is "api"
     */
    @NonNull
    private String[] scopes = new String[]{"api"};
    /**
     * oauth2 application resource id
     * default value is "api"
     */
    @NonNull
    private String[] resourceId = new String[]{"api"};
    /**
     * oauth2 access token validity seconds
     * default value is 7200 second
     */
    @NonNull
    private int accessTokenValiditySeconds = 7200;
    /**
     * oauth2 access refresh token validity seconds
     */
    @NonNull
    private int refreshTokenValiditySeconds = 86400;
    /**
     * jwt.jks keypass
     */
    @NonNull
    private String keypass = "mangobubble";
}
