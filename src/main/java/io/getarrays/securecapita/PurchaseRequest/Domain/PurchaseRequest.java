package io.getarrays.securecapita.PurchaseRequest.Domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.getarrays.securecapita.asserts.model.Inspection;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
@Builder
@Entity

public class PurchaseRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private Long id;
    private Date date;
  private  String  purchaseRequestNumber;
    private  String  receiverEmail;
    private int  departmentCode;

   private String    Reason	;
       @OneToMany(mappedBy = "purchaseRequest")
    private List<PurchaseRequestProduct> purchaseRequestProducts;

}
