package bg.softuni.adminservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@ComponentScan(basePackages = {"bg.softuni.userservice"
        ,"bg.softuni.adminservice"
        ,"bg.softuni.authenticationservice"
        ,"bg.softuni.tournamentservice"
        ,"bg.softuni.teamservice"
})
@EnableWebSecurity
public class AdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }

}
