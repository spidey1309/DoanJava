package com.shopthoitrangnike.shopThoiTrangNike.repository;

import com.shopthoitrangnike.shopThoiTrangNike.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
