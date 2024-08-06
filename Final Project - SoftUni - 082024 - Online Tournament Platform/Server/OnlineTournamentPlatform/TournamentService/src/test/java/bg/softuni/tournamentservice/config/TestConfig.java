package bg.softuni.tournamentservice.config;

import bg.softuni.userservice.service.UserService;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "bg.softuni.tournamentservice.controller",
        "bg.softuni.tournamentservice.service",
        "bg.softuni.tournamentservice.repository",
        "bg.softuni.userservice.service",
        "bg.softuni.userservice.service.impl",
        "bg.softuni.authenticationservice.service.impl"
})
@EntityScan(basePackages = {
        "bg.softuni.tournamentservice.model",
        "bg.softuni.userservice.models.entity",
        "bg.softuni.userservice.models.entity.user",
        "bg.softuni.userservice.models.entity.password",
        "bg.softuni.userservice.models.entity.authorisation"
})
@EnableJpaRepositories(basePackages = {
        "bg.softuni.tournamentservice.repository",
        "bg.softuni.userservice.repository"
})
@PropertySource("classpath:application.yml")
public class TestConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }

    @Bean
    public JavaMailSender javaMailSender() {
        return Mockito.mock(JavaMailSender.class);
    }

//    @Bean
//    public TournamentServiceImpl tournamentService(TournamentRepository tournamentRepository, UserService userService,
//                                                   GameRepository gameRepository, ModelMapper modelMapper,
//                                                   TeamRepository teamRepository) {
//        return new TournamentServiceImpl(tournamentRepository, userService, gameRepository, modelMapper, teamRepository);
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
