package com.farafarin.mata.web.back.service.impl;

import com.farafarin.mata.common.dao.OrderCategoryDao;
import com.farafarin.mata.common.dao.UserOrderDao;
import com.farafarin.mata.common.dto.CategoryDto;
import com.farafarin.mata.common.dto.UserAddressDto;
import com.farafarin.mata.common.entity.OrderCategoryEntity;
import com.farafarin.mata.common.entity.UserOrderEntity;
import com.farafarin.mata.common.entity.UserOrderReportEntity;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.util.AuthenticationUtil;
import com.farafarin.mata.common.dto.UserOrderReportDto;
import com.farafarin.mata.common.util.DateUtil;
import com.farafarin.mata.web.back.service.UserOrderReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 11/23/2017.
 */
@Component
public class UserOrderReportServiceImpl implements UserOrderReportService {

    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private OrderCategoryDao orderCategoryDao;

    @Override
    public Long getUserOrderCount(List<Long> orderStatusList) {
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        return userOrderDao.getOrderCountByUserIdAndStatus(currentUser.getUserId(), orderStatusList);
    }

    @Override
    public List<UserOrderReportDto> getUserOrders(List<Long> orderStatusList, Integer pageSize, Integer pageNumber) {
        List<UserOrderReportDto> userOrderReportList = new ArrayList<>();
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        Integer offset = pageSize * pageNumber;
        List<UserOrderReportEntity> userOrderEntities =
                userOrderDao.getOrderByUserIdAndStatus(currentUser.getUserId(), orderStatusList, pageSize, offset);
        for (UserOrderReportEntity userOrderReportEntity : userOrderEntities) {
            List<CategoryDto> categoryList = prepareCategoryListForOrder(userOrderReportEntity.getCategoryId(),
                    new ArrayList<CategoryDto>());
            UserOrderReportDto userOrderReportDto = prepareUserOrderReport(categoryList, userOrderReportEntity);
            userOrderReportList.add(userOrderReportDto);
        }

        return userOrderReportList;
    }

    @Override
    public void cancelOrder(Long orderId) {
        userOrderDao.cancelOrder(orderId);
    }

    private List<CategoryDto> prepareCategoryListForOrder(Long categoryId, List<CategoryDto> categoryDtoList) {
        OrderCategoryEntity orderCategoryEntity = orderCategoryDao.getOrderCategory(categoryId);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setTitleFa(orderCategoryEntity.getTitleFa());
        categoryDto.setTitleEn(orderCategoryEntity.getTitleEn());
        categoryDtoList.add(categoryDto);
        if (orderCategoryEntity.getParentId() != null) {
            prepareCategoryListForOrder(orderCategoryEntity.getParentId(), categoryDtoList);
        }
        return categoryDtoList;
    }

    private UserOrderReportDto prepareUserOrderReport(List<CategoryDto> categoryList, UserOrderReportEntity userOrderReportEntity) {
        UserOrderReportDto userOrderReportDto = new UserOrderReportDto();
        String jalaliOrderDate = DateUtil.convertGregorianDateToJalaliString(userOrderReportEntity.getCreateDate());
        userOrderReportDto.setOrderDate(jalaliOrderDate);
        userOrderReportDto.setOrderStatus(userOrderReportEntity.getOrderStatus());
        userOrderReportDto.setDescription(userOrderReportEntity.getDescription());
        userOrderReportDto.setAmount(userOrderReportEntity.getAmount());
        userOrderReportDto.setCategoryDtoList(categoryList);
        userOrderReportDto.setOrderId(userOrderReportEntity.getOrderId());

        UserAddressDto userAddressDto = new UserAddressDto();
        userAddressDto.setAddressFa(userOrderReportEntity.getAddressFa());
        userAddressDto.setAddressEn(userOrderReportEntity.getAddressEn());
        userAddressDto.setCityFa(userOrderReportEntity.getCityFa());
        userAddressDto.setCityEn(userOrderReportEntity.getCityEn());

        userOrderReportDto.setUserAddressDto(userAddressDto);
        userOrderReportDto.setOrderNumber(userOrderReportEntity.getOrderNumber());
        return userOrderReportDto;
    }
}
