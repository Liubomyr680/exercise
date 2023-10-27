package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.repository.OrdersRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class OrderServiceTest {
    @InjectMocks
    public OrderService orderService;

    @Mock
    public OrdersRepo orderRepository;

    @Test
    public void testPlaceOrder() {
        // Create a sample Order object
        Order order = new Order();
        order.setPaid(true); // Initially set to true

        when(orderRepository.save(order)).thenReturn(order);

        // Call the method to be tested
        Order result = orderService.placeOrder(order);

        // Assertions
        verify(orderRepository, times(1)).save(order); // Verify that save was called once
        assertFalse(result.isPaid()); // Ensure that the order is marked as unpaid
    }

    @Test
    public void testCleanupNotPaidOrders() {
        // Create a sample list of not paid orders
        List<Order> notPaidOrders = new ArrayList<>();
        Order order1 = new Order();
        Order order2 = new Order();
        notPaidOrders.add(order1);
        notPaidOrders.add(order2);

        // Mock the behavior of LocalDateTime
        LocalDateTime tenMinutesAgo = LocalDateTime.now().minusMinutes(10);

        // Mock the behavior of the findNotPaidOrdersOlderThan method
        when(orderRepository.findNotPaidOrdersOlderThan(tenMinutesAgo)).thenReturn(notPaidOrders);

        // Call the method to be tested
        orderService.cleanupNotPaidOrders();

        // Verify that deleteAll was called with the list of not paid orders
        verify(orderRepository, times(1)).deleteAll(notPaidOrders);
    }
}
