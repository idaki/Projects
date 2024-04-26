package bg.softuni.adminservice.service.userservice;

import bg.softuni.userservice.models.dto.gson.CompanyImportDTO;
import bg.softuni.userservice.models.dto.gson.ConsumerImportDTO;
import bg.softuni.userservice.models.dto.gson.EmployeeImportDTO;
import bg.softuni.userservice.models.entity.business.Company;
import bg.softuni.userservice.models.entity.business.Employee;
import bg.softuni.userservice.models.entity.consumer.Consumer;
import bg.softuni.userservice.repository.CompanyRepository;
import bg.softuni.userservice.repository.ConsumerRepository;
import bg.softuni.userservice.repository.EmployeeRepository;
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
public class AdminUserServiceImpl implements AdminUserService {

    private final ConsumerRepository consumerRepository;
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ResourceLoader resourceLoader;

    public AdminUserServiceImpl(ConsumerRepository consumerRepository, CompanyRepository companyRepository, EmployeeRepository employeeRepository, ModelMapper modelMapper, Gson gson, ResourceLoader resourceLoader) {
        this.consumerRepository = consumerRepository;
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void importConsumerUsers() throws IOException {
        ConsumerImportDTO[] consumerImportDTOS = gson.fromJson(readUsersFromFile(), ConsumerImportDTO[].class);

        int newConsumersCount = 0;
        int skippedConsumersCount = 0;

        for (int i = 14; i <= 19; i++) {
            ConsumerImportDTO consumerImportDTO = consumerImportDTOS[i];
            Optional<Consumer> existingConsumerOptional = consumerRepository.findByEmail(consumerImportDTO.getEmail());

            if (existingConsumerOptional.isEmpty()) {
                Consumer consumer = modelMapper.map(consumerImportDTO, Consumer.class);
                consumerRepository.saveAndFlush(consumer);
                newConsumersCount++;
            } else {
                System.out.printf("Consumer with email %s already exists. Skipping...\n", consumerImportDTO.getEmail());
                skippedConsumersCount++;
            }
        }

    }

    @Override
    public void importEmployeeUsers() throws IOException {
        EmployeeImportDTO[] employeeImportDTOs = gson.fromJson(readUsersFromFile(), EmployeeImportDTO[].class);

        int newEmployeesCount = 0;
        int skippedEmployeesCount = 0;

        for (int i = 7; i <= 13; i++) {
            EmployeeImportDTO employeeImportDTO = employeeImportDTOs[i];
            Optional<Employee> existingEmployeeOptional = employeeRepository.findByEmail(employeeImportDTO.getEmail());

            if (existingEmployeeOptional.isEmpty()) {
                Employee employee = modelMapper.map(employeeImportDTO, Employee.class);

                // Assign a random company to the employee
                Company company = companyRepository.findCompanyById(ThreadLocalRandom.current().nextLong(1, 7));
                if (company != null) {
                    employee.setCompany(company);
                    company.getEmployees().add(employee);
                } else {
                    System.out.println("No company found. Skipping employee.");
                    skippedEmployeesCount++;
                    continue; // Skip this iteration
                }

                employeeRepository.saveAndFlush(employee);
                newEmployeesCount++;
            } else {
                System.out.printf("Employee with email %s already exists. Skipping...\n", employeeImportDTO.getEmail());
                skippedEmployeesCount++;
            }
        }

    }

    @Override
    public void importCompanyUsers() throws IOException {
        CompanyImportDTO[] companyImportDTOS = gson.fromJson(readUsersFromFile(), CompanyImportDTO[].class);

        int newCompaniesCount = 0;
        int skippedCompaniesCount = 0;

        for (int i = 0; i <= 6; i++) {
            CompanyImportDTO companyImportDTO = companyImportDTOS[i];
            Optional<Company> existingCompanyOptional = companyRepository.findByEmail(companyImportDTO.getEmail());

            if (existingCompanyOptional.isEmpty()) {
                Company company = modelMapper.map(companyImportDTO, Company.class);

                companyRepository.saveAndFlush(company);
                newCompaniesCount++;
            } else {
                System.out.printf("Company with email %s already exists. Skipping...\n", companyImportDTO.getEmail());
                skippedCompaniesCount++;
            }
        }

    }

    private String readUsersFromFile() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:files/users.json");
        try (InputStream inputStream = resource.getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
