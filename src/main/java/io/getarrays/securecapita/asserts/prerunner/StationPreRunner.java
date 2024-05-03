package io.getarrays.securecapita.asserts.prerunner;

import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.asserts.repo.StationRepository;
import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.repository.implementation.UserRepository1;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class StationPreRunner implements CommandLineRunner {
    private final StationRepository stationRepository;
    private final UserRepository1 userRepository1;
    @Override
    public void run(String... args) throws Exception {
        ArrayList<Station> stationArrayList = new ArrayList<>();
        stationArrayList.add(Station.builder().station_id(1).stationName("HARARE_HIGH_COURT").build());
        stationArrayList.add(Station.builder().station_id(2).stationName("BULAWAYO_HIGH_COURT").build());
        stationArrayList.add(Station.builder().station_id(3).stationName("MASVINGO_HIGH_COURT").build());
        stationArrayList.add(Station.builder().station_id(4).stationName("MUTARE_HIGH_COURT").build());
        stationArrayList.add(Station.builder().station_id(5).stationName("CHINHOYI_HIGH_COURT").build());
        stationArrayList.add(Station.builder().station_id(6).stationName("SUPRME_COURT").build());
        stationRepository.saveAll(stationArrayList);

        //provide basic role to user with empty roles:
        ArrayList<User> noRoleUsers=userRepository1.findUsersWithNoRoles();
    }
}
