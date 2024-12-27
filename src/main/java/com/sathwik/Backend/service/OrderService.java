package com.sathwik.Backend.service;

import com.sathwik.Backend.dto.OrderDetails;
import com.sathwik.Backend.model.Order;
import com.sathwik.Backend.model.OrderItem;
import com.sathwik.Backend.model.Product;
import com.sathwik.Backend.repository.OrderRepository;
import com.sathwik.Backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order createOrder(OrderDetails orderDetails) {
        Order order = new Order();
        List<OrderItem> orderItemList = new ArrayList<>();
        double totalPrice = 0;

        for (OrderDetails.OrderItemDetails item : orderDetails.getItems()) {
            // fetching from the repo
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // checking stock availability
            if (product.getQuantity() < item.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            }

            product.setQuantity(product.getQuantity() - item.getQuantity());
            productRepository.save(product);

            // Creating an OrderItem and populate its fields
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPriceAtOrder(product.getPrice() * item.getQuantity());

            orderItemList.add(orderItem);

            totalPrice += item.getQuantity() * product.getPrice();
        }

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItemList);

        // Save the Order entity (cascades will save OrderItems as well)
        return orderRepository.save(order);
    }


    public Order getOrderDetails(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> listOrders(int page, int size) {
        return orderRepository.findAll(PageRequest.of(page, size)).getContent();
    }
}
