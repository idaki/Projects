package bg.softuni.initservice.config;

import bg.softuni.initservice.Init;
import bg.softuni.initservice.contoller.InitController;
import bg.softuni.initservice.service.InitService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    public InitService initService() {
        return Mockito.mock(InitService.class);
    }

    @Bean
    public InitController initController() {
        return new InitController(initService());
    }

    @Bean
    public Init init() {
        return new Init(initController());
    }
}
