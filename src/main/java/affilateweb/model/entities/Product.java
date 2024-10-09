package affilateweb.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "Product")
@Data
public class Product {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
    private String name;
    private String image;
    @Column(name = "aff_link")
    private String affLink;
    @Column(name = "current_price")
    private BigDecimal currentPrice;
    @ManyToOne
    @JoinColumn(name = "product_type")
    private ProductType productType;
    @Column(name = "is_official_shop")
    private Boolean isOfficialShop;
    @Column(name = "rating_avg")
    private BigDecimal ratingAvg;
    private Integer sold;
    @Column(name = "rating_count")
    private Integer ratingCount;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
