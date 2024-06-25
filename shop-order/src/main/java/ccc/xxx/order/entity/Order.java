package ccc.xxx.order.entity;

import ccc.xxx.order.dto.OrderSaveDTO;
import lombok.Data;

@Data
public class Order {
    private Integer id; // 订单ID
    private Integer userId; // 客户ID
    private String username; // 客户名称
    private Integer productId; // 商品ID
    private String productName; // 商品名称
    private Integer quantity; // 购买数量

    public static Order newOrder(OrderSaveDTO orderSaveDTO) {
        Order order = new Order();
        order.setUserId(orderSaveDTO.getUserId());
        order.setProductId(orderSaveDTO.getUserId());
        order.setQuantity(orderSaveDTO.getQuantity());
        return order;
    }
}
