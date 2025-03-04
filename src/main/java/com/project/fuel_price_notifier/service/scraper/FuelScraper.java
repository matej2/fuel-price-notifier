package com.project.fuel_price_notifier.service.scraper;

import com.project.fuel_price_notifier.models.FuelHistory;
import com.project.fuel_price_notifier.models.FuelMetadata;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.IOException;

public class FuelScraper implements Scraper {
    private final String tableSelector;
    private final String rowSelector;
    private final String columnSelector;
    Connection jsoupConnection;
    Logger logger = LoggerFactory.getLogger(FuelScraper.class);

    @Autowired
    FuelScraper(Connection jsoupConnection, Environment environment) {
        this.jsoupConnection = jsoupConnection;
        try {
            this.tableSelector = environment.getProperty("application.scrape.selector.table");
            this.rowSelector = environment.getProperty("application.scrape.selector.row");
            this.columnSelector = environment.getProperty("application.scrape.selector.column");
        } catch (Exception e) {
            throw new RuntimeException("Configuration missing");
        }
    }

    private FuelMetadata mapFuelHistoryColumn(Element row) {
        Elements columnList = row.select(columnSelector);
        if (columnList.isEmpty()) {
            throw new RuntimeException("Invalid column format");
        }

        String date = columnList.get(0).text();

        Float gasolinePrice = Float.parseFloat(columnList.get(1).text());
        Float dieselPrice = Float.parseFloat(columnList.get(1).text());
        Float lpgPrice = Float.parseFloat(columnList.get(1).text());

        return new FuelMetadata(date, gasolinePrice, dieselPrice, lpgPrice);
    }

    private FuelHistory mapFuelHistoryRow(Element table) {
        Elements rowList = table.select(rowSelector);
        FuelHistory fuelHistory = new FuelHistory();

        if (rowList.isEmpty()) {
            throw new RuntimeException("No row found");
        }

        for (Element columnElement : rowList) {
            fuelHistory.addRow(mapFuelHistoryColumn(columnElement));
        }
        return fuelHistory;
    }



    @Override
    public FuelHistory getFuelPriceForDay() throws IOException {
        Document response = this.jsoupConnection.get();
        Element table = response.selectFirst(tableSelector);
        return mapFuelHistoryRow(table);
    }
}
