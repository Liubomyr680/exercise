package com.example.demo.util;

import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderCleanupTask {
    private final OrderService orderService;

    @Autowired
    public OrderCleanupTask(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(fixedRate = 600000) // Run every 10 minutes
    public void cleanupNotPaidOrders() {
        // Retrieve and delete not paid orders older than 10 minutes
        orderService.cleanupNotPaidOrders();
    }
}
