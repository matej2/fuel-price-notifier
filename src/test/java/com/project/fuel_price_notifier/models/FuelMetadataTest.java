package com.project.fuel_price_notifier.models;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FuelMetadataTest {
    @Test
    public void testFuelMetadataToStringForValidInput() {
        FuelMetadata metadata = new FuelMetadata("date", 1F, 2F, 3F);

        assertTrue(metadata.toString().contains("1.0"));
        assertTrue(metadata.toString().contains("2.0"));
        assertTrue(metadata.toString().contains("3.0"));
    }
}