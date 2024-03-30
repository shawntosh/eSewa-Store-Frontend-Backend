package com.esewashopping.customer;

import com.esewashopping.cart.entity.CartItem;
import com.esewashopping.shared.Role;
import com.esewashopping.shared.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.Getter;

import java.util.HashSet;

import lombok.NoArgsConstructor;


import java.util.Set;

@Entity
@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cId;

    private String fullName;

    private String email;

    private String password;

    private String address;

    private long contact;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();


}
