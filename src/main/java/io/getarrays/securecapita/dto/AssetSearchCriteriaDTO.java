package io.getarrays.securecapita.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AssetSearchCriteriaDTO {
    private String assetDisc;
    private String assetNumber;
    private String invoiceNumber;
    private String location;
    private String officeLocation;

    private Date date;
}
