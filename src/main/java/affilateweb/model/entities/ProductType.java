package affilateweb.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Product_Type")
@Data
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String category;
}