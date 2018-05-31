package com.farafarin.mata.common.model;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Asus on 5/14/2018.
 */
public class AdminOrderLog {

    @NotNull
    private String logTitle;
    @NotNull
    private String logContent;
    @NotNull
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
