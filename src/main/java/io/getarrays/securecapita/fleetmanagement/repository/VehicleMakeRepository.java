package io.getarrays.securecapita.fleetmanagement.repository;

import io.getarrays.securecapita.fleetmanagement.models.VehicleMake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleMakeRepository    extends JpaRepository<VehicleMake, Integer> {
}
