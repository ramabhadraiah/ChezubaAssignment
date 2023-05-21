package com.assignment.topkart.service;

import com.assignment.topkart.model.LightningDeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Configuration
@EnableScheduling
public class LightningDealScheduler {

    @Autowired
    private List<LightningDeal> lightningDeals;

    @Scheduled(cron = "0 0 0 * * ?") // Runs at 00:00 UTC every day
    public void refreshLightningDeals() {
        // Refresh the lightning deals by updating their data or removing expired deals
        Iterator<LightningDeal> iterator = lightningDeals.iterator();
        while (iterator.hasNext()) {
            LightningDeal deal = iterator.next();
            if (deal.getExpiryTime().isBefore(LocalDateTime.now())) {
                iterator.remove(); // Remove expired deal
            } else {
                // Perform any necessary updates for non-expired deals
            }
        }
    }
}
