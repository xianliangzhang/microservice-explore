package ccc.xxx.order.service;

import ccc.xxx.common.constants.UrlConstants;
import ccc.xxx.product.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign 方式远程调用产品服务
 */
@FeignClient(UrlConstants.PRODUCT_SERVICE_NAME)
public interface RemoteProductService {

    /**
     * Feign + FeignClient 构成完整请求路径
     *
     * @param pid 产品ID
     * @return 产品信息
     */
    @GetMapping("/product/products/{pid}")
    Product findById(@PathVariable("pid") Integer pid);
}
