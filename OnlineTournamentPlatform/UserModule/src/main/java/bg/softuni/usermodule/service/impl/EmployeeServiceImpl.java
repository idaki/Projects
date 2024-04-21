package bg.softuni.usermodule.service.impl;


import bg.softuni.usermodule.models.dto.gson.EmployeeImportDTO;
import bg.softuni.usermodule.models.entity.business.company.Company;
import bg.softuni.usermodule.models.entity.business.employee.Employee;
import bg.softuni.usermodule.repository.CompanyRepository;
import bg.softuni.usermodule.repository.EmployeeRepository;
import bg.softuni.usermodule.service.EmployeeService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class EmployeeServiceImpl implements EmployeeService {

//    private final EmployeeRepository employeeRepository;
//    private final CompanyRepository companyRepository;
//    private final ModelMapper modelMapper;
//    private final String FILE_PATH = "src/main/resources/files/users.json";
//    private final Gson gson;
//    private final ResourceLoader resourceLoader;
//
//    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository, ModelMapper modelMapper, Gson gson, ResourceLoader resourceLoader) {
//        this.employeeRepository = employeeRepository;
//        this.companyRepository = companyRepository;
//        this.modelMapper = modelMapper;
//        this.gson = gson;
//        this.resourceLoader = resourceLoader;
//    }
//
//    @Override
//    public boolean areImported() {
//        return this.employeeRepository.count() > 0;
//    }
//
//    @Override
//    public String readUsersFromFile() throws IOException {
//        Resource resource = resourceLoader.getResource("classpath:files/users.json");
//        try (InputStream inputStream = resource.getInputStream()) {
//            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
//        }
//    }
//
//    @Override
//    public String importUsers() throws IOException {
//        EmployeeImportDTO[] employeeImportDTOs = gson.fromJson(this.readUsersFromFile(), EmployeeImportDTO[].class);
//
//        for (EmployeeImportDTO employeeImportDTO : employeeImportDTOs) {
//            Employee employee = modelMapper.map(employeeImportDTO, Employee.class);
//            Company company = this.companyRepository.findCompanyById(ThreadLocalRandom.current().nextInt(1, 21));
//            employee.setCompany(company);
//            company.getEmployees().add(employee);
//           this.employeeRepository.saveAndFlush(employee);
//        }
//        return "CompanyImport Completed";
//    }
}
