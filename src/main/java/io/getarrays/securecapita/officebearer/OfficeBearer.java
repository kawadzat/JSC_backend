package io.getarrays.securecapita.officebearer;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.officelocations.OfficeLocation;
import jakarta.persistence.*;
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
@Table(name = "office_bearer")
public class OfficeBearer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private OfficeLocation officeLocation;

    @ManyToOne
    private User checkedBy;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
