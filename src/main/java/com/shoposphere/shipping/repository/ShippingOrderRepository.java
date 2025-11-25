package com.shoposphere.shipping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoposphere.shipping.entity.ShippingOrder;

public interface ShippingOrderRepository extends JpaRepository<ShippingOrder, Long>{
	
	Optional<ShippingOrder> findByOrderId(String orderId);

}
