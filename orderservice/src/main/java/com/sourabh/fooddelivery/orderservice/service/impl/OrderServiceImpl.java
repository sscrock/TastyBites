package com.sourabh.fooddelivery.orderservice.service.impl;

import com.sourabh.fooddelivery.orderservice.dto.OrderRequestDto;
import com.sourabh.fooddelivery.orderservice.dto.OrderResponseDto;
import com.sourabh.fooddelivery.orderservice.entity.Order;
import com.sourabh.fooddelivery.orderservice.entity.OrderItem;
import com.sourabh.fooddelivery.orderservice.entity.OrderStatus;
import com.sourabh.fooddelivery.orderservice.mapper.OrderMapper;
import com.sourabh.fooddelivery.orderservice.repository.OrderItemRepository;
import com.sourabh.fooddelivery.orderservice.repository.OrderRepository;
import com.sourabh.fooddelivery.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    // ================= CREATE ORDER =================
    @Override
    public OrderResponseDto createOrder(OrderRequestDto requestDto) {

        Order order = OrderMapper.toEntity(requestDto);
        Order savedOrder = orderRepository.save(order);

        List<OrderItem> items = requestDto.getItems().stream()
                .map(itemDto -> {
                    // TEMP (replace with Restaurant Service later)
                    BigDecimal price = BigDecimal.valueOf(100);
                    return OrderMapper.toOrderItemEntity(itemDto, savedOrder, price);
                })
                .toList();

        List<OrderItem> savedItems = orderItemRepository.saveAll(items);

        BigDecimal totalAmount = savedItems.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        savedOrder.setTotalAmount(totalAmount);
        orderRepository.save(savedOrder);

        return OrderMapper.toResponseDto(savedOrder, savedItems);
    }

    // ================= GET ORDER =================
    @Override
    public OrderResponseDto getOrderById(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);

        return OrderMapper.toResponseDto(order, items);
    }

    // ================= GET BY USER =================
    @Override
    public List<OrderResponseDto> getOrdersByUserId(Long userId) {

        List<Order> orders = orderRepository.findByUserId(userId);

        return orders.stream()
                .map(order -> {
                    List<OrderItem> items = orderItemRepository.findByOrderId(order.getId());
                    return OrderMapper.toResponseDto(order, items);
                })
                .toList();
    }

    // ================= GET BY RESTAURANT =================
    @Override
    public List<OrderResponseDto> getOrdersByRestaurantId(Long restaurantId) {

        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);

        return orders.stream()
                .map(order -> {
                    List<OrderItem> items = orderItemRepository.findByOrderId(order.getId());
                    return OrderMapper.toResponseDto(order, items);
                })
                .toList();
    }

    // ================= UPDATE STATUS =================
    @Override
    public OrderResponseDto updateOrderStatus(Long orderId, OrderStatus status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // ❌ prevent invalid transitions
        if (order.getStatus().isFinalState()) {
            throw new RuntimeException("Order already completed or cancelled");
        }

        order.setStatus(status);
        Order updated = orderRepository.save(order);

        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);

        return OrderMapper.toResponseDto(updated, items);
    }

    // ================= CANCEL ORDER =================
    @Override
    public void cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus().isFinalState()) {
            throw new RuntimeException("Cannot cancel completed order");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}