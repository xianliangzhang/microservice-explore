package ccc.xxx.order.dto;

import lombok.Data;

@Data
public class OrderSaveDTO {
    private Integer userId;
    private Integer productId;
    private Integer quantity;
}
