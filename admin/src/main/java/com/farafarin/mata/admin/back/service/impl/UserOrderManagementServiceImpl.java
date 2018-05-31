package com.farafarin.mata.admin.back.service.impl;

import com.farafarin.mata.common.constant.TransactionStatus;
import com.farafarin.mata.common.constant.TransactionType;
import com.farafarin.mata.common.constant.UserNotificationMessageType;
import com.farafarin.mata.common.dao.*;
import com.farafarin.mata.common.dto.*;
import com.farafarin.mata.common.entity.*;
import com.farafarin.mata.common.constant.UserOrderStatus;
import com.farafarin.mata.common.exception.InsuffiecientBalanceException;
import com.farafarin.mata.common.model.AdminOrderLog;
import com.farafarin.mata.common.model.SmsModel;
import com.farafarin.mata.common.model.UserOrderUpdateModel;
import com.farafarin.mata.common.searchobject.UserOrderSO;
import com.farafarin.mata.admin.back.service.UserOrderManagementService;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.service.SmsService;
import com.farafarin.mata.common.util.AuthenticationUtil;
import com.farafarin.mata.common.util.DateUtil;
import com.farafarin.mata.common.util.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Asus on 11/16/2017.
 */
@Component
public class UserOrderManagementServiceImpl implements UserOrderManagementService {

    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private OrderCategoryDao orderCategoryDao;
    //    @Autowired
//    private UserCreditDao userCreditDao;
//    @Autowired
//    private UserTransactionDao userTransactionDao;
    @Autowired
    private OrderLogDao orderLogDao;
    @Autowired
    private SmsService smsService;
    @Autowired
    private NotificationMessageTemplateDao notificationMessageTemplateDao;

    @Override
    public List<UserOrderDto> getUserOrders(UserOrderSO userOrderSO) {
        List<UserOrderDto> userOrderList = new ArrayList<>();
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        List<UserOrderEntity> userOrderEntities = userOrderDao.retrieveUserOrders(userOrderSO);
        for (UserOrderEntity userOrderEntity : userOrderEntities) {
            List<CategoryDto> categoryList = prepareCategoryListForOrder(userOrderEntity.getCategoryId(),
                    new ArrayList<CategoryDto>());
            UserOrderDto userOrderDto = prepareUserOrderDtoList(categoryList, userOrderEntity);
            userOrderList.add(userOrderDto);
        }
        return userOrderList;
    }

    @Override
    public Long getUserOrderCount(UserOrderSO userOrderSO) {
        return userOrderDao.getUserOrderCount(userOrderSO);
    }

    @Override
    public void updateUserOrder(UserOrderUpdateModel userOrderUpdateModel) {
        UserOrderUpdateEntity userOrderUpdateEntity = prepareUserOrderEntity(userOrderUpdateModel);
        userOrderUpdateEntity.setOrderId(userOrderUpdateModel.getOrderId());
        userOrderDao.updateUserOrder(userOrderUpdateEntity);
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        SmsModel smsModel = prepareSms(userOrderUpdateModel, userOrderUpdateEntity.getStatus(),
                currentUser.getFirstNameFa(), currentUser.getLastNameFa());
        if (Empty.isNotEmpty(smsModel.getDestinationNumber()) && Empty.isNotEmpty(smsModel.getContent())) {

            smsService.afeSendSms(smsModel);
        }
    }

    private SmsModel prepareSms(UserOrderUpdateModel userOrderUpdateModel, Long status, String firstNameFa, String lastNameFa) {
        SmsModel smsModel = new SmsModel();
        UserOrderEntity userOrderEntity = userOrderDao.retrieveUserOrderById(userOrderUpdateModel.getOrderId());
        smsModel.setDestinationNumber(userOrderEntity.getPhoneNumber());
        String messageText = prepareMessage(status, firstNameFa, lastNameFa, userOrderUpdateModel.getCategoryDtoList());
        smsModel.setContent(messageText);

        return smsModel;
    }

