package com.farafarin.mata.common.entity;

import com.farafarin.mata.common.entity.BaseEntity;

/**
 * Created by Asus on 11/19/2017.
 */
public class MessageEntity extends BaseEntity {
    private Long messageId;
    private String header;
    private String description;
    private Long senderUserId;
    private Long  receiverUserId;
    private Long seenStatus;

    public Long getSeenStatus() {
        return seenStatus;
    }

    public void setSeenStatus(Long seenStatus) {
        this.seenStatus = seenStatus;
    }

    public Long getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Long senderUserId) {
        this.senderUserId = senderUserId;
    }

    public Long getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(Long receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
