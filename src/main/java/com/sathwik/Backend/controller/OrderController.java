package com.sathwik.Backend.controller;

import com.sathwik.Backend.dto.OrderDetails;
import com.sathwik.Backend.model.Order;
import com.sathwik.Backend.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sathwik.Backend.util.ErrorResponse;

import java.util.List;

@RestController
@RequestMapping("/api/user/orders")
public class OrderController {

    private final OrderService orderService;
    private static final String DEFAULT_PAGE = "0";
    private static final String DEFAULT_SIZE = "10";

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDetails orderDetails) {
        try {
            return ResponseEntity.ok(orderService.createOrder(orderDetails));
        }catch (IllegalArgumentException ie) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(ie.getMessage(), "Invalid data provided for the order"));
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(re.getMessage(), "No product found with the provided ID"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal server error", "An unexpected error occurred"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetails(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.getOrderDetails(id));
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(re.getMessage(), "No order found with the provided ID"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Internal server error", "An unexpected error occurred"));
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> listOrders(
            @RequestParam(defaultValue = DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = DEFAULT_SIZE) int size) {
        return ResponseEntity.ok(orderService.listOrders(page, size));
    }
}
