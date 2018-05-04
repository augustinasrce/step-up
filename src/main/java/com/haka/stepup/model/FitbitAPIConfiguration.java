package com.haka.stepup.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.web.context.request.RequestContextListener;

import javax.annotation.Resource;

@Configuration
public class FitbitAPIConfiguration {

//    @Resource
//    @Qualifier("accessTokenRequest")
//    private AccessTokenRequest accessTokenRequest;
//
//    @Bean
//    OAuth2RestTemplate fitbitOAuthRestTemplate(UserInfoRestTemplateFactory uir) {
//        return uir.getUserInfoRestTemplate();
//    }




    @Bean
    public OAuth2RestTemplate fitbitOAuthRestTemplate(OAuth2ClientContext clientContext) {
        return new OAuth2RestTemplate(fitBitApi(), clientContext);
    }

    @Bean
    @ConfigurationProperties("fitbitapi.client")
    public AuthorizationCodeResourceDetails fitBitApi() {
        return new AuthorizationCodeResourceDetails();
    }

//    @Bean
//    public RequestContextListener requestContextListener() {
//        return new RequestContextListener();
//    }


}
