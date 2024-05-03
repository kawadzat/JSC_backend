package io.getarrays.securecapita.asserts.controller;

import com.twilio.http.Response;
import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.model.Inspection;
import io.getarrays.securecapita.asserts.service.AssertService;
import io.getarrays.securecapita.domain.HttpResponse;
import io.getarrays.securecapita.dto.Stats;
import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/assert")
@RequiredArgsConstructor
public class AssertController {
    private final UserService userService;
    @Autowired
    AssertService assertService;

    @PostMapping("/create")
    public ResponseEntity<?> createAssert(@RequestBody @Validated AssertEntity newAssert) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains("CREATE:ASSERT"))) {
            AssertEntity createdAssert = assertService.createAssert(newAssert);
            return ResponseEntity.ok(createdAssert);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have permission.");

    }

    //    @PostMapping("/invoice/addtoassert/{id}")
//    public ResponseEntity<HttpResponse> addInvoiceToCustomer(@AuthenticationPrincipal UserDTO user, @PathVariable("id") Long id, @RequestBody Inspection inspection) {
//        assertService.addInspectionToAseert(id, inspection);
//        return ResponseEntity.ok(
//                HttpResponse.builder()
//                        .timeStamp(now().toString())
//                        .data(of("user", userService.getUserByEmail(user.getEmail()),
//                                "assert", assertService.getAsserts()))
//                        .message(String.format("Inspection added to assert with ID: %s", id))
//                        .status(OK)
//                        .statusCode(OK.value())
//                        .build());
//    }
    @PostMapping("/addtoassert/{id}")
    public ResponseEntity<?> addInvoiceToCustomer(@PathVariable("id") Long id, @RequestBody Inspection inspection) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains("CREATE:ASSERT"))) {
            assertService.addInspectionToAssertEntity(id, inspection);
//    UserDTO userDTO = userService.getUserByEmail(user.getEmail());
            List<AssertEntity> asserts = (List<AssertEntity>) assertService.getAsserts();

            HttpResponse response = HttpResponse.builder()
                    .timeStamp(LocalDateTime.now().toString())
//            .data(Map.of("user", userDTO, "assert", asserts))
                    .message(String.format("Inspection added to assert with ID: %s", id))
                    .status(OK)
                    .statusCode(OK.value())
                    .build();

            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have permission.");
    }


    @GetMapping("/inspection/get/{id}")
    public ResponseEntity<HttpResponse> getInspection(@AuthenticationPrincipal UserDTO user, @PathVariable("id") Long id) {
        Inspection inspection = assertService.getInspection(id);
        UserDTO userEntity = userService.getUserByEmail(user.getEmail());
        AssertEntity assertEntity = inspection.getAssertEntity();

        HttpResponse response = HttpResponse.builder()
                .timeStamp(LocalDateTime.now().toString())
                .data(Map.of("user", userEntity, "inspection", inspection, "assert", assertEntity))
                .message("Inspection retrieved")
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    //  @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    public ResponseEntity<?> getAllAsserts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains("VIEW:ASSERT"))) {
            return ResponseEntity.ok(assertService.getAsserts());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have permission.");
    }

    @GetMapping("/{stationName}")
    public ResponseEntity<?> getAllAssertsByStation(@RequestParam("stationId") Long stationId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains("VIEW:ASSERT"))) {
            return assertService.getAllAssertsByStation(stationId);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have permission.");

    }

    @GetMapping("/getByStation")
    public ResponseEntity<?> getByStation(@RequestParam("stationId") Long stationId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains("VIEW:ASSERT"))) {
            return assertService.getAllAssertsByStation(stationId);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have permission.");

    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAssertEntity(@PathVariable("id") Long assertEntityId, @RequestBody AssertEntity assertEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains("UPDATE:ASSERT"))) {
            AssertEntity oldAssertEntity = assertService.getAssertEntityById(assertEntityId);
            oldAssertEntity.setDate(assertEntity.getDate());
            oldAssertEntity.setAssetDisc(assertEntity.getAssetDisc());
            AssertEntity updatedAssertEntity = assertService.updateAssertEntity(oldAssertEntity);
            return ResponseEntity.ok(updatedAssertEntity);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You don't have permission.");

    }

    //stat
    @GetMapping("/stats")
    public ResponseEntity<?> getOverallStats() {
        return assertService.getStats();
    }

}
