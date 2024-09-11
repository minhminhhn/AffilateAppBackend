package affilateweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import affilateweb.model.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
}