    private String prepareMessage(Long status, String firstNameFa, String lastNameFa, List<CategoryDto> categoryDtoList) {
        String message = null;
        if (status.equals(UserOrderStatus.DONE.getUserOrderStatus())) {
            message = notificationMessageTemplateDao
                    .retrieveTemplateById(UserNotificationMessageType.DONE_ORDER.getId())
                    .getMessageTemplate();
            replaceMessage(message, firstNameFa, lastNameFa, categoryDtoList.get(1).getTitleFa(), categoryDtoList.get(0).getTitleFa());
        } else if (status.equals(UserOrderStatus.SEND.getUserOrderStatus())) {
            message = notificationMessageTemplateDao
                    .retrieveTemplateById(UserNotificationMessageType.SEND_ORDER.getId())
                    .getMessageTemplate();
            replaceMessage(message, firstNameFa, lastNameFa, categoryDtoList.get(1).getTitleFa(), categoryDtoList.get(0).getTitleFa());
        } else if (status.equals(UserOrderStatus.VERIFIED.getUserOrderStatus())) {
            message = notificationMessageTemplateDao
                    .retrieveTemplateById(UserNotificationMessageType.VERIFIED_ORDER.getId())
                    .getMessageTemplate();
            replaceMessage(message, firstNameFa, lastNameFa, categoryDtoList.get(1).getTitleFa(), categoryDtoList.get(0).getTitleFa());
        } else if (status.equals(UserOrderStatus.CANCELED.getUserOrderStatus())) {
            message = notificationMessageTemplateDao
                    .retrieveTemplateById(UserNotificationMessageType.CANCELED_ORDER.getId())
                    .getMessageTemplate();
//            replaceMessage(message, firstNameFa, lastNameFa, categoryDtoList.get(1).getTitleFa(), categoryDtoList.get(0).getTitleFa());
        }

        return message;
    }

//    @Override
//    @Transactional
//    public void payOrder(Long orderId) {
//        UserOrderEntity userOrderEntity = userOrderDao.retrieveUserOrderById(orderId);
//        UserCreditEntity userCreditEntity = userCreditDao.retrieveCreditForUser(userOrderEntity.getUserId());
//
//        if (!userOrderEntity.getOrderStatus().equals(UserOrderStatus.WAITING_FOR_PAYMENT.getUserOrderStatus())) {
//            throw new IllegalStateException("User order not in waiting for payment state, hence cannot be paid.");
//        }
//
//        Long userBalance = userCreditEntity.getBalance();
//        Long orderAmount = userOrderEntity.getAmount();
//
//        if (userBalance < orderAmount) {
//            throw new InsuffiecientBalanceException();
//        }
//
//        Long newBalance = userBalance - orderAmount;
//        UserTransactionEntity userTransactionEntity =
//                prepareUserTransactionEntity(userOrderEntity,newBalance);
//        userTransactionDao.createTransaction(userTransactionEntity);
//
//        userCreditEntity.setBalance(newBalance);
//        userCreditDao.updateCredit(userCreditEntity);
//
//        UserOrderUpdateEntity updateEntity = new UserOrderUpdateEntity();
//
//        updateEntity.setStatus(UserOrderStatus.PAID.getUserOrderStatus());
//        updateEntity.setOrderId(userOrderEntity.getOrderId());
//
//        userOrderDao.updateUserOrder(updateEntity);
//    }

    @Override
    public void addOrderLog(AdminOrderLog adminOrderLog) {
        AdminOrderLogEntity adminOrderLogEntity = prepareLogEntity(adminOrderLog);
        orderLogDao.insertOrderLog(adminOrderLogEntity);

    }

    @Override
    public List<AdminOrderLogDto> getOrderLogs(Long orderId) {
        return orderLogDao.getOrderLogForUser(orderId);
    }

    private AdminOrderLogEntity prepareLogEntity(AdminOrderLog adminOrderLog) {
        AdminOrderLogEntity adminOrderLogEntity = new AdminOrderLogEntity();
        UserProfile userProfile = AuthenticationUtil.getCurrentUser();
        adminOrderLogEntity.setOrderId(adminOrderLog.getOrderId());
        adminOrderLogEntity.setUserId(userProfile.getUserId());
        adminOrderLogEntity.setLogContent(adminOrderLog.getLogContent());
        adminOrderLogEntity.setWriterFirstNameFa(userProfile.getFirstNameFa());
        adminOrderLogEntity.setWriterLastNameFa(userProfile.getLastNameFa());
        adminOrderLogEntity.setLogTitle(adminOrderLog.getLogTitle());
        return adminOrderLogEntity;
    }

    //todo because payment step remove this method should be deleted--test

//    private UserTransactionEntity prepareUserTransactionEntity(UserOrderEntity userOrderEntity,Long lastBalance) {
//        UserTransactionEntity userTransactionEntity = new UserTransactionEntity();
//        userTransactionEntity.setUserId(userOrderEntity.getUserId());
//        userTransactionEntity.setOrderId(userOrderEntity.getOrderId());
//        userTransactionEntity.setAmount(userOrderEntity.getAmount());
//        userTransactionEntity.setTransactionStatus(TransactionStatus.SUCCESSFUL.getId());
//        userTransactionEntity.setTransactionType(TransactionType.ADMIN_CREATED.getId());
//        userTransactionEntity.setTransactionDate(new Date());
//        userTransactionEntity.setLastBalance(lastBalance);
//        userTransactionEntity.setDebit(true);
//
//        return userTransactionEntity;
//    }

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

