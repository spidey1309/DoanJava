package com.shopthoitrangnike.shopThoiTrangNike.service;

import com.shopthoitrangnike.shopThoiTrangNike.model.Style;
import com.shopthoitrangnike.shopThoiTrangNike.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StyleService {
    @Autowired
    private StyleRepository styleRepository;

    List<Style> getAllStyle() {
        return styleRepository.findAll();
    }

    public Optional<Style> getStyleById(Long id) {
        return styleRepository.findById(id);
    }

    public void addStyle(Style style){
        styleRepository.save(style);
    }

    public void updateStyle(Style style) {
        styleRepository.save(style);
    }

    public void deleteStyle(Long id){
        styleRepository.deleteById(id);
    }
}
