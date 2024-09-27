package affilateweb.service;

import affilateweb.model.entities.Product;
import affilateweb.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;



    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Page<Product> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepo.findAll(pageable);
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(null);
    }

    public List<Product> searchProducts(String input) {
        return productRepo.searchProducts(input);
    }

}
