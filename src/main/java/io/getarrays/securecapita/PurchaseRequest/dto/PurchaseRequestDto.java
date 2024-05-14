package io.getarrays.securecapita.PurchaseRequest.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PurchaseRequestDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String purchaseRequestNumber;
    private String receiverEmail;
    private int departmentCode;
    private String reason;
}

//can you tell me user flow?
//
//every deparment makes purchase request
//each purchase request has products requested
//for starting lets just make a purchase request
// requested for existing products right?
//they just make request
//a person in a department makes a request then forward the request to next person,
//person also forwards the request to last person
//at each stage a person appove or deny the request by signature

