package com.farafarin.mata.common.dto;

/**
 * Created by K550 VX on 12/11/2017.
 */
public class NotificationMessageTemplateDto {

    private Long id;
    private String messageTemplate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    public void setMessageTemplate(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }
}
