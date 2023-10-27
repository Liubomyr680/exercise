package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.repository.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrdersRepo orderRepository;

    @Autowired
    public OrderService(OrdersRepo orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order placeOrder(Order order) {
        order.setCreationTimestamp(LocalDateTime.now());
        order.setPaid(false);
        return orderRepository.save(order);
    }

    public void markOrderAsPaid(Long orderId) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        existingOrder.setPaid(true);
        orderRepository.save(existingOrder);
    }

    public void cleanupNotPaidOrders() {
        LocalDateTime tenMinutesAgo = LocalDateTime.now().minusMinutes(10);
        List<Order> notPaidOrders = orderRepository.findNotPaidOrdersOlderThan(tenMinutesAgo);

        if(!notPaidOrders.isEmpty()){
            orderRepository.deleteAll(notPaidOrders);
        }
    }
}
