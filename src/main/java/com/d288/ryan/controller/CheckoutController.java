package com.d288.ryan.controller;

import com.d288.ryan.dto.Purchase;
import com.d288.ryan.dto.PurchaseResponse;
import com.d288.ryan.services.CheckoutService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService){
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {

        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        System.out.println("CheckoutReturn" + purchaseResponse);
        return purchaseResponse;
    }
}
