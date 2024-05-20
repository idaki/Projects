package bg.softuni.webservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan({
        "bg.softuni.tournamentservice.controller"
        ,"bg.softuni.tournamentservice.service"
        ,"bg.softuni.tournamentservice.repository"})
class WebServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
