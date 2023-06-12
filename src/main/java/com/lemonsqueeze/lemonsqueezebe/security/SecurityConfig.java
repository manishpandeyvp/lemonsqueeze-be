package com.lemonsqueeze.lemonsqueezebe.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;

import com.lemonsqueeze.lemonsqueezebe.security.filter.AuthenticationFilter;
import com.lemonsqueeze.lemonsqueezebe.security.filter.ExceptionHandlerFilter;
import com.lemonsqueeze.lemonsqueezebe.security.filter.JwtAuthorizationFilter;
import com.lemonsqueeze.lemonsqueezebe.security.manager.CustomAuthenticationManager;
import com.lemonsqueeze.lemonsqueezebe.utility.Constants;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private CustomAuthenticationManager authenticationManager;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager);
        authenticationFilter.setFilterProcessesUrl("/authenticate");

        http
            .headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.disable()))
            .csrf((csrf)-> csrf.disable())
            .authorizeHttpRequests(
                (authz) -> authz
                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                    .requestMatchers(HttpMethod.POST, Constants.REGISTER_USER_PATH).permitAll()
                    .anyRequest()
                    .authenticated()
            )
            .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
            .addFilter(authenticationFilter)
            .addFilterAfter(new JwtAuthorizationFilter(), AuthenticationFilter.class)
            .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
