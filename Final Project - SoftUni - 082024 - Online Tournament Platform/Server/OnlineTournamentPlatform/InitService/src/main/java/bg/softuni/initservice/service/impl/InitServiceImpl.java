package bg.softuni.initservice.service.impl;

import bg.softuni.exceptionhandlerservice.UserAlreadyExistsException;
import bg.softuni.initservice.service.InitService;
import bg.softuni.userservice.repository.RoleRepository;
import bg.softuni.userservice.service.UserService;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;


@Service
public class InitServiceImpl implements InitService {

    private final UserService userService;
    private final DataSource dataSource;
    private final RoleRepository roleRepository;



    public InitServiceImpl(UserService userService, DataSource dataSource, RoleRepository roleRepository) {
        this.userService = userService;
        this.dataSource = dataSource;
        this.roleRepository = roleRepository;
    }

    @Override
    public void initUser( String username, String password, String role ) {

       try {
        userService.InitUser(username, password, role);
        userService.InitUser(username, password , role);
       } catch (UserAlreadyExistsException e) {
           System.out.println("User already exists, skipping initialization: " + e.getMessage());
       }


    }

    @Override
    public void executeSqlScript(String databaseScriptName) {
          if (roleRepository.count()<3){
            Resource resource = new ClassPathResource(databaseScriptName);
            ResourceDatabasePopulator databasePopulate = new ResourceDatabasePopulator(resource);
            databasePopulate.execute(dataSource);
}

    }
}
