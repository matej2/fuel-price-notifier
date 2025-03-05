package com.project.fuel_price_notifier.service.scheduler;

import com.project.fuel_price_notifier.service.FuelPriceProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class SchedulerConfig {
    private final FuelPriceProcessorService fuelPriceProcessorService;

    @Autowired
    public SchedulerConfig(FuelPriceProcessorService fuelPriceProcessorService) {
        this.fuelPriceProcessorService = fuelPriceProcessorService;
    }
    @Scheduled(cron = "0 * * * * * ")
    public void getFuelPrices() throws IOException {
        fuelPriceProcessorService.processPriceChange();
    }
}
