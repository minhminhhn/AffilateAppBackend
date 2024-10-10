package affilateweb.repository;

import affilateweb.model.entities.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
