package affilateweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import affilateweb.model.entities.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, String> {
}