package com.shopthoitrangnike.shopThoiTrangNike.service;

import com.shopthoitrangnike.shopThoiTrangNike.model.Product;
import com.shopthoitrangnike.shopThoiTrangNike.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    List<Product> getAllProduct(){ return productRepository.findAll();}
    public Optional<Product> getProductById(Long id){ return productRepository.findById(id);}
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }
}
