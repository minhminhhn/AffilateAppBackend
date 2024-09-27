package affilateweb.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "Save_Product")
@Data
public class SaveProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "price")
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Timestamp createdAt;
}
