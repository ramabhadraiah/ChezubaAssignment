package com.assignment.topkart.controller;

import com.assignment.topkart.model.LightningDeal;
import com.assignment.topkart.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private List<LightningDeal> lightningDeals = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    @PostMapping("/lightning-deals")
    public ResponseEntity<String> createLightningDeal(@RequestBody LightningDeal deal) {
        if (deal.getExpiryTime().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Expiry time cannot be in the past");
        }

        lightningDeals.add(deal);
        return ResponseEntity.ok("Lightning deal created successfully");
    }

    @PutMapping("/lightning-deals/{dealId}")
    public ResponseEntity<String> updateLightningDeal(@PathVariable String dealId, @RequestBody LightningDeal updatedDeal) {
        Optional<LightningDeal> optionalDeal = lightningDeals.stream()
                .filter(deal -> deal.getId().equals(dealId))
                .findFirst();

        if (optionalDeal.isPresent()) {
            LightningDeal deal = optionalDeal.get();

            deal.setProductName(updatedDeal.getProductName());
            deal.setActualPrice(updatedDeal.getActualPrice());
            deal.setFinalPrice(updatedDeal.getFinalPrice());
            deal.setTotalUnits(updatedDeal.getTotalUnits());
            deal.setAvailableUnits(updatedDeal.getAvailableUnits());
            deal.setExpiryTime(updatedDeal.getExpiryTime());

            return ResponseEntity.ok("Lightning deal updated successfully");
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/orders/{orderId}/approve")
    public ResponseEntity<String> approveOrder(@PathVariable String orderId) {
        Optional<Order> optionalOrder = orders.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst();

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            order.setStatus("Approved");

            return ResponseEntity.ok("Order approved successfully");
        }

        return ResponseEntity.notFound().build();
    }
}

