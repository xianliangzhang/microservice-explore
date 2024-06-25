package ccc.xxx.product.service;

import ccc.xxx.product.entity.Product;
import ccc.xxx.product.mapper.ProductMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductService {

    @Resource
    private ProductMapper productMapper;

    public Collection<Product> listProducts() {
        return productMapper.list();
    }

    public Product findById(Integer pid) {
        return productMapper.findByPid(pid);
    }
}
