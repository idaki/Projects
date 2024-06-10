package bg.softuni.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("bg.softuni.*" )
@ComponentScan(basePackages = { "bg.softuni.tournamentservice.*"
        ,"bg.softuni.gameservice.*"
        ,"bg.softuni.userservice.*"

        ,"bg.softuni.locationservice.*"
        ,"bg.softuni.authenticationservice.*"
})
@EntityScan("bg.softuni.*" )
public class WebServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }
}
