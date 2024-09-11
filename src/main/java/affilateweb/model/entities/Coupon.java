package affilateweb.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "Coupon")
@Data
public class Coupon {
    @Id
    @Column(name = "_id")
    private String id;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Account admin;
    private String platform;
    @Column(name = "aff_link")
    private String affLink;
    @Column(name = "target_user")
    private String targetUser;
    private String status;
    @Column(name = "start_at")
    private Long startAt;
    @Column(name = "expired_at")
    private Long expiredAt;
    @Column(name = "coupon_type")
    private String couponType;
    @Column(name = "coupon_code")
    private String couponCode;
    @Column(name = "coupon_category")
    private String couponCategory;
    @ManyToOne
    @JoinColumn(name = "coupon_category_id")
    private CouponCategory couponCategoryEntity;
    @Column(name = "discount_amount")
    private BigDecimal discountAmount;
    @Column(name = "discount_reward")
    private BigDecimal discountReward;
    @Column(name = "total_click")
    private Integer totalClick;
    @Column(name = "min_spend")
    private BigDecimal minSpend;
    @Column(name = "percentage_used")
    private BigDecimal percentageUsed;
    @Column(name = "voucher_shop")
    private String voucherShop;
    @Lob
    private String note;
    private Timestamp updatedAt;
    private Timestamp createdAt;
    @Column(name = "is_in_wallet")
    private Boolean isInWallet;
}
