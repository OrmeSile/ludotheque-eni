package dev.orme.ludotheque.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

@Configuration
public class Server {
    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter(){
        return new ProtobufHttpMessageConverter();
    }
    @Bean
    GsonHttpMessageConverter gsonHttpMessageConverter(){
        return new GsonHttpMessageConverter();
    }
}
