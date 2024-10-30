package io.getarrays.securecapita.asserts.controller;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.model.Inspection;
import io.getarrays.securecapita.asserts.model.SpecificationInput;
import io.getarrays.securecapita.asserts.service.AssertService;
import io.getarrays.securecapita.domain.HttpResponse;
import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.exception.CustomMessage;
import io.getarrays.securecapita.jasper.pdf.JasperPdfService;
import io.getarrays.securecapita.roles.prerunner.ROLE_AUTH;
import io.getarrays.securecapita.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/assert")
@RequiredArgsConstructor
public class AssertController {
    private final UserService userService;
    private final AssertService assertService;
    private JasperPdfService jasperPdfService;
    @PostMapping("/create")
    public ResponseEntity<?> createAssert(@RequestBody @Validated AssertEntity newAssert) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains(ROLE_AUTH.CREATE_ASSET.name()))) {
            return assertService.createAssert(newAssert);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CustomMessage("You don't have permission."));

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
    public ResponseEntity<?> addInvoiceToCustomer(@PathVariable("id") Long id, @RequestBody @Validated Inspection inspection) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains(ROLE_AUTH.CREATE_ASSET.name()))) {
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
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CustomMessage("You don't have permission."));
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
    public ResponseEntity<?> getAllAsserts(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains(ROLE_AUTH.ALL_STATION.name()))) {
            return ResponseEntity.ok(assertService.getAsserts(page, size));
        }
//        else if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains(ROLE_AUTH.VIEW_ASSET.name()))) {
//            return assertService.getAssertsForOwnStation(page, size);
//        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CustomMessage("You don't have permission."));
    }

//    @GetMapping("/{stationName}")
//    public ResponseEntity<?> getAllAssertsByStation(@RequestParam("stationId") Long stationId, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains(ROLE_AUTH.VIEW_ASSET.name()))) {
//            return assertService.getAllAssertsByStation(stationId, PageRequest.of(page, size));
//        }
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CustomMessage("You don't have permission."));
//
//    }

    @GetMapping("/getByStation")
    public ResponseEntity<?> getByStation(@RequestParam(name = "stationId", required = false) Long stationId,@RequestParam(name = "query", defaultValue = "",required = false) String query, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (stationId != null) {
            if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains(ROLE_AUTH.VIEW_ASSET.name()))) {
                return assertService.getAllAssertsByStation(((UserDTO) authentication.getPrincipal()).getId(), stationId, query,PageRequest.of(page, size, Sort.by("lastModifiedDate").descending()));
            } else {
                return assertService.getAllAssertsByUserStation(((UserDTO) authentication.getPrincipal()).getId(), stationId,query, PageRequest.of(page, size, Sort.by("lastModifiedDate").descending()));
            }
        } else {
            return assertService.getAllAssertsByUserStation(((UserDTO) authentication.getPrincipal()).getId(), query,PageRequest.of(page, size, Sort.by("lastModifiedDate").descending()));
        }
    }

    @GetMapping("/getByStationMin")
    public ResponseEntity<?> getByStationMin(@RequestParam(name = "stationId", required = true) Long stationId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains(ROLE_AUTH.VIEW_ASSET.name()))) {
            return assertService.getAllAssertsByStationMin(stationId);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CustomMessage("You don't have permission."));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAssertEntity(@PathVariable("id") Long assertEntityId, @RequestBody AssertEntity assertEntity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch((r) -> r.getAuthority().contains(ROLE_AUTH.CREATE_ASSET.name()))) {
            AssertEntity oldAssertEntity = assertService.getAssertEntityById(assertEntityId);
            oldAssertEntity.setDate(new Timestamp(System.currentTimeMillis()));
            oldAssertEntity.setAssetDisc(assertEntity.getAssetDisc());
            AssertEntity updatedAssertEntity = assertService.updateAssertEntity(oldAssertEntity);
            return ResponseEntity.ok(updatedAssertEntity);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CustomMessage("You don't have permission."));

    }

    //stat
    @GetMapping("/stats")
    public ResponseEntity<?> getOverallStats() {
        return assertService.getStats();
    }

    //statsToken
    @GetMapping("/statsToken")
    public ResponseEntity<?> getOverallStatsToken() {
        return assertService.getStatsToken();
    }







    @GetMapping("/getAllBydescript")
    public List<AssertEntity> getAllAssetsByassetDisc(){

        List<AssertEntity>  listOfAllAssetsByDisc =  assertService.getAllAssetsByassetDisc()  ;      // userServiceObject.getAllUsers();

        return  listOfAllAssetsByDisc ;
    }


@GetMapping
    List<AssertEntity>getByassetDisc(){
//    Specification<AssertEntity>specification=getSpecification();
    return   assertService.getAllAssetsByassetDisc( );
}


@GetMapping("/ByEquals")
List<AssertEntity>ByEquals(@RequestBody SpecificationInput  specificationInput){

    return  null; //
}


}

