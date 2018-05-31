package com.farafarin.mata.common.searchobject;

/**
 * Created by Asus on 11/8/2017.
 */
public class UserSO {
    private String phoneNumber;
    private String nationalCode;
    private Integer pageSize;
    private Integer pageNumber;
    private Long userType;
    private String firstNameFa;
    private String lastNameFa;
    private Long corporateUserId;

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

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getCorporateUserId() {
        return corporateUserId;
    }

    public void setCorporateUserId(Long corporateUserId) {
        this.corporateUserId = corporateUserId;
    }

    public Integer getOffset() {
        return pageNumber * pageSize;
    }
}
