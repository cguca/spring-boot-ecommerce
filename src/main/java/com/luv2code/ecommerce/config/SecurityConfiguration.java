package com.luv2code.ecommerce.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // protect the endpoint for /api/orders
        http.authorizeRequests()
                .requestMatchers("/api/orders/**")
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();

        http.cors();

        http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());
        //Okta.configureResourceServer401ResponseBody(http);

        // Disable CSRF
        http.csrf().disable();

        return http.build();
    }
}
