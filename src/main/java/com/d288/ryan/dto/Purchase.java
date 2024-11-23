package com.d288.ryan.dto;

import com.d288.ryan.entities.Cart;
import com.d288.ryan.entities.CartItem;
import com.d288.ryan.entities.Customer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Purchase {

    private Customer customer;

    private Cart cart;

    private Set<CartItem> cartItems;
}
