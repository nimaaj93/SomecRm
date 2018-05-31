package com.farafarin.mata.common.model;

/**
 * Created by K550 VX on 11/6/2017.
 */
public class ResponseError {

    private Integer status;
    private String reason;

    public ResponseError() {
    }

    public ResponseError(Integer status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
