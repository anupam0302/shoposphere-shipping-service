package com.shoposphere.shipping.service.impl;

import org.springframework.stereotype.Service;

import com.shoposphere.shipping.dto.ShippingRequest;
import com.shoposphere.shipping.dto.ShippingResponse;
import com.shoposphere.shipping.entity.ShippingDetails;
import com.shoposphere.shipping.entity.ShippingOrder;
import com.shoposphere.shipping.repository.ShippingOrderRepository;
import com.shoposphere.shipping.service.ShippingService;

@Service
public class ShippingServiceImpl implements ShippingService {

	private final ShippingOrderRepository shippingOrderRepository;

	public ShippingServiceImpl(ShippingOrderRepository shippingOrderRepository) {
		this.shippingOrderRepository = shippingOrderRepository;
	}

	@Override
	public ShippingResponse createShippingOrder(ShippingRequest request) {
		ShippingOrder shippingOrder = new ShippingOrder(request.getOrderId(), request.getShippingAddress());
		shippingOrder = shippingOrderRepository.save(shippingOrder);

		ShippingDetails shippingDetails = new ShippingDetails("1234567890", "UPS", "PENDING");
		shippingDetails.setShippingOrder(shippingOrder);
		shippingOrder.setShippingDetails(shippingDetails);

		shippingOrderRepository.save(shippingOrder); 

		return new ShippingResponse(shippingOrder.getId(), shippingOrder.getStatus(),
				shippingDetails.getTrackingNumber());
	}

	@Override
	public ShippingResponse updateShippingStatus(Long shippingOrderId, String status) {
		ShippingOrder shippingOrder = shippingOrderRepository.findById(shippingOrderId)
				.orElseThrow(() -> new RuntimeException("Shipping order not found"));

		shippingOrder.setStatus(status);
		shippingOrderRepository.save(shippingOrder);

		return new ShippingResponse(shippingOrder.getId(), shippingOrder.getStatus(),
				shippingOrder.getShippingDetails().getTrackingNumber());
	}
}
