package io.getarrays.securecapita.StockItemRequest.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
@NoArgsConstructor


@Getter
@Setter
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
@Builder
@Entity

public class StockItemRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String departmentCode;
    private String receiverEmail;
    private String reasonItem;
    private int numberItem;
    private String description;
    private int unitPrice;
    private String estimateValue;
    private int quantity;

}
