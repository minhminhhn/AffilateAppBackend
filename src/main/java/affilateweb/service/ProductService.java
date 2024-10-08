package affilateweb.service;

import affilateweb.dto.HistoryPriceDTO;
import affilateweb.dto.ProductDTO;
import affilateweb.model.entities.HistoryPrice;
import affilateweb.model.entities.Product;
import affilateweb.repository.HistoryPriceRepo;
import affilateweb.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SaveProductService saveProductService;
    @Autowired
    private HistoryPriceRepo historyPriceRepo;



    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Page<Product> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepo.findAll(pageable);
    }

    public ProductDTO getProductById(int id) {
        Product product = productRepo.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct(product);
        List<Product> saveProduct = saveProductService.getSaveProductByUserId();
        productDTO.setSaved(saveProduct.contains(product));
        List<HistoryPrice> historyPrice = historyPriceRepo.findByProductId(id);
        List<HistoryPriceDTO> historyPriceDTO = new ArrayList<>();
        for (HistoryPrice historyPrice1 : historyPrice) {
            HistoryPriceDTO historyPriceDTO1 = new HistoryPriceDTO();
            historyPriceDTO1.setPrice(historyPrice1.getPrice());
            historyPriceDTO1.setDate(String.valueOf(historyPrice1.getDateUpdate()).substring(0, 10));
            historyPriceDTO.add(historyPriceDTO1);
        }
        productDTO.setHistoryPrice(historyPriceDTO);
        return productDTO;
    }

    public List<Product> searchProducts(String input) {
        return productRepo.searchProducts(input);
    }

}
