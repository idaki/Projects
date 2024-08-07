package bg.softuni.initservice.service.impl;

import bg.softuni.initservice.service.InitService;
import bg.softuni.userservice.repository.RoleRepository;
import bg.softuni.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class InitServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private DataSource dataSource;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private InitServiceImpl initService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInitUser() {
        String role = "ADMIN_SUPER";
        String username = "admin";
        String password = "password";

        // Act
        initService.initUser(role, username, password);

        // Assert
        verify(userService, times(2)).InitUser(role, username, password);
    }



    @Test
    public void testExecuteSqlScriptWhenRoleCountThreeOrMore() {
        String scriptName = "testScript.sql";

        // Mock the count method
        when(roleRepository.count()).thenReturn(3L);

        // Act
        initService.executeSqlScript(scriptName);

        // Verify no interaction with ResourceDatabasePopulator and DataSource
        verifyNoInteractions(dataSource);
    }
}
