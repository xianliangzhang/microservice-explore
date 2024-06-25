package ccc.xxx.product.controller;

import ccc.xxx.product.entity.Product;
import ccc.xxx.product.service.ProductService;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Resource
    ProductService productService;

    @GetMapping("/products/{pid}")
    public Product product(@PathVariable("pid") Integer pid) {
        return productService.findById(pid);
    }
}
