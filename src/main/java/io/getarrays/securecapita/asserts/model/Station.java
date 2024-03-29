package io.getarrays.securecapita.asserts.model;

import io.getarrays.securecapita.domain.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.GenerationType.AUTO;

@EqualsAndHashCode
@Getter
@Setter


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "station")
public class Station {

    @Column(name = "station_id", columnDefinition = "BIGINT default 0")
    @Id
    @GeneratedValue(strategy = AUTO)

    private Integer  station_id;

    @Column(name="station_name")
    @Enumerated(EnumType.STRING)
    private StationName stationName;


    @OneToMany(mappedBy = "station")
    @Builder.Default
    private Set<AssertEntity> asserts=new HashSet<>();
    @OneToOne(mappedBy = "station")
    private User user;

    public void add(AssertEntity assertEntity) {
        asserts.add(assertEntity);
    }
}
