package com.project.fuel_price_notifier.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Getter
public class FuelHistory {

    private final ArrayList<FuelMetadata> priceHistory = new ArrayList<>();

    public void addRow(FuelMetadata metadata) {
        priceHistory.add(metadata);
    }

    @Override
    public String toString() {
        return priceHistory.stream().map(FuelMetadata::toString).collect(Collectors.joining(", "));
    }
}
