package io.getarrays.securecapita.asserts.controller;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.model.Inspection;
import io.getarrays.securecapita.asserts.model.StationName;
import io.getarrays.securecapita.asserts.service.AssertService;
import io.getarrays.securecapita.domain.HttpResponse;
import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public AssertEntity createAssert(@RequestBody AssertEntity newAssert) {
        AssertEntity createdAssert = assertService.createAssert(newAssert);
        return createdAssert;
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
public ResponseEntity<HttpResponse> addInvoiceToCustomer( @PathVariable("id") Long id, @RequestBody Inspection inspection) {
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
    public List<AssertEntity> getAllAsserts() {
        List<AssertEntity> listOfAllAsserts = (List<AssertEntity>) assertService.getAsserts();
        return listOfAllAsserts;
    }
    @GetMapping("/{stationName}")
    public List<AssertEntity> getAllAssertsByStation(@RequestParam("stationName") StationName stationName) {
        return assertService.getAllAssertsByStation(stationName);
    }

    @GetMapping("/getByStation")
    public ResponseEntity<?> getByStation(@RequestParam("stationId") Integer stationId){
        if(!StationName.existById(stationId)){
            return ResponseEntity.badRequest().body("Invalid Station Id Passed!");
        }
        return ResponseEntity.ok(assertService.getAllAssertsByStation(StationName.getById(stationId)));
    }




    @PutMapping("/update/{id}")
    public AssertEntity updateAssertEntity(@PathVariable("id") Long assertEntityId  ,@RequestBody   AssertEntity assertEntity) {

        AssertEntity oldAssertEntity =assertService.   getAssertEntityById(assertEntityId);

        oldAssertEntity.setDate(assertEntity.getDate());
        oldAssertEntity.setAssetDisc(assertEntity.getAssetDisc());


//        oldUser.setFirstName(user.getFirstName());
//        oldUser.setLastName(user.getLastName());


                AssertEntity updatedAssertEntity =assertService.updateAssertEntity(oldAssertEntity);



        return updatedAssertEntity;
    }

}
