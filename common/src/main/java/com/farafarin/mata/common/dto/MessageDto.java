package com.farafarin.mata.common.dto;

import com.farafarin.mata.common.util.DateUtil;

import java.util.Date;

/**
 * Created by Asus on 11/22/2017.
 */
public class MessageDto {

    private String messageHeader;
    private String description;
    private String senderFirstNameFa;
    private String senderLastNameFa;
    private String senderFirstNameEn;
    private String senderLastNameEn;
    private String receiverFirstNameFa;
    private String receiverLastNameFa;
    private String receiverFirstNameEn;
    private String receiverLastNameEn;
    private String sendDate;

    public String getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(String messageHeader) {
        this.messageHeader = messageHeader;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSenderFirstNameFa() {
        return senderFirstNameFa;
    }

    public void setSenderFirstNameFa(String senderFirstNameFa) {
        this.senderFirstNameFa = senderFirstNameFa;
    }

    public String getSenderLastNameFa() {
        return senderLastNameFa;
    }

    public void setSenderLastNameFa(String senderLastNameFa) {
        this.senderLastNameFa = senderLastNameFa;
    }

    public String getSenderFirstNameEn() {
        return senderFirstNameEn;
    }

    public void setSenderFirstNameEn(String senderFirstNameEn) {
        this.senderFirstNameEn = senderFirstNameEn;
    }

    public String getSenderLastNameEn() {
        return senderLastNameEn;
    }

    public void setSenderLastNameEn(String senderLastNameEn) {
        this.senderLastNameEn = senderLastNameEn;
    }

    public String getReceiverFirstNameFa() {
        return receiverFirstNameFa;
    }

    public void setReceiverFirstNameFa(String receiverFirstNameFa) {
        this.receiverFirstNameFa = receiverFirstNameFa;
    }

    public String getReceiverLastNameFa() {
        return receiverLastNameFa;
    }

    public void setReceiverLastNameFa(String receiverLastNameFa) {
        this.receiverLastNameFa = receiverLastNameFa;
    }

    public String getReceiverFirstNameEn() {
        return receiverFirstNameEn;
    }

    public void setReceiverFirstNameEn(String receiverFirstNameEn) {
        this.receiverFirstNameEn = receiverFirstNameEn;
    }

    public String getReceiverLastNameEn() {
        return receiverLastNameEn;
    }

    public void setReceiverLastNameEn(String receiverLastNameEn) {
        this.receiverLastNameEn = receiverLastNameEn;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public void setJalaliSendDate(String date){
        String jalaliSendDate = DateUtil.convertGregorianStringToJalaliString(date);
        this.sendDate = jalaliSendDate;
    }
}
