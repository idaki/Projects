package bg.softuni.userservice.service.employee;

import bg.softuni.userservice.models.dto.gson.EmployeeImportDTO;
import bg.softuni.userservice.models.entity.business.Employee;
import bg.softuni.userservice.service.crud.CrudService;

import java.util.Optional;

public interface EmployeeService extends CrudService<Employee, Long> {
    String importUsers(EmployeeImportDTO[] employeeImportDTOS);


}
