package affilateweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import affilateweb.model.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}