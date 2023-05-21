package com.assignment.topkart.controller;

import com.assignment.topkart.model.LightningDeal;
import com.assignment.topkart.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private List<LightningDeal> lightningDeals = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    @GetMapping("/lightning-deals")
    public ResponseEntity<List<LightningDeal>> getAvailableDeals() {
        List<LightningDeal> availableDeals = lightningDeals.stream()
                .filter(deal -> deal.getExpiryTime().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(availableDeals);
    }

    @PostMapping("/orders")
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        Optional<LightningDeal> optionalDeal = lightningDeals.stream()
                .filter(deal -> deal.getId().equals(order.getDealId()))
                .findFirst();

        if (optionalDeal.isPresent()) {
            LightningDeal deal = optionalDeal.get();

            if (deal.getExpiryTime().isAfter(LocalDateTime.now()) && deal.getAvailableUnits() > 0) {
                // Decrease available units and create the order
                deal.setAvailableUnits(deal.getAvailableUnits() - 1);
                orders.add(order);
                return ResponseEntity.ok("Order placed successfully");
            }

            return ResponseEntity.badRequest().body("The lightning deal is expired or out of stock");
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrderStatus(@PathVariable String orderId) {
        Optional<Order> optionalOrder = orders.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst();

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return ResponseEntity.ok(order);
        }

        return ResponseEntity.notFound().build();
    }
}

