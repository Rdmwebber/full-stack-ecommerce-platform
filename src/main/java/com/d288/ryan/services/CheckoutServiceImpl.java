package com.d288.ryan.services;

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

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve cart info from dto

        Cart cart = purchase.getCart();

        // generate tracking number

        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        System.out.println("SETTING TRACKING");

        // populate cart with cartItems

        Set<CartItem> cartItems = purchase.getCartItems();
        cartItems.forEach(item -> cart.add(item));

        //change cart status

        cart.ordered();
        System.out.println("ORDERED");

        // populate customer with cart
        //Customer customer = purchase.getCustomer();
        Customer customer = cart.getCustomer();

        customer.add(cart);
        System.out.println("ABOUT TO SAVE");
        System.out.println(customer);

        // save to db
        customerRepository.save(customer);
        // return response
        System.out.println("BEFORe RETURN" + orderTrackingNumber);
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
