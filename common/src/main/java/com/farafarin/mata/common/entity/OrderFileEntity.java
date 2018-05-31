package com.farafarin.mata.common.entity;

/**
 * Created by Asus on 5/21/2018.
 */
public class OrderFileEntity extends BaseEntity{
    private Long fileId;
    private Long orderId;
    private String fileName;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
