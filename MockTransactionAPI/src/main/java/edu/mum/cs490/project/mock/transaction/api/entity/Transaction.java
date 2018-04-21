/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.project.mock.transaction.api.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author tuvshuu
 */
@Entity
@Table(name = "Transaction")
@NamedQueries(
        @NamedQuery(name = "Transaction.find.last.active", query = "SELECT t FROM Transaction t WHERE "
                + "t.cardNo = :cardNo AND "
                + "(t.result = true or t.payCash = true) "
                + "ORDER BY t.id DESC")
)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cardNo;
    @Column(name = "TxnAmount", nullable = false)
    private double transactionAmount;
    @Column(name = "AblAmount", nullable = false)
    private double availableAmount;
    @Column(name = "UsedAmount", nullable = false)
    private double usedAmount;
    @Column(name = "rsl")
    private Boolean result;
    private Boolean payCash;
    private String transactionId;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Transaction() {
    }

    public Transaction(String cardNo, Double transactionAmount, Double availableAmount, Double usedAmount, Boolean result, String transactionId) {
        this.cardNo = cardNo;
        this.transactionAmount = transactionAmount;
        this.availableAmount = availableAmount;
        this.usedAmount = usedAmount;
        this.result = result;
        this.transactionId = transactionId;
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(double availableAmount) {
        this.availableAmount = availableAmount;
    }

    public double getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(double usedAmount) {
        this.usedAmount = usedAmount;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Boolean getPayCash() {
        return payCash;
    }

    public void setPayCash(Boolean payCash) {
        this.payCash = payCash;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
