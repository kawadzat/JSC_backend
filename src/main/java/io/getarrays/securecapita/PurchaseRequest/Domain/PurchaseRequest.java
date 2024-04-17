package io.getarrays.securecapita.PurchaseRequest.Domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.getarrays.securecapita.asserts.model.Inspection;
import io.getarrays.securecapita.itauditing.Auditable;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
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
public class PurchaseRequest  extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
  private  String  purchaseRequestNumber;
  private  String  receiverEmail;
    private int  departmentCode;
   private String    reason	;

    @Column
    @Nonnull
    private String status;

    @OneToMany(mappedBy = "purchaseRequest")
    private List<PurchaseRequestProduct> purchaseRequestProducts;

}
