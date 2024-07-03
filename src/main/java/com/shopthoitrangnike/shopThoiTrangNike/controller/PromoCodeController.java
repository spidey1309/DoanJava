package com.shopthoitrangnike.shopThoiTrangNike.controller;

import com.shopthoitrangnike.shopThoiTrangNike.model.PromoCode;
import com.shopthoitrangnike.shopThoiTrangNike.repository.PromoCodeRepository;
import com.shopthoitrangnike.shopThoiTrangNike.service.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/promo")
public class PromoCodeController {
    @Autowired
    private PromoCodeService promoCodeService;


    @PostMapping("/apply")
    public ResponseEntity<?> applyPromoCode(@RequestParam String code, @RequestParam double orderAmount) {
        Optional<PromoCode> promoCode = promoCodeService.validatePromoCode(code, orderAmount);
        if (promoCode.isPresent()) {
            return ResponseEntity.ok(promoCode.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid promo code");
        }
    }


}