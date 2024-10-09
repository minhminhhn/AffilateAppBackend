package affilateweb.controller;

import affilateweb.model.entities.Coupon;
import affilateweb.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/get_valid")
    public ResponseEntity<?> getAllValidCoupons() {
        List<Coupon> coupons = couponService.getAllValidCoupons();
        return ResponseEntity.ok(coupons);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllCoupons() {
        List<Coupon> coupons = couponService.getAllCoupons();
        return ResponseEntity.ok(coupons);
    }
}
