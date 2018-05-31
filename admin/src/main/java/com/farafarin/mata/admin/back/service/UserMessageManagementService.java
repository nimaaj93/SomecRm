package com.farafarin.mata.admin.back.service;

import com.farafarin.mata.common.dto.MessageDto;
import com.farafarin.mata.admin.back.model.UserMessageModel;
import com.farafarin.mata.common.searchobject.UserMessageSo;

import java.util.List;

/**
 * Created by Asus on 11/19/2017.
 */
public interface UserMessageManagementService {
    void submitUserMessage(UserMessageModel userMessageModel);
    List<MessageDto> getUserMessages(UserMessageSo userMessageSo);
    Long getUserMessageCount(String nationalCode,String phoneNumber);
}
