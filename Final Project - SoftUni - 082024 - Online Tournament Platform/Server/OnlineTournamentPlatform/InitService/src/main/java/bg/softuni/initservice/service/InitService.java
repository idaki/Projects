package bg.softuni.initservice.service;

public interface InitService {
    void initUser( String username, String password, String role);

    void executeSqlScript(String databaseScriptName);
}
