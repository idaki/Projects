package bg.softuni.userservice.service.impl;



import bg.softuni.userservice.models.dto.gson.EmployeeImportDTO;
import bg.softuni.userservice.models.entity.business.company.Company;
import bg.softuni.userservice.models.entity.business.employee.Employee;
import bg.softuni.userservice.repository.CompanyRepository;
import bg.softuni.userservice.repository.EmployeeRepository;
import bg.softuni.userservice.service.EmployeeService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final String FILE_PATH = "src/main/resources/files/users.json";
    private final Gson gson;
    private final ResourceLoader resourceLoader;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository, ModelMapper modelMapper, Gson gson, ResourceLoader resourceLoader) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public boolean areImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readUsersFromFile() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:files/users.json");
        try (InputStream inputStream = resource.getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    @Override
    public String importUsers() throws IOException {
        EmployeeImportDTO[] employeeImportDTOs = gson.fromJson(this.readUsersFromFile(), EmployeeImportDTO[].class);

        int newEmployeesCount = 0;
        int skippedEmployeesCount = 0;

        for (EmployeeImportDTO employeeImportDTO : employeeImportDTOs) {
            Optional<Employee> existingEmployeeOptional = this.employeeRepository.findByEmail(employeeImportDTO.getEmail());

            if (existingEmployeeOptional.isEmpty()) {
                Employee employee = modelMapper.map(employeeImportDTO, Employee.class);

                // Assign a random company to the employee
                Company company = this.companyRepository.findCompanyById(ThreadLocalRandom.current().nextInt(1, 21));
                if (company != null) {
                    employee.setCompany(company);
                    company.getEmployees().add(employee);
                } else {
                    System.out.println("No company found. Skipping employee.");
                    skippedEmployeesCount++;
                    continue; // Skip this iteration
                }

                this.employeeRepository.saveAndFlush(employee);
                newEmployeesCount++;
            } else {
                System.out.printf("Employee with email %s already exists. Skipping...\n", employeeImportDTO.getEmail());
                skippedEmployeesCount++;
            }
        }

        return String.format("CompanyImport Completed. New Employees: %d, Skipped Employees: %d", newEmployeesCount, skippedEmployeesCount);
    }

}
