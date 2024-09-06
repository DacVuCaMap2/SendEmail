package com.PixelUniverse.app.SendMail;

import lombok.Data;

import java.util.List;

@Data
public class EmailConfirmOrder {
    private String emailTo;
    private String firstName;
    private String lastName;
    private String domainStore;
    private String invoicePrefix;
    private String invoiceNo;
    private String shipping_address_1;
    private String shipping_address_2;
    private String shipping_city;
    private List<EmailConfirmOrderDetails> emailConfirmOrderDetailsList;
}
