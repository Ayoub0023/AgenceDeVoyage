package com.travel.flightservice.config;
import org.springframework.context.annotation.Bean;
import feign.Logger;
public class FeignClientConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
