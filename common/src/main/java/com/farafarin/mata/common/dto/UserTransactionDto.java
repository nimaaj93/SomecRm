package com.farafarin.mata.common.dto;

import com.farafarin.mata.common.util.DateUtil;

import java.util.Date;

/**
 * Created by K550 VX on 1/15/2018.
 */
public class UserTransactionDto {

    private Long transactionId;
    private Long userId;
    private Long orderId;
    private Long amount;
    private Long transactionStatus;
    private Long transactionType;
    private String transactionDate;
    private String transactionNumber;
    private String transactionFollowupNumber;
    private Long lastBalance;
    private boolean debit;
    private String createDate;
    private String createUser;

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

    public Boolean isDebit() {
        return this.debit;
    }

    public void setDebit(boolean debit) {
        this.debit = debit;
    }

    private UserPersonalDto userPersonalDto;

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

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public UserPersonalDto getUserPersonalDto() {
        return userPersonalDto;
    }

    public void setUserPersonalDto(UserPersonalDto userPersonalDto) {
        this.userPersonalDto = userPersonalDto;
    }

    public void setJalaliTransactionDate(Date date) {
        try {
            this.transactionDate = DateUtil.convertGregorianDateToJalaliString(date);
        } catch (Exception e) {
            this.transactionDate = "";
        }
    }

    public void setJalaliCreateDate(Date date) {
        try {
            this.createDate = DateUtil.convertGregorianDateToJalaliString(date);
        } catch (Exception e) {
            this.createDate = "";
        }
    }

}
