package affilateweb.repository;

import affilateweb.model.entities.ProductTypeCouponCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTypeCouponCategoryRepo extends JpaRepository<ProductTypeCouponCategory, Integer> {
    List<ProductTypeCouponCategory> findAllByProductTypeId(int productTypeId);
}
