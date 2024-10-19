package affilateweb.dto;

import affilateweb.model.entities.ProductType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    //toi can id, name, image, currentPrice, ratingAvg, productType, affLink in product
    private Long id;
    private String name;
    private String image;
    private BigDecimal currentPrice;
    private BigDecimal ratingAvg;
    private ProductType productType;
    private String affLink;
    private Boolean saved;
}
