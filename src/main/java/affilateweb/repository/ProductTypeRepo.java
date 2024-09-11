package affilateweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import affilateweb.model.entities.ProductType;

public interface ProductTypeRepo extends JpaRepository<ProductType, Integer> {
}