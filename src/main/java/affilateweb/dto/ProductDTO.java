package affilateweb.dto;

import affilateweb.model.entities.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class ProductDTO{
    private Product product;
    private boolean isSaved;
    private List<HistoryPriceDTO> historyPrice;
}
