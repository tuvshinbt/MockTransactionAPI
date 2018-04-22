/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs490.project.mock.transaction.api.model;

// {"txnId":"00011","cardNo":"01234567890123456","expirationDate":"04/2018","nameOnCard":"TEST","zipCode":"52557","amount":3000,"ccv":"CCV"}
// T924bkQSm4LtVlUvYbA3/VKsfBom6bxEIK8t613bNwHIVzuWYzf04u2GV8VW/29Pj2OWiMCd4nPDyPHsLA+p0EYp8HsvirhTDmSw0NNnK9OROunlgBxqxVPAfr8HxU6b+s6W3x1aqWgMTDy7H+cCZjBUWUoC91B+JbCrHuyj0Bv5B9PVnpeCiYVJj0bxuo9g
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
