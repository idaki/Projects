package bg.softuni.initservice;


import bg.softuni.initservice.config.TestConfig;
import bg.softuni.initservice.contoller.InitController;
import bg.softuni.initservice.service.InitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class})
@AutoConfigureMockMvc
public class InitIntegrationTest {

    @Autowired
    private Init init;

    @Autowired
    private InitController initController;

    @Autowired
    private InitService initService;

    @Test
    void testInit() throws Exception {
        // Call the method to test
        init.run();

        // Add assertions and verifications as necessary
    }
}
