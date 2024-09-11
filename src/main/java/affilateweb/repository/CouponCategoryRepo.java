package affilateweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import affilateweb.model.entities.CouponCategory;

public interface CouponCategoryRepo extends JpaRepository<CouponCategory, Integer> {
}