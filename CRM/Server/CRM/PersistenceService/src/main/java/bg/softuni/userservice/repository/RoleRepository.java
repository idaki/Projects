package bg.softuni.userservice.repository;




import bg.softuni.userservice.models.entity.authorisation.Role;
import bg.softuni.userservice.models.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleEnum roleEnum);
}
