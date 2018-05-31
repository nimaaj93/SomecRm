package com.farafarin.mata.common.service;

/**
 * Created by Asus on 5/19/2018.
 */
public interface MailService {
    public void sendEmail(String from, String to, String subject, String msg);
}
