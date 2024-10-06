package affilateweb.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HistoryPriceDTO {
    private String date;
    private BigDecimal price;
}
