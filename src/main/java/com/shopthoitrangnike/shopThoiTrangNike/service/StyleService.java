package com.shopthoitrangnike.shopThoiTrangNike.service;

import com.shopthoitrangnike.shopThoiTrangNike.model.Style;
import com.shopthoitrangnike.shopThoiTrangNike.repository.StyleRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StyleService {
    private final StyleRepository styleRepository;
    /**
     * Retrieve all categories from the database.
     * @return a list of categories
     */
    public List<Style> getAllStyles() {
        return styleRepository.findAll();
    }
    /**
     * Retrieve a category by its id.
     * @param id the id of the category to retrieve
     * @return an Optional containing the found category or empty if not found
     */
    public Optional<Style> getStyleById(Long id) {
        return styleRepository.findById(id);
    }
    /**
     * Add a new category to the database.
     * @param style the category to add
     */
    public void addStyle(Style style) {
        styleRepository.save(style);
    }
    /**
     * Update an existing category.
     * @param style the category with updated information
     */
    public void updateStyle(@NotNull Style style) {
        Style existingStyle = styleRepository.findById(style.getId())
                .orElseThrow(() -> new IllegalStateException("Style with ID " +
                        style.getId() + " does not exist."));
        existingStyle.setName(style.getName());
        styleRepository.save(existingStyle);
    }


    public void deleteStyleById(Long id) {
        if (!styleRepository.existsById(id)) {
            throw new IllegalStateException("Style with ID " + id + " does not * exist.");
        }
        styleRepository.deleteById(id);
    }

}