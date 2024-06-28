package com.shopthoitrangnike.shopThoiTrangNike.repository;
import com.shopthoitrangnike.shopThoiTrangNike.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
