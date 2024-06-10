package bg.softuni.webservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan(basePackages = { "bg.softuni.tournamentservice.*"
        ,"bg.softuni.gameservice.*"
        ,"bg.softuni.userservice.*"

        ,"bg.softuni.locationservice.*"
        ,"bg.softuni.authenticationservice.*"

})
@EntityScan("bg.softuni.*" )
class WebServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
