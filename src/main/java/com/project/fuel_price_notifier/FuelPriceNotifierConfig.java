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
        return Jsoup
                .connect(targetUrl)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36")
                .header("Accept-Language", "*") ;
    }
}
