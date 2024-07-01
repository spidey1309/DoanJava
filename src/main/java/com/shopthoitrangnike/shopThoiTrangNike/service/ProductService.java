package com.shopthoitrangnike.shopThoiTrangNike.service;

import com.shopthoitrangnike.shopThoiTrangNike.model.Product;
import com.shopthoitrangnike.shopThoiTrangNike.repository.ProductRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    // Retrieve all products from the database
    // Lấy danh sách tất cả các sản phẩm
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    // Lấy sản phẩm theo ID
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    // Add a new product to the database
    // Thêm sản phẩm mới
    @Transactional
    public void addProduct(Product product) {
        productRepository.save(product);
    }
    // Update an existing product
    // Cập nhật sản phẩm hiện có
    @Transactional
    public void updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setSize(updatedProduct.getSize());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setProductType(updatedProduct.getProductType());
            if (updatedProduct.getImage() != null && !updatedProduct.getImage().isEmpty()) {
                existingProduct.setImage(updatedProduct.getImage());
            }
            productRepository.save(existingProduct);
        }
    }
    // Delete a product by its id
    // Xóa sản phẩm theo ID
    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
    public List<Product> searchProducts(String keyword) {
        return productRepository.searchByKeyword(keyword);
    }
}
