package affilateweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import affilateweb.model.entities.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
    Account findByEmail(String email);
}
