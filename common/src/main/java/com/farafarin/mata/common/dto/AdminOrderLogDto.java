package com.farafarin.mata.common.dto;

import com.farafarin.mata.common.util.DateUtil;

import java.util.Date;

/**
 * Created by Asus on 5/14/2018.
 */
public class AdminOrderLogDto {
    private String logTitle;
    private String logContent;
    private String logDate;
    private String writerFirstNameFa;
    private String writerLastNameFa;

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

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public void setLogDateFa(Date logDate) {
        this.logDate = DateUtil.convertGregorianDateToJalaliString(logDate);
    }

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
}
