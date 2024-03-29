package io.getarrays.securecapita.asserts.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
@EqualsAndHashCode
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity

public class Inspection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String remarks;


//    @ManyToOne
//    @JoinColumn(name = "assert_id")
//    //@JoinColumn(name = "assert_entity_id")
//    private AssertEntity assertEntity;


    @ManyToOne
    @JoinColumn(name = "assert_id")
    @JsonBackReference
    private AssertEntity assertEntity;


}
