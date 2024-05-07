package bg.softuni.userservice.service.employee;

import bg.softuni.crudservice.crud.CrudService;
import bg.softuni.userservice.models.dto.gson.EmployeeImportDTO;
import bg.softuni.userservice.models.entity.business.Employee;


public interface EmployeeService extends CrudService<Employee, Long> {
    String importUsers(EmployeeImportDTO[] employeeImportDTOS);


}
