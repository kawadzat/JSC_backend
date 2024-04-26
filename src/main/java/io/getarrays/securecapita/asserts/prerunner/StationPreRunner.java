package io.getarrays.securecapita.asserts.prerunner;

import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.asserts.repo.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class StationPreRunner implements CommandLineRunner {
    private final StationRepository stationRepository;
    @Override
    public void run(String... args) throws Exception {
        if(stationRepository.count()<1){
            ArrayList<Station> stationArrayList =new ArrayList<>();
            stationArrayList.add(Station.builder().stationName("HARARE_HIGH_COURT").build());
            stationArrayList.add(Station.builder().stationName("BULAWAYO_HIGH_COURT").build());
            stationArrayList.add(Station.builder().stationName("MASVINGO_HIGH_COURT").build());
            stationArrayList.add(Station.builder().stationName("MUTARE_HIGH_COURT").build());
            stationRepository.saveAll(stationArrayList);
        }
    }
}
