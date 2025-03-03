package com.project.fuel_price_notifier.models;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FuelHistoryTest {
    private FuelMetadata getFuelMetadata() {
        return new FuelMetadata(
                new FuelEntry(new Date(), 1.0F),
                new FuelEntry(new Date(), 2.0F),
                new FuelEntry(new Date(), 3.0F)
        );
    }

    @Test
    public void testFuelHistoryToStringForValidInput() {
        FuelHistory history = new FuelHistory();
        history.addRow(getFuelMetadata());

        assertTrue(history.toString().contains("1.0"));
        assertTrue(history.toString().contains("2.0"));
        assertTrue(history.toString().contains("3.0"));
    }
}