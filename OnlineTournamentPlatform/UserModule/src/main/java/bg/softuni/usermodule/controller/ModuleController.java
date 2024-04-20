package bg.softuni.usermodule.controller;

import bg.softuni.usermodule.service.CompanyService;
import bg.softuni.usermodule.service.ConsumerService;
import bg.softuni.usermodule.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class ModuleController implements CommandLineRunner {

    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final ConsumerService consumerService;

    public ModuleController(CompanyService companyService, EmployeeService employeeService, ConsumerService consumerService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.consumerService = consumerService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.companyService.importUsers();
        this.employeeService.importUsers();
        this.consumerService.importUsers();
    }
}

