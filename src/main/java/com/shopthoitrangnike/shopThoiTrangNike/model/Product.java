package com.shopthoitrangnike.shopThoiTrangNike.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = 50, message = "Tên sản phẩm không được vượt quá 50 ký tự")
    private String name;

    @NotNull(message = "Giá sản phẩm không được để trống")
    @Min(value = 1, message = "Giá sản phẩm phải là số nguyên dương lớn hơn 0")
    @Max(value = 50000000, message = "Giá sản phẩm phải nhỏ hơn hoặc bằng 50 000 000")
    private int price;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Size sản phẩm chỉ chấp nhận chữ cái và khoảng trắng")
    private String size;

    private String description;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String imageUrl;



}