    private UserOrderDto prepareUserOrderDtoList(List<CategoryDto> categoryList, UserOrderEntity userOrderEntity) {
        UserOrderDto userOrderDto = new UserOrderDto();
        UserAddressDto userAddressDto = new UserAddressDto();
        userOrderDto.setAmount(userOrderEntity.getAmount());
        userAddressDto.setAddressFa(userOrderEntity.getAddressFa());
        userAddressDto.setAddressEn(userOrderEntity.getAddressEn());
        userAddressDto.setCityFa(userOrderEntity.getCityFa());
        userAddressDto.setCityEn(userOrderEntity.getCityEn());


        userOrderDto.setProviderOrderFirstNameFa(userOrderEntity.getFirstNameFa());
        userOrderDto.setProviderOrderLastNameFa(userOrderEntity.getLastNameFa());
        userOrderDto.setProviderOrderFirstNameEn(userOrderEntity.getFirstNameEn());
        userOrderDto.setProviderOrderLastNameEn(userOrderEntity.getLastNameEn());
        userOrderDto.setJalaliOrderDate(userOrderEntity.getCreateDate());
        userOrderDto.setOrderStatus(userOrderEntity.getOrderStatus());
        userOrderDto.setDescription(userOrderEntity.getDescription());
        userOrderDto.setPhoneNumber(userOrderEntity.getPhoneNumber());
        userOrderDto.setNationalCode(userOrderEntity.getNationalCode());
        userOrderDto.setUserId(userOrderEntity.getUserId());
        userOrderDto.setCategoryDtoList(categoryList);
        userOrderDto.setOrderId(userOrderEntity.getOrderId());
        userOrderDto.setUserAddressDto(userAddressDto);
        userOrderDto.setOrderNumber(userOrderEntity.getOrderNumber());
        userOrderDto.setUploadedFileName(userOrderEntity.getUploadedFileName());
        return userOrderDto;
    }

    private UserOrderUpdateEntity prepareUserOrderEntity(UserOrderUpdateModel userOrderUpdateModel) {
        UserOrderUpdateEntity userOrderUpdateDto = new UserOrderUpdateEntity();
        UserOrderEntity userOrderEntity = userOrderDao.retrieveUserOrderById(userOrderUpdateModel.getOrderId());
        if (userOrderUpdateModel.getOperation().equals("next")) {
            if (userOrderEntity.getOrderStatus().equals(UserOrderStatus.SIGN.getUserOrderStatus())) {
                if (userOrderUpdateModel.getDescription() == null) {
                    throw new IllegalArgumentException();
                }
                userOrderUpdateDto.setDescription(userOrderUpdateModel.getDescription());
                userOrderUpdateDto.setStatus(UserOrderStatus.DONE.getUserOrderStatus());
                userOrderUpdateDto.setAddressId(userOrderUpdateModel.getAddressId());

            } else if (userOrderEntity.getOrderStatus().equals(UserOrderStatus.DONE.getUserOrderStatus())) {
                //todo test -- amount should be deleted
//                if (userOrderUpdateModel.getAmount() == null) {
//                    throw new IllegalArgumentException();
//                }
//                userOrderUpdateDto.setAmount(userOrderUpdateModel.getAmount());
                if (userOrderUpdateModel.getDescription() == null) {
                    throw new IllegalArgumentException();
                }
                userOrderUpdateDto.setStatus(UserOrderStatus.SEND.getUserOrderStatus());
                userOrderUpdateDto.setDescription(userOrderUpdateModel.getDescription());
                userOrderUpdateDto.setAddressId(userOrderUpdateModel.getAddressId());

            } else if (userOrderEntity.getOrderStatus().equals(UserOrderStatus.SEND.getUserOrderStatus())) {
                if (userOrderUpdateModel.getDescription() == null) {
                    throw new IllegalArgumentException();
                }
                userOrderUpdateDto.setStatus(UserOrderStatus.VERIFIED.getUserOrderStatus());
                userOrderUpdateDto.setDescription(userOrderUpdateModel.getDescription());
                userOrderUpdateDto.setAddressId(userOrderUpdateModel.getAddressId());

            }
        } else if (userOrderUpdateModel.getOperation().equals("cancel")) {
            userOrderUpdateDto.setStatus(UserOrderStatus.CANCELED.getUserOrderStatus());
        }
        return userOrderUpdateDto;
    }

    private String replaceMessage(String message, String firstNameFa, String lastNameFa, String parentCategory, String childCategory) {
        String userName = firstNameFa + " " + lastNameFa;
        message = message.replaceAll("username ", userName);
        message = message.replaceAll("childCategory", childCategory);
        message = message.replaceAll("parentCategory", parentCategory);
        return message;
    }

}
