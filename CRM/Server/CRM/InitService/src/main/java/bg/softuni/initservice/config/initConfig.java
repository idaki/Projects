package bg.softuni.initservice.config;

import bg.softuni.initservice.Init;
import bg.softuni.initservice.contoller.InitController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class initConfig {
    @Bean
    public CommandLineRunner commandLineRunner(InitController initController) {
        return new Init(initController);
    }
}
