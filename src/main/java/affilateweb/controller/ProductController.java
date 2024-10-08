package affilateweb.controller;

import affilateweb.dto.ProductDTO;
import affilateweb.model.entities.Product;
import affilateweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/api/products")
    public ResponseEntity<?> getAllProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        if (page == null || size == null) {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        } else {
            Page<Product> products = productService.getAllProducts(page, size);
            return ResponseEntity.ok(products);
        }
    }

    @GetMapping("/api/product/detail")
    public ResponseEntity<?> getProductById(@RequestParam int id) {
        ProductDTO product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.badRequest().body("Product not found");
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping("/api/product/search_name")
    public ResponseEntity<?> searchProducts(@RequestParam String input) {
        List<Product> products = productService.searchProducts(input);
        return ResponseEntity.ok(products);
    }
}
