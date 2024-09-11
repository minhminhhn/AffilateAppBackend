package affilateweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import affilateweb.model.entities.HistoryPrice;

public interface HistoryPriceRepo extends JpaRepository<HistoryPrice, Integer> {
}