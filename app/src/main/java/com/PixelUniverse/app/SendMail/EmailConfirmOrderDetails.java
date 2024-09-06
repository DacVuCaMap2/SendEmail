package com.PixelUniverse.app.SendMail;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailConfirmOrderDetails {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String options;

    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private Double quantity;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Double price;

    @Min(value = 0, message = "Fee ship must be greater than or equal to 0")
    private Double feeship;

    @Min(value = 0, message = "Total must be greater than or equal to 0")
    private Double total;
}
