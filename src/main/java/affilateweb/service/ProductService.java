package affilateweb.service;

import affilateweb.dto.HistoryPriceDTO;
import affilateweb.dto.ProductDTO;
import affilateweb.model.entities.Coupon;
import affilateweb.model.entities.HistoryPrice;
import affilateweb.model.entities.Product;
import affilateweb.model.entities.ProductTypeCouponCategory;
import affilateweb.repository.HistoryPriceRepo;
import affilateweb.repository.ProductRepo;
import affilateweb.repository.ProductTypeCouponCategoryRepo;
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

    @Autowired
    private CouponService couponService;
    @Autowired
    private ProductTypeCouponCategoryRepo productTypeCouponCategoryRepo;


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
        productDTO.setId(product.getId());
        productDTO.setShop(product.getShop());
        productDTO.setName(product.getName());
        productDTO.setImage(product.getImage());
        productDTO.setAffLink(product.getAffLink());
        productDTO.setCurrentPrice(product.getCurrentPrice());
        productDTO.setProductType(product.getProductType());
        productDTO.setIsOfficialShop(product.getIsOfficialShop());
        productDTO.setRatingAvg(product.getRatingAvg());
        productDTO.setSold(product.getSold());
        productDTO.setRatingCount(product.getRatingCount());
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setUpdatedAt(product.getUpdatedAt());

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

        List<ProductTypeCouponCategory> productTypeCouponCategories = productTypeCouponCategoryRepo.findAllByProductTypeId(product.getProductType().getId());
        List<Coupon> vouchersFound = new ArrayList<>();
        for (ProductTypeCouponCategory productTypeCouponCategory : productTypeCouponCategories) {
            List<Coupon> vouchersFoundByCategory = couponService.getAllValidCouponsByCategoryId(productTypeCouponCategory.getCouponCategory().getId());
            vouchersFound.addAll(vouchersFoundByCategory);
        }
        productDTO.setVouchersFound(vouchersFound);

        return productDTO;
    }

    public List<Product> searchProducts(String input) {
        return productRepo.searchProducts(input);
    }
}
