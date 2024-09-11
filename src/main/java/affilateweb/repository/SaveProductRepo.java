package affilateweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import affilateweb.model.entities.SaveProduct;

public interface SaveProductRepo extends JpaRepository<SaveProduct, Integer> {
}