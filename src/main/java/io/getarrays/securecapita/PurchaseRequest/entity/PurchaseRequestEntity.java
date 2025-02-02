package io.getarrays.securecapita.PurchaseRequest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.getarrays.securecapita.asserts.model.Station;
import io.getarrays.securecapita.itauditing.Auditable;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
@Builder
@Entity
@NoArgsConstructor
public class PurchaseRequestEntity extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String purchaseRequestNumber;
    private String receiverEmail;
    private int departmentCode;
    private String reason;

    @Column
    @Nonnull
    private String status;

    @OneToMany(mappedBy = "purchaseRequest", cascade = CascadeType.ALL)
    private List<PurchaseRequestItemEntity> purchaseRequestProducts;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "station_id")
    private Station station;
    @Transient
    @NotNull
    private Long selectedStationID;

    //lets assin on profile first

}
