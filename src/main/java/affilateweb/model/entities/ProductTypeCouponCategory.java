package affilateweb.model.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product_type_coupon_category")
@Data
public class ProductTypeCouponCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "coupon_category_id")
    private CouponCategory couponCategory;

}