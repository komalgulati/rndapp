package com.example.rndapp.models;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class Deposit extends Transaction {

    public Deposit() {
        setTranType("Cr");
    }

}
