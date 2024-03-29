package io.getarrays.securecapita.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.GenerationType.IDENTITY;

@EqualsAndHashCode


@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
@Entity
@ToString


public class Store    implements Serializable {

    @Column(name = "store_id", columnDefinition = "BIGINT default 0")
    @Id
    @GeneratedValue(strategy = AUTO)

    private Integer  storeId;

    @Column(name="store_name")
    private String storeName;


//    @JsonIgnore
//    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn
//
//    private List<Inventory> inventories;


}
