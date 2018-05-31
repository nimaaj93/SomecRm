package com.farafarin.mata.common.dao;

import com.farafarin.mata.common.entity.AdminNotificationTypeEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by Asus on 12/1/2017.
 */
@Component
public interface AdminNotificationTypeDao {
    AdminNotificationTypeEntity getAdminNotificationType(@Param("notificationType") Long notificationType);
}
