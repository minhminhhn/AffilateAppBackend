package affilateweb.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import affilateweb.model.entities.SaveProduct;

import java.util.List;

public interface SaveProductRepo extends JpaRepository<SaveProduct, Integer> {

    @Transactional
    void deleteByUser_IdAndProduct_Id(Integer userId, Integer productId);

    List<SaveProduct> findByUser_Id(Integer userId);

    SaveProduct findByUser_IdAndProduct_Id(Integer userId, Integer productId);
}