package com.PixelUniverse.app.SendMail;

import lombok.Data;

@Data
public class EmailConfirmOrderDetails {
    private String name;
    private String options;
    private Double quantity;
    private Double price;
    private Double feeship;
    private Double total;
}
