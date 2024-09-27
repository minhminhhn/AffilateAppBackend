package affilateweb.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "coupon") // Table name should be in lowercase to match the database
@Data
public class Coupon {
    @Id
    @Column(name = "_id", length = 255, nullable = false)
    private String id;

    @ManyToOne
    @JoinColumn(name = "admin_id", insertable = false, updatable = false)
    private Account admin;

    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "platform", length = 50)
    private String platform;

    @Column(name = "aff_link")
    private String affLink;

    @Column(name = "target_user", length = 50)
    private String targetUser;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "start_at")
    private String startAt;

    @Column(name = "expired_at")
    private String expiredAt;

    @Column(name = "coupon_type", length = 50)
    private String couponType;

    @Column(name = "coupon_code", length = 50)
    private String couponCode;

    @ManyToOne
    @JoinColumn(name = "coupon_category_id", insertable = false, updatable = false)
    private CouponCategory couponCategoryEntity;

    @Column(name = "coupon_category_id")
    private Integer couponCategoryId;

    @Column(name = "discount_amount", precision = 38, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "discount_reward", precision = 38, scale = 2)
    private BigDecimal discountReward;

    @Column(name = "total_click")
    private Integer totalClick;

    @Column(name = "min_spend", precision = 38, scale = 2)
    private BigDecimal minSpend;

    @Column(name = "percentage_used", precision = 38, scale = 2)
    private BigDecimal percentageUsed;

    @Column(name = "voucher_shop", length = 255)
    private String voucherShop;

    @Column(name = "note")
    private String note;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "is_in_wallet")
    private Boolean isInWallet;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "long_description")
    private String longDescription;
}
