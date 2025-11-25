package com.shoposphere.shipping.dto;

import jakarta.validation.constraints.NotBlank;

public class ShippingRequest {

    @NotBlank
    private String orderId;

    @NotBlank
    private String shippingAddress;

	public ShippingRequest(@NotBlank String orderId, @NotBlank String shippingAddress) {
		super();
		this.orderId = orderId;
		this.shippingAddress = shippingAddress;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
}
