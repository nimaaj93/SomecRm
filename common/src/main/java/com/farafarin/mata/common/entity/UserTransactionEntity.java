package com.farafarin.mata.common.entity;

import java.util.Date;

/**
 * Created by K550 VX on 1/15/2018.
 */
public class UserTransactionEntity extends BaseEntity {

    private Long transactionId;
    private Long userId;
    private Long orderId;
    private Long amount;
    private Long transactionStatus;
    private Long transactionType;
    private Date transactionDate;
    private String transactionNumber;
    private String transactionFollowupNumber;
    private Long lastBalance;
    private Boolean isDebit;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getLastBalance() {
        return lastBalance;
    }

    public void setLastBalance(Long lastBalance) {
        this.lastBalance = lastBalance;
    }

    public Boolean getDebit() {
        return isDebit;
    }

    public void setDebit(Boolean debit) {
        isDebit = debit;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(Long transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Long getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Long transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getTransactionFollowupNumber() {
        return transactionFollowupNumber;
    }

    public void setTransactionFollowupNumber(String transactionFollowupNumber) {
        this.transactionFollowupNumber = transactionFollowupNumber;
    }
}
