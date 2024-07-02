package com.shopthoitrangnike.shopThoiTrangNike.controller;

import com.shopthoitrangnike.shopThoiTrangNike.service.CartService;
import com.shopthoitrangnike.shopThoiTrangNike.model.Product;
import com.shopthoitrangnike.shopThoiTrangNike.model.ProductType;
import com.shopthoitrangnike.shopThoiTrangNike.service.CategoryService;
import com.shopthoitrangnike.shopThoiTrangNike.service.ProductService;
import com.shopthoitrangnike.shopThoiTrangNike.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService; // Đảm bảo bạn đã inject CategoryService
    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "home/index";
    }
    @PostMapping("/products/buy/{id}")
    public String buyNow(@PathVariable("id") Long productId, @RequestParam("quantity") int quantity) {
        cartService.addToCart(productId, quantity);
        return "redirect:/checkout";
    }
}