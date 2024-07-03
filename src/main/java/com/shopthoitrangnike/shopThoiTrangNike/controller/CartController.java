package com.shopthoitrangnike.shopThoiTrangNike.controller;

import com.shopthoitrangnike.shopThoiTrangNike.model.PromoCode;
import com.shopthoitrangnike.shopThoiTrangNike.service.CartService;
import com.shopthoitrangnike.shopThoiTrangNike.service.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private PromoCodeService promoCodeService;
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
    @PostMapping("/checkout/remove")
    public String removechekout(@RequestParam Long productId) {
        cartService.removeItem(productId);
        return "redirect:/cart/checkout";
    }
    @PostMapping("/checkout/update")
    public String checkoutupdateItem(@RequestParam Long productId, @RequestParam int quantity) {
        cartService.updateItem(productId, quantity);
        return "redirect:/cart/checkout";
    }
    @PostMapping("/apply-discount")
    public String applyDiscount(@RequestParam("promoCode") String promoCode, Model model) {
        Optional<PromoCode> promoCodeOptional = promoCodeService.validatePromoCode(promoCode, cartService.calculateTotalAmount());
        if (promoCodeOptional.isPresent()) {
            PromoCode validPromoCode = promoCodeOptional.get();
            double discountPercentage = validPromoCode.getDiscount();
            double totalAmount = cartService.calculateTotalAmount();
            double discountedAmount = totalAmount - (totalAmount * discountPercentage / 100);
            model.addAttribute("discountedAmount", discountedAmount);
            model.addAttribute("discountPercentage", discountPercentage);
        } else {
            model.addAttribute("discountError", "Mã giảm giá không hợp lệ hoặc đã hết hạn.");
        }
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalAmount", cartService.calculateTotalAmount());
        return "cart/checkout";
    }
}