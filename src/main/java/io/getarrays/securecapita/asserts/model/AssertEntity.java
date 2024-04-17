package io.getarrays.securecapita.asserts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.getarrays.securecapita.itauditing.Auditable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
@Builder
@Entity
@NoArgsConstructor

@Table(name = "assert")

public class AssertEntity    extends Auditable<String>  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    @Temporal(TemporalType.DATE)
    private Date date;
    private String assetDisc;
    private int assetNumber;
    private String  serialNumber;
    private String invoiceNumber;
    private  String assertType;
    private String location;
    private int quantity;

    private String initialRemarks;
    @OneToMany(mappedBy = "assertEntity")
    @JsonManagedReference
    private List<Inspection> inspections;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "station_id")
    private Station station;

}
