package io.getarrays.securecapita.asserts.prerunner;

import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.asserts.repo.StationRepository;
import io.getarrays.securecapita.domain.Role;
import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.repository.implementation.RoleRepository1;
import io.getarrays.securecapita.repository.implementation.UserRepository1;
import io.getarrays.securecapita.roles.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StationPreRunner implements CommandLineRunner {
    private final StationRepository stationRepository;
    private final UserRepository1 userRepository1;
    private final RoleRepository1 roleRepository1;

    @Override
    public void run(String... args) throws Exception {
        ArrayList<Station> stationArrayList = new ArrayList<>();
        stationArrayList.add(Station.builder().station_id(1L).stationName("HARARE_HIGH_COURT").build());
        stationArrayList.add(Station.builder().station_id(2L).stationName("BULAWAYO_HIGH_COURT").build());
        stationArrayList.add(Station.builder().station_id(3L).stationName("MASVINGO_HIGH_COURT").build());
        stationArrayList.add(Station.builder().station_id(4L).stationName("MUTARE_HIGH_COURT").build());
        stationArrayList.add(Station.builder().station_id(5L).stationName("CHINHOYI_HIGH_COURT").build());
        stationArrayList.add(Station.builder().station_id(6L).stationName("SUPRME_COURT").build());
        stationRepository.saveAll(stationArrayList);
    }
}
