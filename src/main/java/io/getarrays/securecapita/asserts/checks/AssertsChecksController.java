package io.getarrays.securecapita.asserts.checks;

import io.getarrays.securecapita.asserts.service.AssertService;
import io.getarrays.securecapita.asserts.service.StationService;
import io.getarrays.securecapita.repository.implementation.UserRepository1;
import io.getarrays.securecapita.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/assert/checks")
@RequiredArgsConstructor
public class AssertsChecksController {
    private final UserRepository1 userRepository1;
    private final AssetsChecksService assetsChecksService;


}
