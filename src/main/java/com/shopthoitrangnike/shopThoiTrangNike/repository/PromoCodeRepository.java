package com.shopthoitrangnike.shopThoiTrangNike.repository;

import com.shopthoitrangnike.shopThoiTrangNike.model.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {
    Optional<PromoCode> findByCodeAndActiveTrue(String code);
    PromoCode findByCode(String code);
}