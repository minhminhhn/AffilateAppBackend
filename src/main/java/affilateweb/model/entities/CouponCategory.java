package affilateweb.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Coupon_Category")
@Data
public class CouponCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_category_id")
    private Integer id;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "avatar_url")
    private String avatarUrl;
}
