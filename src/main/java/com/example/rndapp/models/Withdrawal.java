package com.example.rndapp.models;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class Withdrawal extends Transaction {

    public Withdrawal() {
        setTranType("Dr");
    }

}
