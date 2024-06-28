package com.shopthoitrangnike.shopThoiTrangNike.controller;



import com.shopthoitrangnike.shopThoiTrangNike.model.Category;
import com.shopthoitrangnike.shopThoiTrangNike.model.Style;
import com.shopthoitrangnike.shopThoiTrangNike.service.StyleService;
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
public class StyleController {
    @Autowired
    private final StyleService styleService;
    @GetMapping("/styles/add")
    public String showAddForm(Model model) {
        model.addAttribute("style", new Style());
        return "/styles/add-style";
    }
    @PostMapping("/styles/add")
    public String addStyle(@Valid Style style, BindingResult result) {
        if (result.hasErrors()) {
            return "/styles/add-style";
        }
        styleService.addStyle(style);
        return "redirect:/styles";
    }
    // Hiển thị danh sách danh mục
    @GetMapping("/styles")
    public String listStyles(Model model) {
        List<Style> styles = styleService.getAllStyles();
        model.addAttribute("styles", styles);
        return "/styles/styles-list";
    }
    // GET request to show category edit form
    @GetMapping("/styles/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Style style = styleService.getStyleById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid style Id:"
                        + id));
        model.addAttribute("style", style);
        return "/styles/update-style";
    }
    // POST request to update category
    @PostMapping("/styles/update/{id}")
    public String updateStyle(@PathVariable("id") Long id, @Valid Style style,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            style.setId(id);
            return "/styles/update-style";
        }
        styleService.updateStyle(style);
        model.addAttribute("styles", styleService.getAllStyles());
        return "redirect:/styles";
    }
    // GET request for deleting category
    @GetMapping("/styles/delete/{id}")
    public String deleteStyle(@PathVariable("id") Long id, Model model) {
        Style style = styleService.getStyleById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid style Id:"
                        + id));
        styleService.deleteStyleById(id);
        model.addAttribute("styles", styleService.getAllStyles());
        return "redirect:/styles";
    }
}
