package io.getarrays.securecapita.asserts.officebearer;

import io.getarrays.securecapita.asserts.model.AssertEntity;
import io.getarrays.securecapita.domain.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.sql.Timestamp;

public class OfficeBConfirm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private AssertEntity asset;

    @ManyToOne
    private User confirmedBy;
    private Timestamp createdDate;
    private Timestamp updatedDate;


}
