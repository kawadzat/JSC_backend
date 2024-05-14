package io.getarrays.securecapita.PurchaseRequest.Domain;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
@Builder
@Entity
@NoArgsConstructor
@Table(name = "signatures")
public class Signature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private Long id;

    private String fullName;

}
