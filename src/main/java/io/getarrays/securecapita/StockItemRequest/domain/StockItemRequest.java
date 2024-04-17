package io.getarrays.securecapita.StockItemRequest.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.getarrays.securecapita.asserts.model.Inspection;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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
public class StockItemRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String departmentCode;
    private String receiverEmail;
    private String reasonItem;


    @Column
    @Nonnull
    private String status;

//    @OneToMany(mappedBy = "assertEntity")
//    @JsonManagedReference
//    private List<StockItemRequestProduct> stockItemRequestProducts;

    @OneToMany(mappedBy = "stockItemRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockItemRequestProduct> stockItemRequestProducts = new ArrayList<>();

}
