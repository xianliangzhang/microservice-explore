package ccc.xxx.order.controller;

import ccc.xxx.common.constants.UrlConstants;
import ccc.xxx.order.dto.OrderSaveDTO;
import ccc.xxx.order.entity.Order;
import ccc.xxx.order.service.OrderService;
import ccc.xxx.order.service.RemoteProductService;
import ccc.xxx.order.service.RemoteUserService;
import ccc.xxx.product.entity.Product;
import ccc.xxx.user.entity.User;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Resource
    OrderService orderService;

    @Resource
    RemoteUserService remoteUserService;

    @Resource
    RemoteProductService remoteProductService;

    @Resource
    DiscoveryClient discoveryClient;

    @GetMapping("/orders/list")
    public Collection<Order> listOrders() {
        Collection<Order> orders = orderService.listOrders();
        LOGGER.info("Loaded orders: {}", orders);
        if (orders.isEmpty()) {
            return orders;
        }

        orders.forEach(order -> {
            User user = remoteUserService.findById(order.getUserId());
            order.setUsername(user.getUsername());

            Product product = remoteProductService.findById(order.getProductId());
            order.setProductName(product.getName());
        });

        return orders;
    }

    @PostMapping("/orders/save")
    public Order order(@RequestBody OrderSaveDTO orderSaveDTO) {
        LOGGER.info("Received request: {}", orderSaveDTO);

        // Nacos 调用
        // String queryProductUrl = getServiceUrlPrefix(UrlConstants.PRODUCT_SERVICE_NAME, "/product") + "/products/" + orderSaveDTO.getProductId();
        // Product product = restTemplate.getForObject(queryProductUrl, Product.class);

        // Feign 调用
        Product product = remoteProductService.findById(orderSaveDTO.getProductId());
        LOGGER.info("Loaded product: {}", product);

        // Nacos 调用
        // String queryUserUrl = getServiceUrlPrefix(UrlConstants.USER_SERVICE_NAME, "/user") + "/users/" + orderSaveDTO.getUserId();
        // User user = restTemplate.getForObject(queryUserUrl, User.class);

        // Feign 调用
        User user = remoteUserService.findById(orderSaveDTO.getUserId());
        LOGGER.info("Loaded user: {}", user);

        Order order = Order.newOrder(orderSaveDTO);
        order.setUsername(user == null ? null : user.getUsername());
        order.setProductName(product == null ? null : product.getName());

        orderService.save(order);
        LOGGER.info("Saved order: {}", order);
        return order;
    }

    private String getServiceUrlPrefix(String serviceName, String serviceContext) {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(serviceName);
        int loadBalancedServiceIndex = ThreadLocalRandom.current().nextInt(serviceInstanceList.size());
        ServiceInstance serviceInstance = serviceInstanceList.get(loadBalancedServiceIndex);
        return UrlConstants.COMMON_SERVICE_PREFIX
                .replace("{HOST}", serviceInstance.getHost())
                .replace("{PORT}", String.valueOf(serviceInstance.getPort()))
                .replace("{CONTEXT}", serviceContext);
    }
}
