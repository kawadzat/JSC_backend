package io.getarrays.securecapita.itinventory;

import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.exception.CustomMessage;
import io.getarrays.securecapita.task.TaskDto;
import io.getarrays.securecapita.task.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/laptop")
@RequiredArgsConstructor
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    @PostMapping
    public ResponseEntity<CustomMessage> createLaptop(@AuthenticationPrincipal UserDTO currentUser,
                                                    @RequestBody @Valid LaptopDto laptopDto) throws Exception {
        return ResponseEntity.ok(new CustomMessage("Laptop Created Successfully", laptopService.createLaptop(currentUser,
                laptopDto)));
    }






}
