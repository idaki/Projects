package bg.softuni.adminservice.service.userservice;

import java.io.IOException;

public interface AdminUserService {


    void importEmployeeUsers() throws IOException;

    void importCompanyUsers() throws IOException;

    void importConsumerUsers() throws IOException;
}
