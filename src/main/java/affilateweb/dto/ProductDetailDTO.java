package affilateweb.dto;

import affilateweb.model.entities.Coupon;
import affilateweb.model.entities.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDetailDTO extends Product {
    private boolean isSaved;
    private List<HistoryPriceDTO> historyPrice;
    private List<Coupon> vouchersFound;
}
