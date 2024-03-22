package b2bapp.b2bappbackend.config;

import b2bapp.b2bappbackend.DTO.mapper.ReviewDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ReviewDTOMapper reviewMapper() {
        return new ReviewDTOMapper();
    }
}
