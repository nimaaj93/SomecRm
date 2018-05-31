package com.farafarin.mata.web.back.model;

import com.farafarin.mata.common.validation.annotation.Phone;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Asus on 11/7/2017.
 */
public class UserSignUpRequestModel {

    @NotNull
    @Size(max = 50)
    private String firstNameFa;

    @NotNull
    @Size(max = 50)
    private String lastNameFa;

    @NotNull
    @Size(max = 11)
    private String phoneNumber;

    @NotNull
    @Size(max = 100)
    private String email;
    @Size(max = 100)
    private String learnMethod;
    @Size(max = 200)
    private String description;

    public String getFirstNameFa() {
        return firstNameFa;
    }

    public void setFirstNameFa(String firstNameFa) {
        this.firstNameFa = firstNameFa;
    }

    public String getLastNameFa() {
        return lastNameFa;
    }

    public void setLastNameFa(String lastNameFa) {
        this.lastNameFa = lastNameFa;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLearnMethod() {
        return learnMethod;
    }

    public void setLearnMethod(String learnMethod) {
        this.learnMethod = learnMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
