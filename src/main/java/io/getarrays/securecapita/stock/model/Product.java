package io.getarrays.securecapita.stock.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
@Entity
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)

public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    @Column(name ="product_id")
    private Integer productId;
    @Temporal(TemporalType.DATE)
    @Column(name ="received_date")
    private Date recievedDate;
    @Column(name="product_name")
    private String productName;
    @Column(name = "product_quantity", columnDefinition = "int default 0")
    private int productQuantity;

    @Column(name="stock_status")
    private String stockStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="inventory_id")
    private Inventory inventory;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    private Order order;
//
//    @ManyToOne(fetch = FetchType.LAZY )
//    @JoinColumn(name = "supplier_id")
//    private  Supplier supplier;


    public void calculateStockStatus() {
        if (productQuantity > 0) {
            stockStatus = "In Stock";
        } else {
            stockStatus = "Out of Stock";
        }
    }

}
