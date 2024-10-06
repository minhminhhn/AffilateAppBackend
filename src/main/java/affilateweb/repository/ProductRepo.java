package affilateweb.repository;

import affilateweb.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT *, MATCH(p.name) AGAINST (CONCAT('\"', ?1, '\"') IN BOOLEAN MODE) AS relevance_score " +
            "FROM Product p " +
            "WHERE MATCH(p.name) AGAINST (CONCAT('\"', ?1, '\"') IN BOOLEAN MODE) " +
            "ORDER BY relevance_score DESC", nativeQuery = true)
    List<Product> searchProducts(String input);

}