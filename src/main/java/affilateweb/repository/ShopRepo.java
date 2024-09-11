package affilateweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import affilateweb.model.entities.Shop;

public interface ShopRepo extends JpaRepository<Shop, Integer> {
}