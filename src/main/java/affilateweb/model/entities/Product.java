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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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

    public Product(Integer id, Shop shop, String name, String image, String affLink, BigDecimal currentPrice, ProductType productType, Boolean isOfficialShop, BigDecimal ratingAvg, Integer sold, Integer ratingCount) {
        this.id = id;
        this.shop = shop;
        this.name = name;
        this.image = image;
        this.affLink = affLink;
        this.currentPrice = currentPrice;
        this.productType = productType;
        this.isOfficialShop = isOfficialShop;
        this.ratingAvg = ratingAvg;
        this.sold = sold;
        this.ratingCount = ratingCount;
    }
}
