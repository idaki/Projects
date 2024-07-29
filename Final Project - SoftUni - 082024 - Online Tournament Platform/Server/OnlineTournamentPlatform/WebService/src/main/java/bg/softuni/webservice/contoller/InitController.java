package bg.softuni.webservice.contoller;

import bg.softuni.webservice.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InitController {


    private final InitService initService;

    public InitController(InitService initService) {
        this.initService = initService;
    }


    public void initUser(String username, String password, String role) {
        initService.initUser(username, password,  role);
    }

    public void executeSqlScript(String databaseScriptName) {
        initService.executeSqlScript(databaseScriptName);
    }
}
