package affilateweb.controller;

import affilateweb.service.SaveProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/save_product")
public class SaveProductController {

    @Autowired
    private SaveProductService saveProductService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@RequestParam int productId) {
        try {
            saveProductService.saveProduct(productId);
            return ResponseEntity.ok("Product saved successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteSaveProduct(@RequestParam int productId) {
        try {
            saveProductService.deleteSaveProduct(productId);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/get_save_product")
    public ResponseEntity<?> getSaveProduct() {
        return ResponseEntity.ok(saveProductService.getSaveProductByUserId());
    }
}