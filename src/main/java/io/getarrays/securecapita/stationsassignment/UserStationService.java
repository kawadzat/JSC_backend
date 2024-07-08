package io.getarrays.securecapita.stationsassignment;

import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.asserts.repo.StationRepository;
import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.exception.CustomMessage;
import io.getarrays.securecapita.repository.implementation.UserRepository1;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserStationService {
    private final UserStationRepo userStationRepo;
    private final UserRepository1 userRepository1;
    private final StationRepository stationRepository;

    public ResponseEntity<?> getCurrentUserStations() {
        Long userId = ((UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<UserStation> userStationList = userStationRepo.findAllByUser(userId);
        return ResponseEntity.ok(UserStationDto.fromEntities(userStationList));
    }

    public ResponseEntity<?> addStationToUser(Long userId, Long stationId) {
        Optional<UserStation> optionalUserStation=userStationRepo.findAllByUserAndStation(userId,stationId);
        if(optionalUserStation.isPresent()){
            return ResponseEntity.badRequest().body(new CustomMessage("user is already in the station."));
        }
        Optional<User> optionalUser=userRepository1.findById(userId);
        User assignedBy = userRepository1.findById((((UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId())).get();
        Optional<Station> optionalStation=stationRepository.findById(stationId);
        if(optionalUser.isEmpty()||optionalStation.isEmpty()){
            return ResponseEntity.badRequest().body(new CustomMessage("user or station is not valid, try again."));
        }

        UserStation userStation=UserStation.builder()
                .user(optionalUser.get())
                .assignedBy(assignedBy)
                .station(optionalStation.get())
                .createdDate(new Timestamp(new Date().getTime()))
                .build();
        return ResponseEntity.ok(UserStationDto.fromEntity(userStationRepo.save(userStation)));

    }

    public ResponseEntity<?> removeStationFromUser(Long assignId) {
        Optional<UserStation> optionalUserStation=userStationRepo.findById(assignId);
        if(optionalUserStation.isEmpty()){
            return ResponseEntity.badRequest().body(new CustomMessage("user or station is not valid, try again."));
        }
        userStationRepo.delete(optionalUserStation.get());
        return ResponseEntity.ok(new CustomMessage("user removed from the station."));

    }

    public ResponseEntity<?> getUserStations(Long userId) {
        List<UserStation> userStationList = userStationRepo.findAllByUser(userId);
        return ResponseEntity.ok(UserStationDto.fromEntities(userStationList));
    }
}
