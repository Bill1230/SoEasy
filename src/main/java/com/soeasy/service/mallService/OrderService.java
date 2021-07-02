package com.soeasy.service.mallService;

import java.util.List;

import com.soeasy.model.Order.OrderBean;

public interface OrderService {

	OrderBean save(OrderBean order);

	OrderBean findByOrderId(Integer orderId);

	List<OrderBean> findAllwithOrder(Integer customerId);


}