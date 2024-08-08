package io.getarrays.securecapita.asserts.prerunner;

import io.getarrays.securecapita.asserts.model.Province;
import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.asserts.repo.ProvinceEntityRepo;
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
    private final ProvinceEntityRepo provinceEntityRepo;

    @Override
    public void run(String... args) throws Exception {
        List<Province> provinces = new ArrayList<>();
        provinces.addAll(List.of(Province.builder()
                        .id(1L)
                        .name("MASHOLAND WEST")
                        .build(),
                Province.builder()
                        .id(2L)
                        .name("MASHONALAND CENTRAL")
                        .build(), Province.builder()
                        .id(3L)
                        .name("BULAWAYO")
                        .build(), Province.builder()
                        .id(4L)
                        .name("MIDLANDS")
                        .build(), Province.builder()
                        .id(5L)
                        .name("MASVINGO")
                        .build(), Province.builder()
                        .id(6L)
                        .name("MASHONALAND EAST")
                        .build(), Province.builder()
                        .id(7L)
                        .name("MATEBELELAND NORTH")
                        .build(), Province.builder()
                        .id(8L)
                        .name("MATEBELELAND SOUTH")
                        .build(), Province.builder()
                        .id(9L)
                        .name("HARARE")
                        .build(), Province.builder()
                        .id(10L)
                        .name("MANICALAND")

                        .build(), Province.builder()
                        .id(11L)
                        .name("SUPERIOR COURTS")
                        .build()));

        List<Province> provinceList = provinceEntityRepo.saveAll(provinces);

        ArrayList<Station> stationArrayList = new ArrayList<>();
        stationArrayList.add(Station.builder().station_id(1L).province(getProvince(provinceList,11L)).stationName("HARARE_HIGH_COURT").build());
        stationArrayList.add(Station.builder().station_id(2L).province(getProvince(provinceList,11L)).stationName("BULAWAYO_HIGH_COURT").build());
        stationArrayList.add(Station.builder().station_id(3L).province(getProvince(provinceList,11L)).stationName("MASVINGO_HIGH_COURT").build());
        stationArrayList.add(Station.builder().station_id(4L).province(getProvince(provinceList,11L)).stationName("MUTARE_HIGH_COURT").build());
        stationArrayList.add(Station.builder().station_id(5L).province(getProvince(provinceList,11L)).stationName("CHINHOYI_HIGH_COURT").build());
        stationArrayList.add(Station.builder().station_id(6L).province(getProvince(provinceList,11L)).stationName("MASVINGO_COURT").build());
        stationArrayList.add(Station.builder().station_id(7L).province(getProvince(provinceList,11L)).stationName("SUPREME_COURT").build());
        stationArrayList.add(Station.builder().station_id(8L).province(getProvince(provinceList,11L)).stationName("HARARE_LABOUR_COURT").build());
        stationArrayList.add(Station.builder().station_id(9L).province(getProvince(provinceList,11L)).stationName("BULAWAYO_LABOUR_COURT").build());
        stationArrayList.add(Station.builder().station_id(10L).province(getProvince(provinceList,11L)).stationName("GWERU_LABOUR_COURT").build());
        stationArrayList.add(Station.builder().station_id(11L).province(getProvince(provinceList,11L)).stationName("MASTER_HIGH_HARARE").build());
        stationArrayList.add(Station.builder().station_id(12L).province(getProvince(provinceList,11L)).stationName("MASTER_HIGH_BULAWAYO").build());
        stationArrayList.add(Station.builder().station_id(13L).province(getProvince(provinceList,11L)).stationName("COMMERCIAL_COURT").build());
        stationArrayList.add(Station.builder().station_id(14L).province(getProvince(provinceList,11L)).stationName("SHERIF_COURT").build());
        stationArrayList.add(Station.builder().station_id(15L).province(getProvince(provinceList,11L)).stationName("COMMERCIAL_COURT").build());
        stationArrayList.add(Station.builder().station_id(16L).province(getProvince(provinceList,11L)).stationName("CONSTITUTIONAL_COURT").build());







        stationArrayList.add(Station.builder().station_id(17L).province(getProvince(provinceList,9L)).stationName("BEATRICE_CIRCUT_COURT").build());
        stationArrayList.add(Station.builder().station_id(18L).province(getProvince(provinceList,9L)).stationName("CHIEF_MAG_OFFICE").build());
        stationArrayList.add(Station.builder().station_id(19L).province(getProvince(provinceList,9L)).stationName("CHITUNGWIZA_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(20L).province(getProvince(provinceList,9L)).stationName("HARARE_CIVIL_COURT").build());
        stationArrayList.add(Station.builder().station_id(21L).province(getProvince(provinceList,9L)).stationName("HARARE_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(22L).province(getProvince(provinceList,9L)).stationName("MBARE_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(23L).province(getProvince(provinceList,9L)).stationName("NORTON_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(24L).province(getProvince(provinceList,9L)).stationName("EPWORTH_MAG_COURT").build());






        stationArrayList.add(Station.builder().station_id(25L).province(getProvince(provinceList,8L)).stationName("BEITBRIDGE_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(26L).province(getProvince(provinceList,8L)).stationName("ESIGODINI_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(27L).province(getProvince(provinceList,8L)).stationName("FILABUSI_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(28L).province(getProvince(provinceList,8L)).stationName("GWANDA_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(29L).province(getProvince(provinceList,8L)).stationName("KEZI_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(30L).province(getProvince(provinceList,8L)).stationName("PLUMTREE_MAG_COURT").build());



       stationArrayList.add(Station.builder().station_id(31L).province(getProvince(provinceList,1L)).stationName("BANKET_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(32L).province(getProvince(provinceList,1L)).stationName("BINDURA_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(33L).province(getProvince(provinceList,1L)).stationName("CENTENARY_MAG_COURT").build());


        stationArrayList.add(Station.builder().station_id(34L).province(getProvince(provinceList,7L)).stationName("BINGA_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(35L).province(getProvince(provinceList,7L)).stationName("HWANGE_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(36L).province(getProvince(provinceList,7L)).stationName("LUPANE_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(37L).province(getProvince(provinceList,7L)).stationName("_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(38L).province(getProvince(provinceList,7L)).stationName("SHAMVA_MAG_COURT").build());


        stationArrayList.add(Station.builder().station_id(39L).province(getProvince(provinceList,3L)).stationName("BULAWAYO_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(40L).province(getProvince(provinceList,3L)).stationName("INYATI_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(40L).province(getProvince(provinceList,3L)).stationName("NKAYI_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(41L).province(getProvince(provinceList,3L)).stationName("TSHOLOTSHO_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(42L).province(getProvince(provinceList,3L)).stationName("WESTERN_COMMONAGE_COURT").build());




        stationArrayList.add(Station.builder().station_id(43L).province(getProvince(provinceList,3L)).stationName("BULAWAYO_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(44L).province(getProvince(provinceList,3L)).stationName("INYATI_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(45L).province(getProvince(provinceList,3L)).stationName("NKAYI_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(46L).province(getProvince(provinceList,3L)).stationName("TSHOLOTSHO_MAG_COURT").build());
        stationArrayList.add(Station.builder().station_id(47L).province(getProvince(provinceList,3L)).stationName("WESTERN_COMMONAGE_COURT").build());




        stationRepository.saveAll(stationArrayList);
    }

    private Province getProvince(List<Province> provinces,Long id){
        return provinces.stream().findFirst().filter(province -> province.getId().equals(id)).get();
    }
}
