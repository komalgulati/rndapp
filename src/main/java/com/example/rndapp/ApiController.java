package com.example.rndapp;

import com.example.rndapp.models.Account;
import com.example.rndapp.models.Withdrawal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;
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

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    private TransactionRepository repository;

    @PostMapping("/withdrawal")
    public Object DoWithdrawal (@RequestBody JsonNode node) {
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
                Double balance = jedis.hincrByFloat("act" + withdrawal.getAccountId(), "balance", -withdrawal.getAmount());
                if (balance < 0) {
                    ApiError apiError =
                            new ApiError(HttpStatus.BAD_REQUEST, "Insufficient Balance", "Error");
                    return new ResponseEntity(apiError, apiError.getStatus());
                }
            }


          //repository.save(withdrawal);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return withdrawal;
    }

}

