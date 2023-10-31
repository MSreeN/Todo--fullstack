package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class BasicAuthenticationSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityConfig(HttpSecurity httpSecurity)throws Exception{
        RequestMatcher optionsMatcher = new AntPathRequestMatcher("/**", HttpMethod.OPTIONS.toString());
        httpSecurity.authorizeHttpRequests(auth -> auth
                        .requestMatchers(optionsMatcher).permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                //this lambda expression wll return httpsecurity object with csrf set to disable and this
                //method will return that object as bean
                .csrf(csrf -> csrf.disable());
        return httpSecurity.build();
    }
}
