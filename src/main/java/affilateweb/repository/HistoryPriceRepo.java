package affilateweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import affilateweb.model.entities.HistoryPrice;

import java.util.List;

public interface HistoryPriceRepo extends JpaRepository<HistoryPrice, Integer> {
    List<HistoryPrice> findByProductId(int productId);
}