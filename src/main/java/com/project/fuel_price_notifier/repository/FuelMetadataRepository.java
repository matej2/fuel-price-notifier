package com.project.fuel_price_notifier.repository;

import com.project.fuel_price_notifier.model.LatestFuelMetadataEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelMetadataRepository extends MongoRepository<LatestFuelMetadataEntry, Long> {
    LatestFuelMetadataEntry findFirstByOrderByDateCreatedDesc();
}
