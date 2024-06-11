package bg.softuni.webservice;

import bg.softuni.authenticationservice.service.LoginService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@EnableJpaRepositories("bg.softuni.*")
@ComponentScan(basePackages = {
        "bg.softuni.tournamentservice.*",
        "bg.softuni.gameservice.*",
        "bg.softuni.userservice.*",
        "bg.softuni.locationservice.*",
        "bg.softuni.authenticationservice.*"
})
@EntityScan("bg.softuni.*")
public class WebServiceApplication {

    private final LoginService loginService;

    @Autowired
    public WebServiceApplication(LoginService loginService) {
        this.loginService = loginService;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }


}
