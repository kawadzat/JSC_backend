package io.getarrays.securecapita.PurchaseRequest.Domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.getarrays.securecapita.asserts.model.AssertEntity;
import jakarta.persistence.*;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_DEFAULT)
@Entity

public class PurchaseRequestProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private Long id;
    private int   itemNumber;
    private String    ItemDescription;
    private String  unitPrice	;
    private String    estimateValue	;
    private int       quantity;

    @ManyToOne
    @JoinColumn(name = "purchaseRequest_id",nullable = false)
    private PurchaseRequest purchaseRequest;

}
