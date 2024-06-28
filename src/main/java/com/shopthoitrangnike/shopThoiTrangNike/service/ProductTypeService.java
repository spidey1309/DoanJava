package com.shopthoitrangnike.shopThoiTrangNike.service;

import com.shopthoitrangnike.shopThoiTrangNike.model.ProductType;
import com.shopthoitrangnike.shopThoiTrangNike.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    public List<ProductType> getAllProductType(){
        return productTypeRepository.findAll();
    }

}
