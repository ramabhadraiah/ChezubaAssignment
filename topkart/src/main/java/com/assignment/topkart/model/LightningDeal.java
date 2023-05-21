package com.assignment.topkart.model;


import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="lightningDeal")
public class LightningDeal {
    private String id;
    private String productName;
    private double actualPrice;
    private double finalPrice;
    private int totalUnits;
    private int availableUnits;
    private LocalDateTime expiryTime;
}
