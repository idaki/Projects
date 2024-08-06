package bg.softuni.initservice.controller;

import bg.softuni.initservice.contoller.InitController;
import bg.softuni.initservice.service.InitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class InitControllerTest {

    @Mock
    private InitService initService;

    @InjectMocks
    private InitController initController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInitUser() {
        String role = "ADMIN_SUPER";
        String username = "admin";
        String password = "password";

        initController.initUser(role, username, password); // Ensure the argument order matches

        verify(initService).initUser(role, username, password);
    }

    @Test
    public void testExecuteSqlScript() {
        String scriptName = "testScript.sql";

        initController.executeSqlScript(scriptName);

        verify(initService).executeSqlScript(scriptName);
    }
}
