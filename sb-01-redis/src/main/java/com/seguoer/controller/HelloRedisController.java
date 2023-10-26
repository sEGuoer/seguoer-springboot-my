package com.seguoer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRedisController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("hello-redis")
    public String helloRedis() {
        Long count = stringRedisTemplate.opsForValue().increment("count");
        return "count=" + count;
    }
}
