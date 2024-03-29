package io.getarrays.securecapita.asserts.controller;

import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.asserts.model.StationName;
import io.getarrays.securecapita.asserts.service.AssertService;
import io.getarrays.securecapita.asserts.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/station")
@RequiredArgsConstructor
public class StationController {

   /* to create user */

    @Autowired
    StationService stationService;


    @PostMapping("/create")
    public Station createStation(@RequestBody Station newStation) {
        Station createdStation = stationService.createStation(newStation);
        return createdStation;
    }

//is this the project i cant see the enum
    @PutMapping("/update/{id}")
    public Station updateStation(@PathVariable("id") Long stationId  ,@RequestBody  Station  station) {

        Station oldstation =stationService.getStationById(stationId);


        oldstation.setStationName(station.getStationName());


        Station updatedStation =stationService.updateStation(oldstation);

        return updatedStation;
    }



    @GetMapping("/getAll")
    public ResponseEntity<?> getAllStations(){
        List<HashMap<String,Object>> responses=new ArrayList<>();
        for(StationName stationName:StationName.values()){
            HashMap<String,Object> response=new HashMap<>();
            response.put("id",stationName.getId());
            response.put("name",stationName.getName());
            responses.add(response);
        }
        return ResponseEntity.ok(responses);
    }


    //add assert to station
    @PostMapping("/addAssertToStation")
    public ResponseEntity<?> addAssertToStation(@RequestParam("assertId") Long assertId,@RequestParam("stationId") Long stationId){
        return stationService.addAssert(stationId,assertId);
    }

}