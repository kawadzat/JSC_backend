package io.getarrays.securecapita.asserts.checks;

import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.asserts.repo.AssertEntityRepository;
import io.getarrays.securecapita.asserts.repo.AssertsJpaRepository;
import io.getarrays.securecapita.asserts.repo.StationRepository;
import io.getarrays.securecapita.asserts.service.AssertService;
import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.dto.StationItemStat;
import io.getarrays.securecapita.dto.StationStats;
import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.exception.CustomMessage;
import io.getarrays.securecapita.repository.implementation.UserRepository1;
import io.getarrays.securecapita.roles.prerunner.ROLE_AUTH;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
@AllArgsConstructor
public class AssetsChecksService {
    private final StationRepository stationRepository;
    private final UserRepository1 userRepository1;
    private final AssertEntityRepository assertRepository;
    private final AssertService assertService;
    private final AssetChecksRepository assetChecksRepository;

    public ResponseEntity<?> checkAssets(Long stationId) {
        User user = userRepository1.findById(((UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> (r.getAuthority().contains(ROLE_AUTH.CHECK_ASSET.name()) || r.getAuthority().contains(ROLE_AUTH.ALL_STATION.name())))) {
            if(!user.isStationAssigned(stationId)){
                return ResponseEntity.badRequest().body(new CustomMessage("Station not Assigned to User."));
            }
            Optional<Station> stationOptional = stationRepository.findById(stationId);
            if (stationOptional.isEmpty() || (!user.isStationAssigned(stationId)) && authentication.getAuthorities().stream().noneMatch((r) -> r.getAuthority().contains(ROLE_AUTH.ALL_STATION.name()))) {
                return ResponseEntity.badRequest().body(new CustomMessage("Station not found."));
            }
            Timestamp timestamp = new Timestamp(new Date().getTime());
            AssetCheck assetCheck = AssetCheck.builder()
                    .checkedBy(user)
                    .createdDate(timestamp)
                    .updatedDate(timestamp)
                    .station(stationOptional.get())
                    .build();
            assetChecksRepository.save(assetCheck);
            return ResponseEntity.ok(new CustomMessage("Added new Asset Check to Station."));
        }
        return ResponseEntity.badRequest().body(new CustomMessage("Not authorized for assets checks."));
    }

    public ResponseEntity<?> getChecksMaster(int page, int size){
        if(!canViewCheckList()){
            return ResponseEntity.badRequest().body(new CustomMessage("Not authorized for assets checks."));
        }
        //check if user have access to all stations
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains(ROLE_AUTH.ALL_STATION.name()))){
            Page<AssertChecksResponseDto> assertChecksResponseDtoPage = assetChecksRepository.findAllChecks(PageRequest.of(page, size, Sort.by("updatedDate").descending()));
            return ResponseEntity.ok(new AssertChecksResponseDtoPage(assertChecksResponseDtoPage));
        }
        List<Long> stationIds = new ArrayList<>();
        User user = userRepository1.findById(((UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()).get();
        user.getStations().forEach(station -> stationIds.add(station.getId()));
        Page<AssertChecksResponseDto> assertChecksResponseDtoPage = assetChecksRepository.findAllChecks(stationIds, PageRequest.of(page, size, Sort.by("updatedDate").descending()));
        return ResponseEntity.ok(new AssertChecksResponseDtoPage(assertChecksResponseDtoPage));
    }

    public ResponseEntity<?> createCheck(Long stationId) {
        Optional<Station> stationOptional = stationRepository.findById(stationId);
        if (stationOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(new CustomMessage("Station not found."));
        }
        if(!canCheck(stationOptional.get())){
            return ResponseEntity.badRequest().body(new CustomMessage("Not authorized for assets checks."));
        }
        Timestamp timestamp = new Timestamp(new Date().getTime());
        AssetCheck assetCheck = AssetCheck.builder()
                .checkedBy(userRepository1.findById(((UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()).get())
                .createdDate(timestamp)
                .updatedDate(timestamp)
                .station(stationRepository.findById(stationId).get())
                .build();
        assetChecksRepository.save(assetCheck);
        return ResponseEntity.ok(new CustomMessage("Added new Asset Check to Station."));
    }

    private boolean canCheck(Station station) {
        User user = userRepository1.findById(((UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> (r.getAuthority().contains(ROLE_AUTH.CHECK_ASSET.name()) || r.getAuthority().contains(ROLE_AUTH.ALL_STATION.name())))) {
            return user.isStationAssigned(station.getStation_id());
        }
        return false;
    }
    private boolean canViewCheckList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream().anyMatch((r) -> (r.getAuthority().contains(ROLE_AUTH.CHECK_ASSET.name()) || r.getAuthority().contains(ROLE_AUTH.ALL_STATION.name())));
    }
//    public ResponseEntity<?> getChecksMaster(int page, int size) {
//        User user = userRepository1.findById(((UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()).get();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication.getAuthorities().stream().anyMatch((r) -> (r.getAuthority().contains(ROLE_AUTH.CHECK_ASSET.name()) || r.getAuthority().contains(ROLE_AUTH.ALL_STATION.name())))) {
////            Optional<Station> stationOptional = stationRepository.findById(stationId);
////            if (stationOptional.isEmpty() || (!user.isStationAssigned(stationId)) && authentication.getAuthorities().stream().noneMatch((r) -> r.getAuthority().contains(ROLE_AUTH.ALL_STATION.name()))) {
////                return ResponseEntity.badRequest().body(new CustomMessage("Station not found."));
////            }
//            Page<AssertChecksResponseDto> assertChecksResponseDtoPage = assetChecksRepository.findAllChecks(stationOptional.get().getStation_id(), PageRequest.of(page, size, Sort.by("updatedDate").descending()));
//            return ResponseEntity.ok(new AssertChecksResponseDtoPage(assertChecksResponseDtoPage));
//        }
//        return ResponseEntity.badRequest().body(new CustomMessage("Not authorized for assets checks."));


//    public ResponseEntity<?> getChecksMaster(int page, int size) {
//        User user = userRepository1.findById(((UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()).get();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication.getAuthorities().stream().anyMatch((r) -> (r.getAuthority().contains(ROLE_AUTH.CHECK_ASSET.name()) || r.getAuthority().contains(ROLE_AUTH.ALL_STATION.name())))) {
//            Page<AssertChecksResponseDto> assertChecksResponseDtoPage = assetChecksRepository.findAllChecks(PageRequest.of(page, size, Sort.by("updatedDate").descending()));
//            return ResponseEntity.ok(new AssertChecksResponseDtoPage(assertChecksResponseDtoPage));
//        }
        }
