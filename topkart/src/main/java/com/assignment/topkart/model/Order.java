package com.assignment.topkart.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Order")
public class Order {
    private String orderId;
    private String dealId;
    private String productName;
    private double finalPrice;
    private LocalDateTime orderTime;
    private String status;
}
