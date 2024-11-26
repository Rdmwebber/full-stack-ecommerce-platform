package com.d288.ryan.services;

import com.d288.ryan.dao.CartRepository;
import com.d288.ryan.dao.CustomerRepository;
import com.d288.ryan.dto.Purchase;
import com.d288.ryan.dto.PurchaseResponse;
import com.d288.ryan.entities.Cart;
import com.d288.ryan.entities.CartItem;
import com.d288.ryan.entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;


@Service
public class CheckoutServiceImpl implements CheckoutService{


    private CustomerRepository customerRepository;
    private CartRepository cartRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve cart info from dto

        Cart cart = purchase.getCart();

        cart.setId(null);

        // retrieve customer from dto

        Customer customer = purchase.getCustomer();


        // generate tracking number

        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);


        // populate cart with cartItems

        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> {
            cart.add(item);
            item.getExcursions().forEach(excursion -> excursion.setVacation(item.getVacation()));
        });

        //populate cart with customer

        cart.setCustomer(customer);

        //add cart to customer

        customer.add(cart);


        //change cart status

        cart.ordered();

        // save to db
        //customerRepository.save(customer);
        cartRepository.save(cart);
        // return response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
