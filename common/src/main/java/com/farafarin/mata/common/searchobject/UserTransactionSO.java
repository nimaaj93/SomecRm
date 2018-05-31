package com.farafarin.mata.common.searchobject;

import javax.validation.constraints.Size;

/**
 * Created by K550 VX on 1/15/2018.
 */
public class UserTransactionSO extends BaseSO {

    @Size(max = 10)
    private String nationalCode;
    @Size(max = 50)
    private String firstName;
    @Size(max = 50)
    private String lastName;
    @Size(max = 50)
    private String transactionNumber;
    @Size(max = 50)
    private String transactionFollowUpNumber;

    private Long userId;

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public String getTransactionFollowUpNumber() {
        return transactionFollowUpNumber;
    }

    public void setTransactionFollowUpNumber(String transactionFollowUpNumber) {
        this.transactionFollowUpNumber = transactionFollowUpNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
