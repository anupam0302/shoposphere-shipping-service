package com.shoposphere.shipping.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shoposphere.shipping.dto.ShippingRequest;
import com.shoposphere.shipping.dto.ShippingResponse;
import com.shoposphere.shipping.service.ShippingService;

@RestController
@RequestMapping("/api/shipping")
public class ShippingController {

    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ShippingResponse createShippingOrder(@RequestBody ShippingRequest request) {
        return shippingService.createShippingOrder(request);
    }

    @PutMapping("/{shippingOrderId}/status")
    public ShippingResponse updateShippingStatus(@PathVariable Long shippingOrderId, @RequestParam String status) {
        return shippingService.updateShippingStatus(shippingOrderId, status);
    }
}
