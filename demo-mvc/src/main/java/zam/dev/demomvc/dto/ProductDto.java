package zam.dev.demomvc.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductDto{


    @NotEmpty(message = "Name can not empty")
    private String name;

    @NotEmpty(message = "Description can not empty")
    private String description;

    @NotNull(message = "Prize can not null")
    private double prize;

    @NotNull(message = "Quantity can not null")
    private long quantity;

}