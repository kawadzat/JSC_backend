package io.getarrays.securecapita.PurchaseRequest.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PurchaseRequestDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private  String  purchaseRequestNumber;
    private  String  receiverEmail;
    private int  departmentCode;
    private String    reason	;


}
