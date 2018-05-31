package com.farafarin.mata.admin.back.entity;

import com.farafarin.mata.common.entity.BaseEntity;

/**
 * Created by Asus on 11/19/2017.
 */
public class UserMessageEntity extends BaseEntity {
    private Long userMessageId;
    private Long messageId;
    private Long userId;

    public Long getUserMessageId() {
        return userMessageId;
    }

    public void setUserMessageId(Long userMessageId) {
        this.userMessageId = userMessageId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
