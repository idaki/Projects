package bg.softuni.userservice.service.employee;


import bg.softuni.crudservice.crud.CrudServiceImpl;
import bg.softuni.userservice.models.dto.gson.EmployeeImportDTO;
import bg.softuni.userservice.models.entity.business.Employee;
import bg.softuni.userservice.repository.EmployeeRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl extends CrudServiceImpl<Employee, Long> implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        super(employeeRepository);
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String importUsers(EmployeeImportDTO[] employeeImportDTOS) {
        int newEmployeesCount = 0;
        int skippedEmployeesCount = 0;

        for (EmployeeImportDTO employeeImportDTO : employeeImportDTOS) {
            Optional<Employee> existingEmployeeOptional = employeeRepository.findByEmail(employeeImportDTO.getEmail());

            if (existingEmployeeOptional.isEmpty()) {
                Employee employee = modelMapper.map(employeeImportDTO, Employee.class);
                employeeRepository.saveAndFlush(employee);
                newEmployeesCount++;
            } else {
                System.out.printf("Employee with email %s already exists. Skipping...\n", employeeImportDTO.getEmail());
                skippedEmployeesCount++;
            }
        }

        return String.format("Employee import completed. New Employees: %d, Skipped Employees: %d", newEmployeesCount, skippedEmployeesCount);
    }


}