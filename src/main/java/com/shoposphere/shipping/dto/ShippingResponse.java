package com.shoposphere.shipping.dto;


public class ShippingResponse {

    private Long shippingOrderId;
    private String status;
    private String trackingNumber;
    
	public ShippingResponse(Long shippingOrderId, String status, String trackingNumber) {
		this.shippingOrderId = shippingOrderId;
		this.status = status;
		this.trackingNumber = trackingNumber;
	}
    
	public Long getShippingOrderId() {
		return shippingOrderId;
	}
	public void setShippingOrderId(Long shippingOrderId) {
		this.shippingOrderId = shippingOrderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
}
