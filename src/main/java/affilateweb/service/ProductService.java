package affilateweb.service;

import affilateweb.dto.HistoryPriceDTO;
import affilateweb.dto.ProductDTO;
import affilateweb.dto.ProductDetailDTO;
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


public List<ProductDTO> getAllProducts() {
    List<Product> products = productRepo.findAll();
    List<ProductDTO> productDTOs = new ArrayList<>();
    for (Product product : products) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setImage(product.getImage());
        productDTO.setCurrentPrice(product.getCurrentPrice());
        productDTO.setRatingAvg(product.getRatingAvg());
        productDTO.setProductType(product.getProductType());
        productDTO.setAffLink(product.getAffLink());
        productDTO.setSaved(saveProductService.getSaveProductByUserId().contains(product));
        productDTOs.add(productDTO);
    }
    return productDTOs;
}

    public Page<ProductDTO> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepo.findAll(pageable);
        return products.map(product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setImage(product.getImage());
            productDTO.setCurrentPrice(product.getCurrentPrice());
            productDTO.setRatingAvg(product.getRatingAvg());
            productDTO.setProductType(product.getProductType());
            productDTO.setAffLink(product.getAffLink());
            productDTO.setSaved(saveProductService.getSaveProductByUserId().contains(product));
            return productDTO;
        });
    }

    public ProductDetailDTO getProductById(int id) {
        Product product = productRepo.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setId(product.getId());
        productDetailDTO.setShop(product.getShop());
        productDetailDTO.setName(product.getName());
        productDetailDTO.setImage(product.getImage());
        productDetailDTO.setAffLink(product.getAffLink());
        productDetailDTO.setCurrentPrice(product.getCurrentPrice());
        productDetailDTO.setProductType(product.getProductType());
        productDetailDTO.setIsOfficialShop(product.getIsOfficialShop());
        productDetailDTO.setRatingAvg(product.getRatingAvg());
        productDetailDTO.setSold(product.getSold());
        productDetailDTO.setRatingCount(product.getRatingCount());
        productDetailDTO.setCreatedAt(product.getCreatedAt());
        productDetailDTO.setUpdatedAt(product.getUpdatedAt());

        List<Product> saveProduct = saveProductService.getSaveProductByUserId();
        productDetailDTO.setSaved(saveProduct.contains(product));

        List<HistoryPrice> historyPrice = historyPriceRepo.findByProductId(id);
        List<HistoryPriceDTO> historyPriceDTO = new ArrayList<>();
        for (HistoryPrice historyPrice1 : historyPrice) {
            HistoryPriceDTO historyPriceDTO1 = new HistoryPriceDTO();
            historyPriceDTO1.setPrice(historyPrice1.getPrice());
            historyPriceDTO1.setDate(String.valueOf(historyPrice1.getDateUpdate()).substring(0, 10));
            historyPriceDTO.add(historyPriceDTO1);
        }
        productDetailDTO.setHistoryPrice(historyPriceDTO);

        List<ProductTypeCouponCategory> productTypeCouponCategories = productTypeCouponCategoryRepo.findAllByProductTypeId(product.getProductType().getId());
        List<Coupon> vouchersFound = new ArrayList<>();
        for (ProductTypeCouponCategory productTypeCouponCategory : productTypeCouponCategories) {
            List<Coupon> vouchersFoundByCategory = couponService.getAllValidCouponsByCategoryId(productTypeCouponCategory.getCouponCategory().getId());
            vouchersFound.addAll(vouchersFoundByCategory);
        }
        productDetailDTO.setVouchersFound(vouchersFound);

        return productDetailDTO;
    }

    public List<Product> searchProducts(String input) {
        return productRepo.searchProducts(input);
    }
}
