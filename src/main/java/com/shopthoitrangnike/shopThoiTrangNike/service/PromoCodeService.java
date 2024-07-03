package com.shopthoitrangnike.shopThoiTrangNike.service;

import com.shopthoitrangnike.shopThoiTrangNike.model.PromoCode;
import com.shopthoitrangnike.shopThoiTrangNike.repository.PromoCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PromoCodeService {
    @Autowired
    private PromoCodeRepository promoCodeRepository;

    public Optional<PromoCode> validatePromoCode(String code, double orderAmount) {
        Optional<PromoCode> promoCodeOpt = promoCodeRepository.findByCodeAndActiveTrue(code);
        if (promoCodeOpt.isPresent()) {
            PromoCode promoCode = promoCodeOpt.get();
            if (promoCode.getStartDate().isBefore(LocalDate.now()) && promoCode.getEndDate().isAfter(LocalDate.now())) {
                return promoCodeOpt;
            }
        }
        return Optional.empty();
    }

    public List<PromoCode> getAllPromoCodes() {
        return promoCodeRepository.findAll();
    }

    public PromoCode findByCode(String code) {
        return promoCodeRepository.findByCode(code);
    }


    public void save(PromoCode promoCode) {
        promoCodeRepository.save(promoCode);
    }
}