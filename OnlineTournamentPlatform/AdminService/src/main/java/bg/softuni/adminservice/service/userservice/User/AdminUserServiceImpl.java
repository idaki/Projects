package bg.softuni.adminservice.service.userservice.User;

import bg.softuni.userservice.models.entity.business.Company;
import bg.softuni.userservice.models.entity.business.Employee;
import bg.softuni.userservice.models.entity.consumer.Consumer;
import bg.softuni.userservice.models.entity.user.User;
import bg.softuni.userservice.repository.UserRepository;
import bg.softuni.userservice.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements bg.softuni.adminservice.service.userservice.user.AdminUserService {
    private final UserRepository userRepository;
    private final UserService userService;

    public AdminUserServiceImpl(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @Override
    public void createNewCompany() {
        Company company = new Company();
        company.setUsername("user1");
        company.setEmail("user1@example.com");
        this.userService.save(company);
    }

    @Override
    public void creatConsumer() {
        Consumer consumer = new Consumer();
        consumer.setUsername("user2");
        consumer.setEmail("user2@example.com");
        this.userService.save(consumer);
    }

    @Override
    public void createNewEmployee() {
        Employee employee = new Employee();
        employee.setUsername("user3");
        employee.setEmail("user3@example.com");
        Company company =
                 (Company)   this.userService.findByUsername("user1").get();
        employee.setCompany(company);
        this.userService.save(employee);

    }

    @Override
    public void removeCompany() {
        User user =
                   this.userService.findByUsername("user1").get();
        this.userService.deleteById(user.getId());
    }

    @Override
    public void removeConsumer() {
        User user =
                this.userService.findByUsername("user2").get();
        this.userService.deleteById(user.getId());
    }

    @Override
    public void removeEmployee() {
        User user =
                this.userService.findByUsername("user3").get();
        this.userService.deleteById(user.getId());
    }
}
