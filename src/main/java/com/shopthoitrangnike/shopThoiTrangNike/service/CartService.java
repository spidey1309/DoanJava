package com.shopthoitrangnike.shopThoiTrangNike.service;


import com.shopthoitrangnike.shopThoiTrangNike.model.CartItem;
import com.shopthoitrangnike.shopThoiTrangNike.model.Product;
import com.shopthoitrangnike.shopThoiTrangNike.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class CartService {

    private List<CartItem> cartItems = new ArrayList<>();

    @Autowired
    private ProductRepository productRepository;

    public void addToCart(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm: " + productId));

        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        cartItems.add(new CartItem(product, quantity));
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void removeFromCart(Long productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public void clearCart() {
        cartItems.clear();
    }

    public double calculateTotalAmount() {
        return cartItems.stream()
                .mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice())
                .sum();
    }

    public void updateItem(Long productId, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(quantity);
                break;
            }
        }
    }

    public void removeItem(Long productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }
}