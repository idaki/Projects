package bg.softuni.userservice.service;


import bg.softuni.userservice.models.entity.business.employee.Employee;
import bg.softuni.userservice.models.entity.business.employee.PasswordEmployee;

public interface PasswordEmployeeService<T> {
    PasswordEmployee getPasswordEmployee(Employee employee);
}