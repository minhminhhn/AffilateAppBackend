package affilateweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import affilateweb.model.entities.Coupon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponRepo extends JpaRepository<Coupon, String> {
    @Query("SELECT c FROM Coupon c WHERE c.expiredAt > :currentDateTime")
    List<Coupon> findAllValidCoupons(@Param("currentDateTime") String currentDateTime);

    @Query("SELECT c FROM Coupon c WHERE c.expiredAt > :currentDateTime AND c.couponCategoryId = :couponCategoryId")
    List<Coupon> findAllValidCouponsByCategoryId(@Param("currentDateTime") String currentDateTime, @Param("couponCategoryId") int couponCategoryId);
}