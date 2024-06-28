package com.shopthoitrangnike.shopThoiTrangNike.service;


import com.shopthoitrangnike.shopThoiTrangNike.model.Category;
import com.shopthoitrangnike.shopThoiTrangNike.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategorys() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
}
