package io.getarrays.securecapita.assertmoverequests;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.repo.AssertEntityRepository;
import io.getarrays.securecapita.asserts.repo.AssertsJpaRepository;
import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.exception.CustomMessage;
import io.getarrays.securecapita.officelocations.OfficeLocation;
import io.getarrays.securecapita.officelocations.OfficeLocationRepository;
import io.getarrays.securecapita.repository.implementation.UserRepository1;
import io.getarrays.securecapita.roles.prerunner.ROLE_AUTH;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AssertMoveService {
    private final MoveLocationRepository moveLocationRepository;
    private final AssertsJpaRepository assertEntityRepository;
    private final OfficeLocationRepository officeLocationRepository;
    private final UserRepository1 userRepository1;

    public ResponseEntity<Object> getAll(int page, int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository1.findById(((UserDTO) authentication.getPrincipal()).getId()).get();
        Page<AssertMoveRequest> officeLocations;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdDate").descending());
        if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains(ROLE_AUTH.ALL_STATION.name()))) {
            officeLocations = moveLocationRepository.findAll(pageRequest);
        } else {
            officeLocations = moveLocationRepository.findByUserIdAndAssignedStations(userRepository1.findById(((UserDTO) authentication.getPrincipal()).getId()).get().getId(), pageRequest);
        }
        return ResponseEntity.ok(AssertMoveResponseDto.toList(officeLocations.get().toList()));
    }


    public ResponseEntity<Object> approve(Long assertRequestId) {
        Optional<AssertMoveRequest> assertMoveRequest = moveLocationRepository.findById(assertRequestId);
        if (assertMoveRequest.isPresent() && assertMoveRequest.get().getStatus() == AssertMoveStatus.PENDING) {
            Optional<AssertEntity> optionalAssert = assertEntityRepository.findById(assertMoveRequest.get().getAssertEntity().getId());
            if (optionalAssert.isPresent()) {
                optionalAssert.get().setOfficeLocation(assertMoveRequest.get().getOfficeLocation());
                optionalAssert.get().setLocation(assertMoveRequest.get().getOfficeLocation().getName());
                optionalAssert.get().setStation(assertMoveRequest.get().getOfficeLocation().getStation());
                assertMoveRequest.get().setStatus(AssertMoveStatus.APPROVED);
                assertMoveRequest.get().setUpdatedDate(new Timestamp(System.currentTimeMillis()));
                assertEntityRepository.save(optionalAssert.get());
                moveLocationRepository.save(assertMoveRequest.get());
                return ResponseEntity.ok(new CustomMessage("Assert moved to " + assertMoveRequest.get().getOfficeLocation().getName()));
            }
        }
        return ResponseEntity.ok(new CustomMessage("Request not found."));
    }


    //cool
    public ResponseEntity<Object> reject(Long assertRequestId) {
        Optional<AssertMoveRequest> assertMoveRequest = moveLocationRepository.findById(assertRequestId);
        if (assertMoveRequest.isPresent()) {
            assertMoveRequest.get().setStatus(AssertMoveStatus.REJECTED);
            assertMoveRequest.get().setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            moveLocationRepository.save(assertMoveRequest.get());
            return ResponseEntity.ok(new CustomMessage("Assert move request rejected."));
        }
        return ResponseEntity.ok(new CustomMessage("Request not found."));
    }

    public ResponseEntity<Object> addRequest(AssertMoveRequestDto assertMoveService) {
        Optional<AssertEntity> optionalAssert = assertEntityRepository.findById(assertMoveService.getAssertId());
        if (optionalAssert.isPresent()) {
            Optional<OfficeLocation> optionalOfficeLocation = officeLocationRepository.findById(assertMoveService.getLocationId());
            if (optionalOfficeLocation.isPresent()) {
                AssertMoveRequest assertMoveRequest = AssertMoveRequest.builder()
                        .assertEntity(optionalAssert.get())
                        .officeLocation(optionalOfficeLocation.get())

                        .reason(assertMoveService.getReason())
                        .status(AssertMoveStatus.PENDING)
                        .build();
                assertMoveRequest.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                assertMoveRequest.setUpdatedDate(assertMoveRequest.getCreatedDate());
                moveLocationRepository.save(assertMoveRequest);
                return ResponseEntity.ok(new CustomMessage("Assert move requested to " + optionalOfficeLocation.get().getName()));
            }
            return ResponseEntity.badRequest().body(new CustomMessage("Location not found."));
        }
        return ResponseEntity.badRequest().body(new CustomMessage("Assert not found."));
    }
}
