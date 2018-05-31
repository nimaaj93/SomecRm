package com.farafarin.mata.common.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by K550 VX on 1/15/2018.
 */
public class AdminUserTransactionModel {

    @NotNull
    private Long userId;
    @NotNull
    @Min(value = 0L)
    private Long amount;
    @NotEmpty
    private String transactionDate;
    @NotEmpty
    @Size(max = 50)
    private String transactionNumber;
    @NotEmpty
    @Size(max = 50)
    private String transactionFollowupNumber;


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

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
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
