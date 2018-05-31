package com.farafarin.mata.common.dao;

import com.farafarin.mata.common.entity.UserOfferEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Asus on 11/27/2017.
 */
@Component
public interface UserOfferDao {

    void insertUserOffer(@Param("model") UserOfferEntity model);

    Long getUserOfferByUserId(@Param("userId") Long userId);

    List<Long> getSeenNotExpiredOfferIdsForUser(@Param("userId") Long userId);

}
