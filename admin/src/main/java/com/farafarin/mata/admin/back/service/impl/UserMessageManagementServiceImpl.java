package com.farafarin.mata.admin.back.service.impl;

import com.farafarin.mata.common.dao.MessageDao;
import com.farafarin.mata.common.dto.MessageDto;
import com.farafarin.mata.common.entity.MessageEntity;
import com.farafarin.mata.admin.back.model.UserMessageModel;
import com.farafarin.mata.common.searchobject.UserMessageSo;
import com.farafarin.mata.admin.back.service.UserMessageManagementService;
import com.farafarin.mata.admin.exception.UserNotFoundException;
import com.farafarin.mata.common.constant.UserMessageSeenStatus;
import com.farafarin.mata.common.dao.UserDao;
import com.farafarin.mata.common.entity.UserPersonalEntity;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.util.AuthenticationUtil;
import com.farafarin.mata.common.util.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Asus on 11/19/2017.
 */
@Component
public class UserMessageManagementServiceImpl implements UserMessageManagementService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private MessageDao messageDao;

    @Override
    @Transactional
    public void submitUserMessage(UserMessageModel userMessageModel) {
        UserPersonalEntity userPersonalEntity =
                userDao.retrieveUserByNationalCodeAndPhoneNumber(userMessageModel.getNationalCode(),
                        userMessageModel.getPhoneNumber());
        if (!Empty.isNotEmpty(userPersonalEntity)) {
            throw new UserNotFoundException();
        }
        MessageEntity messageEntity = prepareMessageEntity(userMessageModel, userPersonalEntity);
        messageDao.insertMessage(messageEntity);
    }

    @Override
    public List<MessageDto> getUserMessages(UserMessageSo userMessageSo) {
        return messageDao.getUserMessages(userMessageSo);
    }

    @Override
    public Long getUserMessageCount(String nationalCode, String phoneNumber) {
        return messageDao.getUserMessageCount(nationalCode,phoneNumber);
    }


    private MessageEntity prepareMessageEntity(UserMessageModel userMessageModel, UserPersonalEntity userPersonalEntity) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setHeader(userMessageModel.getHeader());
        messageEntity.setDescription(userMessageModel.getDescription());
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        messageEntity.setSenderUserId(currentUser.getUserId());
        messageEntity.setReceiverUserId(userPersonalEntity.getUserId());
        messageEntity.setSeenStatus(UserMessageSeenStatus.NEW.getSeenStatus());
        return messageEntity;
    }

//    private UserMessageEntity prepareUserMessageEntity(Long messageId, Long userId) {
//        UserMessageEntity userMessageEntity = new UserMessageEntity();
//        userMessageEntity.setMessageId(messageId);
//        userMessageEntity.setUserId(userId);
//        return userMessageEntity;
//    }
}
