package com.mftplus.aggregationcustomer.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "productEntity")
@Table(name = "product_tbl")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{validation.name}")
    private String name;
    @NotBlank(message = "{validation.brand}")
    private String brand;
//    @Pattern(regexp = "^[0-9]{2,}$", message = "{validation.serialNumber}")
//    private String serialNumber;
//    @NotBlank(message = "{validation.price}")
    private double price;
}
