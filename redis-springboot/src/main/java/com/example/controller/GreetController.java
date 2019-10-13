package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
public class GreetController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/welcome")
    public String welcome() {
        return "hello world";
    }

    @GetMapping("/echo")
    public String echo(String content) {
        redisTemplate.opsForValue().set("test:echoKey", content);
        String getResult = (String) redisTemplate.opsForValue().get("test:echoKey");
        System.out.println(getResult);
        return getResult;
    }
}
