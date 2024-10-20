package affilateweb.service;

import affilateweb.model.entities.Account;
import affilateweb.model.entities.Product;
import affilateweb.model.entities.SaveProduct;
import affilateweb.repository.ProductRepo;
import affilateweb.repository.SaveProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaveProductService {

    @Autowired
    private SaveProductRepo saveProductRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private AccountService accountService;

    public void saveProduct(int productId) {
        Optional<Product> productOptional = productRepo.findById(productId);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product not found");
        }

        //if product is already saved, retur error
        Optional<SaveProduct> saveProductOptional = Optional.ofNullable(
                saveProductRepo.findByUser_IdAndProduct_Id(accountService.getAccount().getId(), productId));
        if (saveProductOptional.isPresent()) {
            throw new RuntimeException("Product is already saved");
        }

        Product product = productOptional.get();
        SaveProduct saveProduct = new SaveProduct();
        saveProduct.setProduct(product);
        saveProduct.setUser(accountService.getAccount());
        saveProduct.setPrice(product.getCurrentPrice());
        saveProduct.setCreatedAt(Timestamp.from(Instant.now()));
        saveProductRepo.save(saveProduct);
    }

    public void deleteSaveProduct(int productId) {
        Optional<Product> productOptional = productRepo.findById(productId);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product not found");
        }

        saveProductRepo.deleteByUser_IdAndProduct_Id(accountService.getAccount().getId(), productId);
    }

    //get lits product by user id
    public List<Product> getSaveProductByUserId() {
        Account account = accountService.getAccount();
        if (account == null) {
            return new ArrayList<>();
        }
        List<Product> listProduct = new ArrayList<>();
        List<SaveProduct> listSaveProduct = saveProductRepo.findByUser_Id(account.getId());
        for (SaveProduct saveProduct : listSaveProduct) {
            listProduct.add(saveProduct.getProduct());
        }

        return listProduct;
    }
}