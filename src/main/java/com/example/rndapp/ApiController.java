package com.example.rndapp;

import com.example.rndapp.models.Account;
import com.example.rndapp.models.Withdrawal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@RestController
public class ApiController {
    @PostMapping("/accounts")
    public Account CreateAccount (@RequestBody JsonNode node) {
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

    @PostMapping("/withdrawal")
    public Withdrawal DoWithdrawal (@RequestBody JsonNode node) {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Withdrawal withdrawal = null;
        try {
            withdrawal = objectMapper.treeToValue(node, Withdrawal.class);
            try {
                // thread to sleep for 1000 milliseconds
                Thread.sleep(23);
            } catch (Exception e) {
                System.out.println(e);
            }
            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();
            withdrawal.setTransactionId(uuidAsString);
            withdrawal.setTranTime(Timestamp.from(Instant.now()));
            try (Jedis jedis = AppJedisPool.getPool().getResource()) {
                jedis.set("hddasd", "sadasdd");
                jedis.incr("counter");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return withdrawal;
    }

}

