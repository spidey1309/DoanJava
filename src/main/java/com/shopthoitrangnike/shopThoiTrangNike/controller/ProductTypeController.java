package com.shopthoitrangnike.shopThoiTrangNike.controller;



import com.shopthoitrangnike.shopThoiTrangNike.model.ProductType;
import com.shopthoitrangnike.shopThoiTrangNike.service.ProductTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductTypeController {
    @Autowired
    private final ProductTypeService productTypeService;
    @GetMapping("/styles/add")
    public String showAddForm(Model model) {
        model.addAttribute("style", new ProductType());
        return "/styles/add-style";
    }
    @PostMapping("/styles/add")
    public String addStyle(@Valid ProductType productType, BindingResult result) {
        if (result.hasErrors()) {
            return "/styles/add-style";
        }
        productTypeService.addStyle(productType);
        return "redirect:/styles";
    }
    // Hiển thị danh sách danh mục
    @GetMapping("/styles")
    public String listStyles(Model model) {
        List<ProductType> productTypes = productTypeService.getAllStyles();
        model.addAttribute("styles", productTypes);
        return "/styles/styles-list";
    }
    // GET request to show category edit form
    @GetMapping("/styles/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        ProductType productType = productTypeService.getStyleById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid style Id:"
                        + id));
        model.addAttribute("style", productType);
        return "/styles/update-style";
    }
    // POST request to update category
    @PostMapping("/styles/update/{id}")
    public String updateStyle(@PathVariable("id") Long id, @Valid ProductType productType,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            productType.setId(id);
            return "/styles/update-style";
        }
        productTypeService.updateStyle(productType);
        model.addAttribute("styles", productTypeService.getAllStyles());
        return "redirect:/styles";
    }
    // GET request for deleting category
    @GetMapping("/styles/delete/{id}")
    public String deleteStyle(@PathVariable("id") Long id, Model model) {
        ProductType productType = productTypeService.getStyleById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid style Id:"
                        + id));
        productTypeService.deleteStyleById(id);
        model.addAttribute("styles", productTypeService.getAllStyles());
        return "redirect:/styles";
    }
}
