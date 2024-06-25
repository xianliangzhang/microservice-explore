package ccc.xxx.order.service;

import ccc.xxx.common.constants.UrlConstants;
import ccc.xxx.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(UrlConstants.USER_SERVICE_NAME)
public interface RemoteUserService {

    /**
     * Feign + FeignClient 构成完整请求路径
     *
     * @param uid 用户 ID
     * @return 用户信息
     */
    @GetMapping("/user/users/{uid}")
    User findById(@PathVariable("uid") Integer uid);
}
