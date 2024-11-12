package io.getarrays.securecapita.dto;

import lombok.Data;

@Data
public class AssetSearchCriteriaDTO {
    private String assetDisc;
    private String assetNumber;
    private String invoiceNumber;
    private String location;
    private String officeLocation;
}
