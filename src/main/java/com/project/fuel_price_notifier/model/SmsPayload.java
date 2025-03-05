package com.project.fuel_price_notifier.model;

public record SmsPayload(
        String validDate,
        float priceGasoline,
        float percentIncreasePriceGasoline,
        float absoluteIncreasePriceGasoline,
        float priceDiesel,
        float percentIncreasePriceDiesel,
        float absoluteIncreasePriceDiesel) {

    public String toString() {
        return String.format("""
                Price update for date range %s:
                
                - Gasoline:
                  price changed to %s for %s € (%.4f %%)
                
                - Diesel:
                  price changed to %s for %s € (%.4f %%)
                
                """,
                validDate,
                priceGasoline,
                absoluteIncreasePriceGasoline,
                percentIncreasePriceGasoline,
                priceDiesel,
                absoluteIncreasePriceDiesel,
                percentIncreasePriceDiesel);
    }
}