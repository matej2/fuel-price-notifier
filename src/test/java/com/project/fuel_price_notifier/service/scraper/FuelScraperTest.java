package com.project.fuel_price_notifier.service.scraper;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FuelScraperTest {

    @Mock
    Connection jsoupConnection;
    @Mock
    Environment environment;
    @Mock
    Document document;
    @Mock
    Element body;

    private String getSampleHtml() {
        return """
                <!DOCTYPE html>
                <html>
                
                <body>
                
                
                <table class="table stripes at-table">
                
                
                
                
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
                </table>
                </body>
                </html>
                """;
    }

    private void mockEnvironmentConfig() {
        when(environment.getProperty("application.scrape.selector.table")).thenReturn(".w3-example");
        when(environment.getProperty("application.scrape.selector.row")).thenReturn("tr");
        when(environment.getProperty("application.scrape.selector.column")).thenReturn("td.align-general");
    }

    @Test
    void getFuelPriceForDay() throws IOException {
        when(body.text()).thenReturn(getSampleHtml());
        when(document.body()).thenReturn(body);
        when(jsoupConnection.get()).thenReturn(document);
        mockEnvironmentConfig();

        FuelScraper scraper = new FuelScraper(jsoupConnection, environment);

        assertNull(scraper.getFuelPriceForDay());
    }
}