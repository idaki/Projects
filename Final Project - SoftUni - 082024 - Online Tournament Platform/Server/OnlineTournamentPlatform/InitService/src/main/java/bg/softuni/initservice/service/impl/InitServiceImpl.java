package bg.softuni.initservice.service.impl;

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
    public void initUser(String role, String username, String password ) {
        userService.InitUser(role,username, password);
        userService.InitUser(role,username, password);}

    @Override
    public void executeSqlScript(String databaseScriptName) {
          if (roleRepository.count()<3){
            Resource resource = new ClassPathResource(databaseScriptName);
            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
            databasePopulator.execute(dataSource);
}

    }
}
