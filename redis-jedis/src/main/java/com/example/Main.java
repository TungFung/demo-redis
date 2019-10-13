package com.example;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<HostAndPort> set = new HashSet<>();
        set.add(new HostAndPort("192.168.184.130", 7001));
        set.add(new HostAndPort("192.168.184.130", 7002));
        set.add(new HostAndPort("192.168.184.130", 7003));
        set.add(new HostAndPort("192.168.184.130", 7004));
        set.add(new HostAndPort("192.168.184.130", 7005));
        set.add(new HostAndPort("192.168.184.130", 7006));
        JedisCluster jedisCluster = new JedisCluster(set);
        String setResult = jedisCluster.set("test:mykey", "hello");
        System.out.println(setResult);//OK
        String getResult = jedisCluster.get("test:mykey");
        System.out.println(getResult);//hello
    }
}
