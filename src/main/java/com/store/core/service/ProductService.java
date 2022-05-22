package com.store.core.service;

import com.store.core.domain.Product;
import com.store.core.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Product saveProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public Product updateProduct(Integer id, Product updateSourceProduct) {
        return productRepository.save(updateSourceProduct);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
