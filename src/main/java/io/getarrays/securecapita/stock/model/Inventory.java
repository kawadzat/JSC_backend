package io.getarrays.securecapita.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@EqualsAndHashCode
@Getter
@Setter
@ToString

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Inventory  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="inventory_id")
    private Integer inventoryId;
    @Column(name = "inventory_date")
    @Temporal(TemporalType.DATE)
    private Date inventoryDate;
    @Column(name = "inventory_name")
    private String inventoryName;
    @Column(name="inventory_code")
    private String inventoryCode;

    @Column(name="reorder_level")
    private Integer reorderLevel;
    @Column(name="alert_level")
    private Integer alertLevel;


//    @JsonIgnore
//    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Product> products;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

}
