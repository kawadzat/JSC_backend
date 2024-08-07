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
        stationArrayList.add(Station.builder().station_id(7L).stationName("SHURUNGWI_COURT").build());
        stationArrayList.add(Station.builder().station_id(8L).stationName("HARARE_LABOUR_COURT").build());
        stationArrayList.add(Station.builder().station_id(9L).stationName("CHEGUTU_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(10L).stationName("MVUMA_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(11L).stationName("HARARE_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(12L).stationName("MASVINGO_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(13L).stationName("COMMERCIAL_COURT").build());
        stationArrayList.add(Station.builder().station_id(14L).stationName("SHERIF_COURT").build());
        stationArrayList.add(Station.builder().station_id(15L).stationName("COMMERCIAL_COURT").build());
        stationArrayList.add(Station.builder().station_id(16L).stationName("NORTON_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(17L).stationName("KWEKWE_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(18L).stationName("BULAWAYO_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(19L).stationName("GOKWE_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(20L).stationName("MABVUKU_TAFARA_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(21L).stationName("MBERENGWA_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(22L).stationName("ZVISHAVANE_MAG_COURT").build());


        stationArrayList.add(Station.builder().station_id(23L).stationName("CHIVHU_MAG_COURT").build());

        stationArrayList.add(Station.builder().station_id(24L).stationName("GOROMONZI_MAG_COURT").build());

        stationArrayList.add(Station.builder().station_id(25L).stationName("HWEDZA_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(26L).stationName("KOTWA_MAG_COURT").build());

        stationArrayList.add(Station.builder().station_id(27L).stationName("MUREWA_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(28L).stationName("MARONDERA_MAG_COURT").build());

        stationArrayList.add(Station.builder().station_id(29L).stationName("MUTAWATAWA_MAG_COURT").build());

        stationArrayList.add(Station.builder().station_id(30L).stationName("MUTOKO_MAG_COURT").build());


        stationArrayList.add(Station.builder().station_id(31L).stationName("BANKET_MAG_COURT").build());





        stationArrayList.add(Station.builder().station_id(32L).stationName("BINDURA_MAG_COURT").build());



        stationArrayList.add(Station.builder().station_id(33L).stationName("CENTENARY_MAG_COURT").build());




        stationArrayList.add(Station.builder().station_id(34L).stationName("CONCESSION_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(35L).stationName("GURUVE_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(36L).stationName("MTDARWIN_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(37L).stationName("RUSHINGA_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(38L).stationName("SHAMVA_MAG_COURT").build());




        stationArrayList.add(Station.builder().station_id(39L).stationName("BULAWAYO_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(40L).stationName("INYATI_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(40L).stationName("NKAYI_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(41L).stationName("TSHOLOTSHO_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(42L).stationName("WESTERN_COMMONAGE_COURT").build());




        




        stationRepository.saveAll(stationArrayList);
    }
}
