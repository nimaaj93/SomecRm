package com.farafarin.mata.common.dao;

import com.farafarin.mata.common.dto.AdminNotificationDto;
import com.farafarin.mata.common.entity.AdminNotificationEntity;
import com.farafarin.mata.common.searchobject.AdminNotificationSO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Asus on 12/1/2017.
 */
@Component
public interface AdminNotificationDao {

    void insertAdminNotification(@Param("model")AdminNotificationEntity model);

    List<AdminNotificationDto> getNotifications(@Param("so") AdminNotificationSO adminNotificationSO);

    Integer getNotificationsCount();

}
