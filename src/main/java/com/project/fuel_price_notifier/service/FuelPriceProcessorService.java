package com.project.fuel_price_notifier.service;

import com.project.fuel_price_notifier.model.FuelHistory;
import com.project.fuel_price_notifier.model.FuelMetadata;
import com.project.fuel_price_notifier.model.SmsPayload;
import com.project.fuel_price_notifier.service.client.TwillioClient;
import com.project.fuel_price_notifier.service.scraper.FuelScraper;

import java.io.IOException;

public class FuelPriceProcessorService {
    private final FuelScraper scraper;
    private final TwillioClient twillioClient;

    FuelPriceProcessorService(FuelScraper scraper, TwillioClient twillioClient) {
        this.scraper = scraper;
        this.twillioClient = twillioClient;
    }

    private SmsPayload fillPayloadDataForLatestPriceData(FuelHistory history) {
        FuelMetadata firstMetadata = history.getPriceHistory().get(0);
        FuelMetadata secondMetadata = history.getPriceHistory().get(1);

        float priceDiffGasoline = secondMetadata.gasoline() - firstMetadata.gasoline();
        float priceDiffDiesel = secondMetadata.gasoline() - firstMetadata.gasoline();

        float percentDiffGasoline = (Math.abs(priceDiffGasoline) / secondMetadata.gasoline()) * 100;
        float percentDiffDiesel = (Math.abs(priceDiffDiesel) / secondMetadata.diesel()) * 100;

        return new SmsPayload(
                firstMetadata.date(),
                firstMetadata.gasoline(),
                percentDiffGasoline,
                priceDiffGasoline,
                firstMetadata.diesel(),
                percentDiffDiesel,
                priceDiffDiesel);
    }

    public void processPriceChange() throws IOException {
        FuelHistory fuelHistory = scraper.getFuelPriceList();

        SmsPayload payload = fillPayloadDataForLatestPriceData(fuelHistory);
        twillioClient.sendMessage(payload);

    }
}
