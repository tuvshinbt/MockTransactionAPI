/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.project.mock.transaction.api.model;

//{
//  "txnId" : "00011",
//  "cardNo" : "0123456789012345",
//  "expirationDate" : "04/2018",
//  "nameOnCard" : "TEST",
//  "zipCode" : "52557",
//  "amount" : 1.1,
//  "ccv" : "CCV"
//}
/**
 *
 * @author tuvshuu
 */
public class TransactionRequest {

    private String txnId;
    private String cardNo;
    private String expirationDate;
    private String nameOnCard;
    private String CCV;
    private String zipCode;
    private Double amount;

    public TransactionRequest() {
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCCV() {
        return CCV;
    }

    public void setCCV(String CCV) {
        this.CCV = CCV;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionRequest{" + "txnId=" + txnId + '}';
    }

}
