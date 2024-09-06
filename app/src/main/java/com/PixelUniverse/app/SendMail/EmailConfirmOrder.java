package com.PixelUniverse.app.SendMail;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class EmailConfirmOrder {
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String emailTo;
    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
    @NotBlank(message = "Domain store cannot be blank")
    private String domainStore;
    @NotBlank(message = "Invoice prefix cannot be blank")
    private String invoicePrefix;
    @NotBlank(message = "Invoice no cannot be blank")
    private String invoiceNo;
    @NotBlank(message = "Invoice shipping address 1 cannot be blank")
    private String shippingAddress1;
    @NotBlank(message = "Invoice shipping address 2 cannot be blank")
    private String shippingAddress2;
    @NotBlank(message = "Shipping city cannot be blank")
    private String shippingCity;
    private List<@Valid EmailConfirmOrderDetails> emailConfirmOrderDetailsList;
}
