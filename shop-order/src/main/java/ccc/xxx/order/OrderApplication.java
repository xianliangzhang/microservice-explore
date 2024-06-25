package ccc.xxx.order;

import ccc.xxx.order.entity.Order;
import ccc.xxx.common.config.MybatisConfig;
import ccc.xxx.order.mapper.OrderMapper;
import jakarta.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients   // 使能 Feign 功能
@EnableDiscoveryClient // 使能 Nacos 注册发现功能
@SpringBootApplication
@Import(MybatisConfig.class)
@MapperScan("ccc.xxx.order.mapper")
public class OrderApplication {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
