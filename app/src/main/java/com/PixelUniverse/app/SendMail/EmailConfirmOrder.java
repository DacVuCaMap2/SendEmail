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
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingCity;
    private List<EmailConfirmOrderDetails> emailConfirmOrderDetailsList;
}
