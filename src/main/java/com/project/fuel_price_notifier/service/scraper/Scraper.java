package com.project.fuel_price_notifier.service.scraper;

import com.project.fuel_price_notifier.model.FuelHistory;

import java.io.IOException;

public interface Scraper {
    FuelHistory getFuelPriceList() throws IOException;
}
