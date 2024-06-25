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
            if(stationId==null&&user.getStation()==null){
                return ResponseEntity.badRequest().body(new CustomMessage("Station not Assigned to User."));
            }
            Optional<Station> stationOptional = stationRepository.findById(stationId==null?user.getStation().getStation_id():stationId);
            if (stationOptional.isEmpty() || (!Objects.equals(user.getStation().getStation_id(), stationOptional.get().getStation_id()) && authentication.getAuthorities().stream().noneMatch((r) -> r.getAuthority().contains(ROLE_AUTH.ALL_STATION.name())))) {
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

    public ResponseEntity<?> getChecks(Long stationId, int page, int size) {
        User user = userRepository1.findById(((UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> (r.getAuthority().contains(ROLE_AUTH.CHECK_ASSET.name()) || r.getAuthority().contains(ROLE_AUTH.ALL_STATION.name())))) {
            if(stationId==null&&user.getStation()==null){
                return ResponseEntity.badRequest().body(new CustomMessage("Station not Assigned to User."));
            }
            Optional<Station> stationOptional = stationRepository.findById(stationId==null?user.getStation().getStation_id():stationId);
            if (stationOptional.isEmpty() || (!Objects.equals(user.getStation().getStation_id(), stationOptional.get().getStation_id()) && authentication.getAuthorities().stream().noneMatch((r) -> r.getAuthority().contains(ROLE_AUTH.ALL_STATION.name())))) {
                return ResponseEntity.badRequest().body(new CustomMessage("Station not found."));
            }
            Page<AssertChecksResponseDto> assertChecksResponseDtoPage = assetChecksRepository.findAllChecks(stationOptional.get().getStation_id(), PageRequest.of(page, size, Sort.by("updatedDate").descending()));
            return ResponseEntity.ok(new AssertChecksResponseDtoPage(assertChecksResponseDtoPage));
        }
        return ResponseEntity.badRequest().body(new CustomMessage("Not authorized for assets checks."));
    }
}
