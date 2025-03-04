package com.project.fuel_price_notifier.service.scraper;

import com.project.fuel_price_notifier.model.FuelHistory;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.mock.env.MockEnvironment;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FuelScraperTest {

    @Spy
    Connection jsoupConnection;

    private Document getTestDocument() {
        String html = """
                <!DOCTYPE html>
                <html class="type-ArticlePage" lang="sl" dir="ltr">
                
                <body class="ArticlePage">
                
                \s
                
                <div class="legislation-element__table table-module block " id="e162094">
                	<div class="adapt">
                		<div class="inner">
                
                
                
                
                
                
                
                
                					<div class="at-container at-responsive-table"><div class="at-wrapper"><div class="at-scrollable-table"><table class="table stripes at-table">
                
                
                
                
                							<thead>
                
                								<tr>
                
                										<th scope="col" class="align-general bgcolor">Datum veljavnosti </th>
                
                										<th scope="col" class="align-general bgcolor">Drobnoprodajna cena NMB 95 (evro/liter)</th>
                
                										<th scope="col" class="align-general bgcolor">Drobnoprodajna cena dizelskega goriva (evro/liter)</th>
                
                								</tr>
                
                							</thead>
                
                						<tbody>
                
                
                
                
                
                								<tr>
                
                											<td class="align-general bold">od 25. 2. do 10. 3. 2025</td>
                
                											<td class="align-general bold">1,538</td>
                
                											<td class="align-general bold">1,598</td>
                
                								</tr>
                
                
                
                								<tr>
                
                											<td class="align-general">od 11. 2. do 24. 2. 2025</td>
                
                											<td class="align-general">1,535</td>
                
                											<td class="align-general">1,586</td>
                
                								</tr>
                
                
                
                								<tr>
                
                											<td class="align-general">od 28. 1. do 10. 2. 2025</td>
                
                											<td class="align-general">1,550</td>
                
                											<td class="align-general">1,619</td>
                
                								</tr>
                
                
                
                
                						</tbody>
                					</table></div></div></div></div></div></div>
                
                </body></html>
                """;
        Document result = Document.createShell("");
        result.append(html);
        return result;
    }

    public Environment getEnvironment() {
        MockEnvironment mockEnv = new MockEnvironment();
        mockEnv.setProperty("application.scrape.selector.table",".legislation-element__table table");
        mockEnv.setProperty("application.scrape.selector.row", "tr");
        mockEnv.setProperty("application.scrape.selector.column", "td.align-general");
        return mockEnv;
    }

    @Test
    void getFuelPriceList() throws IOException {
        when(jsoupConnection.get()).thenReturn(getTestDocument());

        FuelScraper scraper = new FuelScraper(jsoupConnection, getEnvironment());

        FuelHistory response = scraper.getFuelPriceList();

        assertEquals(1.538F, response.getPriceHistory().getFirst().gasoline());
        assertEquals(1.598F, response.getPriceHistory().getFirst().diesel());
    }
}