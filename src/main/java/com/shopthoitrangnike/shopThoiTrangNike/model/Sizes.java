package com.shopthoitrangnike.shopThoiTrangNike.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sizes")
public class Sizes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Sizes là bắt buộc")
    private String sizes;
}
