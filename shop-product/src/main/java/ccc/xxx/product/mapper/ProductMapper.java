package ccc.xxx.product.mapper;

import ccc.xxx.product.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

@Mapper
public interface ProductMapper {

    Collection<Product> list();

    Product findByPid(Integer pid);
}
