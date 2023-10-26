package com.seguoer.controller;

import com.seguoer.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRedisController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @GetMapping("hello-redis")
    public String helloRedis() {
        Long count = stringRedisTemplate.opsForValue().increment("count");
        return "count=" + count;
    }
    @GetMapping("saveObjectToRedis")
    public String saveObjectToRedis() {
        User user = new User(1L, "John", 18);
        redisTemplate.opsForValue().set("user", user);
        return "done!";
    }

    @GetMapping("getObjectFromRedis")
    public User getObjectFromRedis() {
        return (User) redisTemplate.opsForValue().get("user");
    }
}
