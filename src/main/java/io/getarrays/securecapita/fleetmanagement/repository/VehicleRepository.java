package io.getarrays.securecapita.fleetmanagement.repository;

import io.getarrays.securecapita.fleetmanagement.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
