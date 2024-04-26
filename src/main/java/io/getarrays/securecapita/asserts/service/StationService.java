package io.getarrays.securecapita.asserts.service;

import com.twilio.http.Response;
import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.asserts.repo.AssertEntityRepository;
import io.getarrays.securecapita.asserts.repo.StationRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StationService {
    private final StationRepository stationRepository;
    private final AssertEntityRepository assertRepository;
    /* to create user */
    public Station createStation(Station newStation) {
        Station createdStation = stationRepository.save(newStation);
        return createdStation;
    }

    /* updating the user */
    public Station updateStation(Station station) {

        Station updatedStation =stationRepository.save(station);

        return updatedStation;
    }


    public Station getStationById(Long stationId) {

        Optional<Station> optionalStation = stationRepository.findById(stationId);
        boolean isPresent = optionalStation.isPresent();

        if(isPresent) {

            Station station =optionalStation.get();
            return station;
        }

        return null;


    }


    public ResponseEntity<?> addAssert(Long stationId, Long assertId) {
        Optional<Station> optionStation=stationRepository.findById(stationId);
        if(optionStation.isEmpty()){
            return ResponseEntity.badRequest().body("Station does not exist!");
        }
        Optional<AssertEntity> optionalAssert=assertRepository.findById(assertId);
        if(optionalAssert.isEmpty()){
            return ResponseEntity.badRequest().body("Assert does not exist!");
        }
        System.out.println("Ok");
        optionalAssert.get().setStation(optionStation.get());
        assertRepository.save(optionalAssert.get());
        return ResponseEntity.ok("Added Assert to Station");
    }

    public ResponseEntity<?> getAllStations() {
        List<Station> stations=stationRepository.findAll();
        List<Map<String, Object>> transformedStations = stations.stream()
                .map(station -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", station.getStation_id());
                    map.put("name", station.getStationName());
                    return map;
                })
                .toList();
        return ResponseEntity.ok(transformedStations);
    }
}

@Data
class StationResponse {
    private Long id;
    private String name;
}

