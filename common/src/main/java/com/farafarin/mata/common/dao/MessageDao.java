package com.farafarin.mata.common.dao;


import com.farafarin.mata.common.dto.MessageDto;
import com.farafarin.mata.common.dto.UserMessageDto;
import com.farafarin.mata.common.entity.MessageEntity;
import com.farafarin.mata.common.searchobject.UserMessageSo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Asus on 11/19/2017.
 */
@Component
public interface MessageDao {
    void insertMessage(@Param("model") MessageEntity model);

    List<MessageDto> getUserMessages(@Param("so") UserMessageSo so);

    Long getUserMessageCount(@Param("nationalCode") String nationalCode,@Param("phoneNumber") String phoneNumber);

    Long getUserNewMessageCount(@Param("messageStatus") Long messageStatus,@Param("userId") Long userId);

    List<UserMessageDto> getUserNewMessage(@Param("userId") Long userId);

    void updateUserMessageStatus(@Param("currentStatus") Long currentStatus,@Param("newStatus") Long newStatus,@Param("receiverUserId") Long receiverUserId);
}
