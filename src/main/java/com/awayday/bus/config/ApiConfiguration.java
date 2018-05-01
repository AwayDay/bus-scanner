package com.awayday.bus.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApiConfiguration {
    private final String BUS_STATION_API_HOST;

    public ApiConfiguration(@Value("${openApi.busStation.host}") String host) {
        BUS_STATION_API_HOST = host;
    }

    @Bean
    public WebClient openBusApiClient() {
        return WebClient.builder()
                .baseUrl(BUS_STATION_API_HOST)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .defaultHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8")
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
