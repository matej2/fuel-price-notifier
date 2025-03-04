package com.project.fuel_price_notifier.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FuelHistoryTest {
    private FuelMetadata getFuelMetadata() {
        return new FuelMetadata(
                "date",
                1F,
                2F,
                3F
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