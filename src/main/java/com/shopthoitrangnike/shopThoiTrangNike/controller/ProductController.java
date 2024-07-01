package com.shopthoitrangnike.shopThoiTrangNike.controller;

import com.shopthoitrangnike.shopThoiTrangNike.model.Product;
import com.shopthoitrangnike.shopThoiTrangNike.model.ProductType;
import com.shopthoitrangnike.shopThoiTrangNike.service.CategoryService;
import com.shopthoitrangnike.shopThoiTrangNike.service.ProductService;
import com.shopthoitrangnike.shopThoiTrangNike.service.ProductTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private static String UPLOAD_DIR = "src/main/resources/static/images/";
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService; // Đảm bảo bạn đã inject CategoryService
    @Autowired
    private ProductTypeService productTypeService;
    // Display a list of all products
    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "/products/product-list";
    }

    // For adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories()); //Load categories
        List<ProductType> productTypes = productTypeService.getAllStyles();
        model.addAttribute("productTypes", productTypes);
        return "/products/add-product";
    }
    // Process the form for adding a new product
    @PostMapping("/add")
    public String addProduct(@Valid Product product, BindingResult result, @RequestParam("imagesFile") MultipartFile imagesFile) {
        if (result.hasErrors()) {
            return "/products/add-product";
        }
        if (!imagesFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(imagesFile.getOriginalFilename());
            product.setImage(fileName);

            // Lưu ảnh vào thư mục lưu trữ (ví dụ: trong thư mục resources/static/images/)
            String uploadDir = "src/main/resources/static/images/";
            try {
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                try (InputStream inputStream = imagesFile.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException("Could not save avatar image: " + e.getMessage());
                }
            } catch (IOException e) {
                throw new RuntimeException("Could not create upload directory: " + e.getMessage());
            }
        }
        productService.addProduct(product);
        return "redirect:/products";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("productTypes", productTypeService.getAllStyles());
        return "/products/update-product";
    }

    @PostMapping("/update")
    public String updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, @RequestParam("imagesFile") MultipartFile imagesFile) {
        if (result.hasErrors()) {
            return "/products/update-product";
        }

        if (!imagesFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(imagesFile.getOriginalFilename());
            product.setImage(fileName);

            String uploadDir = "src/main/resources/static/images/";
            try {
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                try (InputStream inputStream = imagesFile.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new RuntimeException("Could not save image file: " + e.getMessage());
                }
            } catch (IOException e) {
                throw new RuntimeException("Could not create upload directory: " + e.getMessage());
            }
        }
        productService.updateProduct(product.getId(), product);
        return "redirect:/products";
    }
    // For deleting an existing product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }
    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> searchResults = productService.searchProducts(keyword);
        model.addAttribute("products", searchResults);
        return "/products/product-list"; // Đảm bảo rằng bạn có file product-list.html để hiển thị kết quả
    }
}
