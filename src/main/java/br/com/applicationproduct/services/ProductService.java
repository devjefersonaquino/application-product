package br.com.applicationproduct.services;

import br.com.applicationproduct.entity.Product;
import br.com.applicationproduct.exceptionhandler.ProductNotFoundException;
import br.com.applicationproduct.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
        product.setCreateDate(LocalDateTime.now());
        return productRepository.save(product);
    }

    public List<Product> listAllProducts(){
        return productRepository.findAll();
    }

    public Product findById (Long id){
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ProductNotFoundException());
    }

    public Product updateProducts(Product product, Long id){
        findById(id);
        Product updateProduct = productRepository.getById(id);
            BeanUtils.copyProperties(product, updateProduct, "id");
            updateProduct.setCreateDate(LocalDateTime.now());
        return productRepository.save(updateProduct);
    }

    public void deleteProduct(Long id){
        findById(id);
        productRepository.deleteById(id);
    }
}
