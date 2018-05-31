package com.farafarin.mata.common.entity;

/**
 * Created by Asus on 5/14/2018.
 */
public class AdminOrderLogEntity extends BaseEntity {
    private Long logId;
    private Long orderId;
    private Long userId;
    private String logTitle;
    private String logContent;
    private String writerFirstNameFa;
    private String writerLastNameFa;

    public String getWriterFirstNameFa() {
        return writerFirstNameFa;
    }

    public void setWriterFirstNameFa(String writerFirstNameFa) {
        this.writerFirstNameFa = writerFirstNameFa;
    }

    public String getWriterLastNameFa() {
        return writerLastNameFa;
    }

    public void setWriterLastNameFa(String writerLastNameFa) {
        this.writerLastNameFa = writerLastNameFa;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogTitle() {
        return logTitle;
    }

    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }
}
