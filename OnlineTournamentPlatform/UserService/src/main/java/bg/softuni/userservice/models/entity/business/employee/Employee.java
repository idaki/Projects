package bg.softuni.userservice.models.entity.business.employee;


import bg.softuni.userservice.models.entity.business.company.Company;
import jakarta.persistence.*;

@Entity
@Table(name = "platform_employees")
public class Employee  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String username;

    @Column(unique = true,nullable = false)
    private String email;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @Column(name="active_account")
    private Boolean isActive;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
