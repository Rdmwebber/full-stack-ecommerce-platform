package com.d288.ryan.services;

import com.d288.ryan.dto.Purchase;
import com.d288.ryan.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
