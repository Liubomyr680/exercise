package com.example.demo.repository;

import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Orders o WHERE o.isPaid = false AND o.creationTimestamp <= :timestamp")
    List<Order> findNotPaidOrdersOlderThan(LocalDateTime timestamp);
}
