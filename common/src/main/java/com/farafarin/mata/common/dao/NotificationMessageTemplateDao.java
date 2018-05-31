package com.farafarin.mata.common.dao;

import com.farafarin.mata.common.dto.NotificationMessageTemplateDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by K550 VX on 12/11/2017.
 */
@Component
public interface NotificationMessageTemplateDao {

    NotificationMessageTemplateDto retrieveTemplateById(@Param("id") Long id);

}
