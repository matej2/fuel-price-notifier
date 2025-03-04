package com.project.fuel_price_notifier.service.scraper;

import com.project.fuel_price_notifier.models.FuelHistory;

import java.io.IOException;

public interface Scraper {
    FuelHistory getFuelPriceForDay() throws IOException;
}
