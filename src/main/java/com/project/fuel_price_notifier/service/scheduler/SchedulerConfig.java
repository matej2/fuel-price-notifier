package com.project.fuel_price_notifier.service.scheduler;

import com.project.fuel_price_notifier.model.FuelHistory;
import com.project.fuel_price_notifier.service.client.TwillioClient;
import com.project.fuel_price_notifier.service.scraper.Scraper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class SchedulerConfig {
    private final Scraper fuelScraper;
    private final TwillioClient twillioClient;

    @Autowired
    public SchedulerConfig(Scraper fuelScraper, TwillioClient twillioClient) {
        this.fuelScraper = fuelScraper;
        this.twillioClient = twillioClient;
    }
    @Scheduled(cron = "0 * * * * * ")
    public void getFuelPrices() throws IOException {
        FuelHistory history = fuelScraper.getFuelPriceList();
        twillioClient.sendMessage(history.toString());
    }
}
