package ccc.xxx.order.service;

import ccc.xxx.order.dto.OrderQueryDTO;
import ccc.xxx.order.entity.Order;
import ccc.xxx.order.mapper.OrderMapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Resource
    OrderMapper orderMapper;

    public Collection<Order> listOrders() {
        return orderMapper.list();
    }

    public void save(Order order) {
        orderMapper.save(order);
        LOGGER.debug("Order saved: orderId={}, userId={}, productId={}, quantity={}",
                order.getId(), order.getUserId(), order.getProductId(), order.getQuantity());
    }
}
