package com.project.fuel_price_notifier.service;

import com.project.fuel_price_notifier.model.FuelHistory;
import com.project.fuel_price_notifier.model.FuelMetadata;
import com.project.fuel_price_notifier.model.LatestFuelMetadataEntry;
import com.project.fuel_price_notifier.model.SmsPayload;
import com.project.fuel_price_notifier.repository.FuelMetadataRepository;
import com.project.fuel_price_notifier.service.client.TwillioClient;
import com.project.fuel_price_notifier.service.scraper.FuelScraper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FuelPriceProcessorService {
    private final FuelScraper scraper;
    private final TwillioClient twillioClient;
    private final FuelMetadataRepository fuelMetadataRepository;

    FuelPriceProcessorService(FuelScraper scraper, TwillioClient twillioClient, FuelMetadataRepository fuelMetadataRepository) {
        this.scraper = scraper;
        this.twillioClient = twillioClient;
        this.fuelMetadataRepository = fuelMetadataRepository;
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

        FuelMetadata fuelMetadata = fuelHistory.getPriceHistory().getFirst();
        LatestFuelMetadataEntry latestFuelMetadataEntry = new LatestFuelMetadataEntry(
                fuelMetadata.date(),
                fuelMetadata.gasoline(),
                fuelMetadata.diesel(),
                fuelMetadata.lpg()
        );

        fuelMetadataRepository.save(latestFuelMetadataEntry);
        //fuelMetadataRepository.findFirstByOrderByDateCreatedDesc();
        //twillioClient.sendMessage(payload);

    }
}
