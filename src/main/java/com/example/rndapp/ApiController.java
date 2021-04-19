package com.example.rndapp;

import com.example.rndapp.models.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
public class ApiController {
    @PostMapping("/accounts")
    public Account Account (@RequestBody JsonNode node) {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Account acct = null;
        try {
            acct = objectMapper.treeToValue(node, Account.class);
            try (Jedis jedis = AppJedisPool.getPool().getResource()) {
                jedis.set("hddasd", "sadasdd");
                jedis.incr("counter");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return acct;
    }
}
