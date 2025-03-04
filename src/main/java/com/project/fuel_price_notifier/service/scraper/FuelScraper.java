package com.project.fuel_price_notifier.service.scraper;

import com.project.fuel_price_notifier.model.FuelHistory;
import com.project.fuel_price_notifier.model.FuelMetadata;
import com.project.fuel_price_notifier.util.FormattingUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;

@Service
@Slf4j
public class FuelScraper implements Scraper {
    private final String tableSelector;
    private final String rowSelector;
    private final String columnSelector;
    private final Connection jsoupConnection;

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

        String date = columnList.getFirst().text();
        Float gasolinePrice = -1.0F;
        Float dieselPrice = -1.0F;
        Float lpgPrice = -1.0F;

        try {
            gasolinePrice = FormattingUtil.parseLocalFloat(columnList.get(1).text());
            dieselPrice = FormattingUtil.parseLocalFloat(columnList.get(2).text());
        } catch (ParseException e) {
            log.warn("Cannot parse details for date {}", date);
        }

        return new FuelMetadata(date, gasolinePrice, dieselPrice, lpgPrice);
    }

    private FuelHistory mapFuelHistoryRow(Element table) {
        Elements rowList = table.select(rowSelector);
        FuelHistory fuelHistory = new FuelHistory();

        if (rowList.isEmpty()) {
            throw new RuntimeException("No row found");
        }

        // Remove table header
        rowList.removeFirst();

        for (Element columnElement : rowList) {
            fuelHistory.addRow(mapFuelHistoryColumn(columnElement));
        }
        return fuelHistory;
    }



    @Override
    public FuelHistory getFuelPriceList() throws IOException {
        Document response = this.jsoupConnection.get();
        Element table = response.selectFirst(tableSelector);
        assert table != null;
        return mapFuelHistoryRow(table);
    }
}
