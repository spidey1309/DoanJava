package com.shopthoitrangnike.shopThoiTrangNike.controller;

import com.shopthoitrangnike.shopThoiTrangNike.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalAmount", cartService.calculateTotalAmount());
        return "cart/cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity, @RequestParam(required = false) boolean checkout) {
        cartService.addToCart(productId, quantity);
        if (checkout) {
            return "redirect:/cart/checkout";
        }
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateItem(@RequestParam Long productId, @RequestParam int quantity) {
        cartService.updateItem(productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam Long productId) {
        cartService.removeItem(productId);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String showCheckout(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalAmount", cartService.calculateTotalAmount());
        return "cart/checkout";
    }
}