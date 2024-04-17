package io.getarrays.securecapita.fleetmanagement.service;


import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequest;
import io.getarrays.securecapita.fleetmanagement.models.Vehicle;
import io.getarrays.securecapita.fleetmanagement.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    //Get All Vehicles
    public List<Vehicle> findAll(){
        return vehicleRepository.findAll();
    }

    //Get Vehicle By Id
    public Vehicle findById(int id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    //Delete Vehicle
    public void delete(int id) {
        vehicleRepository.deleteById(id);
    }

    //Update Vehicle
    /* to create user */
    public Vehicle createVehicle(Vehicle vehicle) {

        Vehicle createdVehicle =vehicleRepository.save(vehicle);

        return createdVehicle;
    }







}

