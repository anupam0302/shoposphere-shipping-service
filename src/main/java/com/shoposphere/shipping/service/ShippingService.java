package com.shoposphere.shipping.service;

import com.shoposphere.shipping.dto.ShippingRequest;
import com.shoposphere.shipping.dto.ShippingResponse;

public interface ShippingService {
	
    ShippingResponse createShippingOrder(ShippingRequest request);

    ShippingResponse updateShippingStatus(Long shippingOrderId, String status);

}
