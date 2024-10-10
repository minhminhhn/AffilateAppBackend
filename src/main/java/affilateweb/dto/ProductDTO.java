package affilateweb.dto;

import affilateweb.model.entities.Coupon;
import affilateweb.model.entities.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO extends Product {
    private boolean isSaved;
    private List<HistoryPriceDTO> historyPrice;
    private List<Coupon> vouchersFound;
}
