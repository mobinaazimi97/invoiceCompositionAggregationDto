package com.mftplus.aggregationcustomer.customer;

import com.mftplus.aggregationcustomer.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Entity(name = "customerEntity")
@Table(name = "customer_tbl")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NotBlank(message = "{validation.firstname}")
    private String firstName;
//    @NotBlank(message ="{validation.lastname}")
    private String lastName;
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "{validation.email}")
    private String email;
    @Pattern(regexp = "^[0-9]{8,}$", message = "{validation.phone}")
    private String phone;

    @OneToOne
    private Order order;
}
