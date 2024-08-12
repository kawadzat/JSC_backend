package io.getarrays.securecapita.assertmoverequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.officelocations.OfficeLocation;
import io.jsonwebtoken.lang.Assert;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assert_move_requests")
public class AssertMoveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private OfficeLocation officeLocation;
    private String reason;
    @ManyToOne
    private AssertEntity assertEntity;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    @Enumerated(EnumType.STRING)
    private AssertMoveStatus status;
}
