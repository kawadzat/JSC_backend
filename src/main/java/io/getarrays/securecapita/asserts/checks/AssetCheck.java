package io.getarrays.securecapita.asserts.checks;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
@Builder
@Entity
@NoArgsConstructor

@Table(name = "assert_check")
public class AssetCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Station station;
    @ManyToOne
    private User checkedBy;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
