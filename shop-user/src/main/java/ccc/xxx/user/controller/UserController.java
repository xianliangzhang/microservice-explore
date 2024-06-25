package ccc.xxx.user.controller;

import ccc.xxx.user.entity.User;
import ccc.xxx.user.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserMapper userMapper;

    @GetMapping("/users/{uid}")
    public User findById(@PathVariable("uid") Integer uid) {
        LOGGER.info("Received request: uid={}", uid);
        return userMapper.findById(uid);
    }

}
