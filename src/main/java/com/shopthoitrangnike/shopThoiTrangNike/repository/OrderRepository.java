package com.shopthoitrangnike.shopThoiTrangNike.repository;

import com.shopthoitrangnike.shopThoiTrangNike.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
