package affilateweb.service;

import affilateweb.model.entities.Coupon;
import affilateweb.repository.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponRepo couponRepo;

    public List<Coupon> getAllValidCoupons() {
        // Lấy thời gian hiện tại và định dạng nó thành chuỗi ISO 8601
        String currentDateTime = Instant.now().toString();
        // Gọi repository để lấy danh sách coupon còn hạn
        return couponRepo.findAllValidCoupons(currentDateTime);
    }

    //Create a new coupon with all information
    public void createCoupon(Coupon coupon) {
        couponRepo.save(coupon);
    }


}
