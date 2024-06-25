package ccc.xxx.order.mapper;

import ccc.xxx.order.dto.OrderQueryDTO;
import ccc.xxx.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

@Mapper
public interface OrderMapper {

    Collection<Order> list();

    void save(Order order);
}
