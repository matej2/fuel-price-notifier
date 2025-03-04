package com.project.fuel_price_notifier;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class FuelPriceNotifierConfig {
    @Bean
    public Connection getJsoupConnection(Environment environment) {
        String targetUrl = environment.getProperty("application.scrape.url");
        if (targetUrl == null) {
            throw new RuntimeException("Config 'application.scrape.url' is missing");
        }
        return Jsoup.connect(targetUrl);
    }
}
