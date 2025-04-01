package bg.softuni.webservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories("bg.softuni.*")
@ComponentScan(basePackages = {
        "bg.softuni.*",
})
@EntityScan("bg.softuni.*")
@EnableScheduling
public class WebServiceApplication {





    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }


}
