package com.project.fuel_price_notifier.models;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FuelMetadataTest {
    @Test
    public void testFuelMetadataToStringForValidInput() {
        FuelEntry gasoline = new FuelEntry(new Date(), 1.0F);
        FuelEntry diesel = new FuelEntry(new Date(), 2.0F);
        FuelEntry lpg = new FuelEntry(new Date(), 3.0F);

        FuelMetadata metadata = new FuelMetadata(gasoline, diesel, lpg);

        assertTrue(metadata.toString().contains("1.0"));
        assertTrue(metadata.toString().contains("2.0"));
        assertTrue(metadata.toString().contains("3.0"));
    }
}