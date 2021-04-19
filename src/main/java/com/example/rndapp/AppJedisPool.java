package com.example.rndapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

@Configuration
public class AppJedisPool {

    final static JedisPoolConfig poolConfig = buildPoolConfig();

    private static String redisPassword;

    @Value("${redis.password}")
    public void setRedisPassword(String str) {
        redisPassword = str;
    }

    private static String redisHost;

    @Value("${redis.host}")
    public void setRedisHost(String str) {
        redisHost = str;
    }

    private static int redisPort;
    @Value("${redis.port}")
    public void setRedisPort(int port) {
        redisPort = port;
    }

    private static JedisPool pool = null;
    public static JedisPool getPool() {
        if (pool == null) {
            if (redisPassword == null || redisPassword.isEmpty()) {
                pool = new JedisPool(poolConfig, redisHost, redisPort, 6000);
            } else {
                pool = new JedisPool(poolConfig, redisHost, redisPort, 6000, redisPassword);
            }
        }
        return pool;
    }

    private static JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(20);
        poolConfig.setMaxIdle(10);
        poolConfig.setMinIdle(2);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }


}


