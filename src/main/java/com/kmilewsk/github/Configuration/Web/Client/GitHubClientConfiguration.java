package com.kmilewsk.github.Configuration.Web.Client;

import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Logger;


@Configuration
public class GitHubClientConfiguration {

    @Bean
    public ErrorDecoder errorDecoder(){
        return new FeignApiDecoder();
    }

    @Bean
    Logger.Level feignLoggerLever(){
        return Logger.Level.FULL;
    }
}
