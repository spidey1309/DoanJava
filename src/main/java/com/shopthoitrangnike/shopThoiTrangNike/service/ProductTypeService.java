package com.shopthoitrangnike.shopThoiTrangNike.service;

import com.shopthoitrangnike.shopThoiTrangNike.model.ProductType;
import com.shopthoitrangnike.shopThoiTrangNike.repository.ProductTypeRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductTypeService {
    private final ProductTypeRepository productTypeRepository;
    /**
     * Retrieve all categories from the database.
     * @return a list of categories
     */
    public List<ProductType> getAllStyles() {
        return productTypeRepository.findAll();
    }
    /**
     * Retrieve a category by its id.
     * @param id the id of the category to retrieve
     * @return an Optional containing the found category or empty if not found
     */
    public Optional<ProductType> getStyleById(Long id) {
        return productTypeRepository.findById(id);
    }
    /**
     * Add a new category to the database.
     * @param productType the category to add
     */
    public void addStyle(ProductType productType) {
        productTypeRepository.save(productType);
    }
    /**
     * Update an existing category.
     * @param productType the category with updated information
     */
    public void updateStyle(@NotNull ProductType productType) {
        ProductType existingProductType = productTypeRepository.findById(productType.getId())
                .orElseThrow(() -> new IllegalStateException("Style with ID " +
                        productType.getId() + " does not exist."));
        existingProductType.setName(productType.getName());
        productTypeRepository.save(existingProductType);
    }


    public void deleteStyleById(Long id) {
        if (!productTypeRepository.existsById(id)) {
            throw new IllegalStateException("Style with ID " + id + " does not * exist.");
        }
        productTypeRepository.deleteById(id);
    }

}