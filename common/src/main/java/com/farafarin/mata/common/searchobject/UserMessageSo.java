package com.farafarin.mata.common.searchobject;

/**
 * Created by Asus on 11/22/2017.
 */
public class UserMessageSo {
    private String receiverNationalCode;
    private String receiverPhoneNumber;
    private Integer pageSize;
    private Integer pageNumber;

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
    public Integer getOffset() {
        return pageNumber * pageSize;
    }

    public String getReceiverNationalCode() {
        return receiverNationalCode;
    }

    public void setReceiverNationalCode(String receiverNationalCode) {
        this.receiverNationalCode = receiverNationalCode;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }
}
