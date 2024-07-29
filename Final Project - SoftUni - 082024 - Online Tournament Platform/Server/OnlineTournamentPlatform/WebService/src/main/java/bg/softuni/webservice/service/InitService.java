package bg.softuni.webservice.service;

public interface InitService {
    void initUser(String role, String username, String password );

    void executeSqlScript(String databaseScriptName);
}
