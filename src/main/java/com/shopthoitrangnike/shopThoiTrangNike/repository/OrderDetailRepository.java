package com.shopthoitrangnike.shopThoiTrangNike.repository;

import com.shopthoitrangnike.shopThoiTrangNike.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
