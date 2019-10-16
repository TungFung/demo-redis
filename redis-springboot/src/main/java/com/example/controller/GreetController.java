package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.bean.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/greet")
public class GreetController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/welcome")
    public String welcome() {
        return "hello world";
    }

    @GetMapping("/saveValue")
    public String saveValue(String content) {
        redisTemplate.opsForValue().set("test:value2", content);
        String getResult = redisTemplate.opsForValue().get("test:value2");
        System.out.println(getResult);
        return getResult;
    }

    @GetMapping("/saveJson")
    public String saveJSON(String amount){
        Order order = new Order();
        order.setOrderNo("0001542");
        order.setAmount(new BigDecimal(amount));
        redisTemplate.opsForValue().set("test:json2", JSON.toJSONString(order));
        String str = redisTemplate.opsForValue().get("test:json2");
        System.out.println(str);
        return str;
    }

    @GetMapping("/saveHash")
    public String order(String amount){
        Order order = new Order();
        order.setOrderNo("0001542");
        order.setAmount(new BigDecimal(amount));
        redisTemplate.opsForHash().put("test:hash2", "test:hash:0001542", JSON.toJSONString(order));
        redisTemplate.opsForHash().put("test:hash2", "test:hash:0001543", JSON.toJSONString(order));
        Map<Object, Object> map = redisTemplate.opsForHash().entries("test:hash2");
        System.out.println(map);
        return "ok";
    }
}
