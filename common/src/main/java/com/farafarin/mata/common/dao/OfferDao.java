package com.farafarin.mata.common.dao;

import com.farafarin.mata.common.dto.OfferDto;
import com.farafarin.mata.common.entity.OfferEntity;
import com.farafarin.mata.common.model.OfferUpdateModel;
import com.farafarin.mata.common.searchobject.OfferSO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Asus on 11/21/2017.
 */
@Component
public interface OfferDao {
    void insertUserOffer(@Param("model") OfferEntity offerEntity);
    List<OfferDto> getOffers(@Param("so")OfferSO so);
    void updateOffer(@Param("model")OfferUpdateModel model);
    Long getOfferCount(@Param("so") OfferSO so);
    Long getActiveOfferCount(@Param("status") Long status);
    List<OfferDto> getOffersByStatus(@Param("offerStatus") Long offerStatus);
}
