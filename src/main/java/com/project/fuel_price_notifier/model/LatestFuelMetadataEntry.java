package com.project.fuel_price_notifier.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document("GroceryItem")
@Getter
@Setter
public class LatestFuelMetadataEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    String dateCreated;
    String dateValid;
    Float gasoline;
    Float diesel;
    Float lpg;

    public LatestFuelMetadataEntry() {
        super();
    }
    public LatestFuelMetadataEntry(String date, Float gasoline, Float diesel, Float lpg) {
        super();
        this.dateValid = date;
        this.gasoline = gasoline;
        this.diesel = diesel;
        this.lpg = lpg;
    }
}
