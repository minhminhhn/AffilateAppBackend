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

        //get list ProductTypeCouponCategory found for product type
        List<ProductTypeCouponCategory> productTypeCouponCategories = productTypeCouponCategoryRepo.findAllByProductTypeId(product.getProductType().getId());

        List<Coupon> vouchersFound = new ArrayList<>();
        //for each list ProductTypeCouponCategory found, get list voucher found for category
        for (ProductTypeCouponCategory productTypeCouponCategory : productTypeCouponCategories) {
            List<Coupon> vouchersFoundByCatrgory = couponService.getAllValidCouponsByCategoryId(productTypeCouponCategory.getCouponCategory().getId());
            //save list to vouchersFound
            vouchersFound.addAll(vouchersFoundByCatrgory);
        }
        productDTO.setVouchersFound(vouchersFound);

        return productDTO;
    }

    public List<Product> searchProducts(String input) {
        return productRepo.searchProducts(input);
    }
}
