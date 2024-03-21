package b2bapp.b2bappbackend.config;

import b2bapp.b2bappbackend.DTO.mapper.ReviewMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ReviewMapper reviewMapper() {
        return new ReviewMapper();
    }
}
