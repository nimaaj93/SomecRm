package com.farafarin.mata.common.service.impl;

import com.farafarin.mata.common.constant.NotificationType;
import com.farafarin.mata.common.constant.UserNotificationMessageType;
import com.farafarin.mata.common.constant.UserOrderStatus;
import com.farafarin.mata.common.constant.UserStatus;
import com.farafarin.mata.common.dao.*;
import com.farafarin.mata.common.dto.CategoryDto;
import com.farafarin.mata.common.entity.*;
import com.farafarin.mata.common.exception.OrderSubmitionException;
import com.farafarin.mata.common.model.AdminOrderModel;
import com.farafarin.mata.common.model.SmsModel;
import com.farafarin.mata.common.security.model.UserProfile;
import com.farafarin.mata.common.service.FileUploadService;
import com.farafarin.mata.common.service.SmsService;
import com.farafarin.mata.common.util.AuthenticationUtil;
import com.farafarin.mata.common.model.UserOrderModel;
import com.farafarin.mata.common.service.UserOrderService;
import com.farafarin.mata.common.util.DateUtil;
import com.farafarin.mata.common.util.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Asus on 11/17/2017.
 */
@Component
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAddressDao userAddressDao;
    @Autowired
    private OrderCategoryDao orderCategoryDao;
    @Autowired
    private AdminNotificationDao adminNotificationDao;
    @Autowired
    private AdminNotificationTypeDao adminNotificationTypeDao;
    @Autowired
    private OrderFileDao orderFileDao;
    @Autowired
    private SmsService smsService;
    @Autowired
    private NotificationMessageTemplateDao notificationMessageTemplateDao;
    @Autowired
    private FileUploadService fileUploadService;

    @Override
    @Transactional
    public void submitUserOrderRequest(UserOrderModel userOrderModel) {
        try {
            UserOrderEntity userOrderEntity = prepareUserOrderEntity(userOrderModel);
            userOrderDao.insertUserOrder(userOrderEntity);
            AdminNotificationEntity adminNotificationEntity = prepareAdminNotificationEntity();
            adminNotificationDao.insertAdminNotification(adminNotificationEntity);
            if (userOrderModel.getFile() != null) {
                OrderFileEntity orderFileEntity = prepareOrderFileEntitiy(userOrderModel, userOrderEntity.getOrderId());
                orderFileDao.insertOrderFile(orderFileEntity);
                fileUploadService.saveFile(userOrderModel.getFile(),userOrderEntity.getOrderId().toString());
            }
            SmsModel smsModel = prepareSms(userOrderEntity.getOrderId(), userOrderEntity.getOrderStatus());
            if (Empty.isNotEmpty(smsModel.getDestinationNumber()) && Empty.isNotEmpty(smsModel.getContent())) {

                smsService.afeSendSms(smsModel);
            }
        } catch (Exception e) {
            throw new OrderSubmitionException(e);
        }
    }

    private OrderFileEntity prepareOrderFileEntitiy(UserOrderModel userOrderModel, Long orderId) {
        OrderFileEntity orderFileEntity = new OrderFileEntity();
        orderFileEntity.setFileName(userOrderModel.getUploadedFileName());
        orderFileEntity.setOrderId(orderId);
        return orderFileEntity;
    }

    @Override
    public void submitUserOrderRequest(AdminOrderModel adminOrderModel) {
        UserPersonalEntity userPersonalEntity =
                userDao.retrieveUserByNationalCodeAndPhoneNumber(adminOrderModel.getNationalCode(),
                        adminOrderModel.getPhoneNumber());

        UserOrderEntity userOrderEntity = prepareUserOrderEntity(adminOrderModel, userPersonalEntity);
        userOrderDao.insertUserOrder(userOrderEntity);
    }

    @Override
    public UserPersonalEntity getUserInfo(String nationalCode, String phoneNumber) {
        return userDao.retrieveUserByNationalCodeAndPhoneNumber(nationalCode, phoneNumber);
    }

    @Override
    public List<UserAddressEntity> getUserAddresses(String nationalCode, String phoneNumber) {
        UserPersonalEntity userPersonalEntity =
                userDao.retrieveUserByNationalCodeAndPhoneNumber(nationalCode, phoneNumber);

        return userAddressDao.retrieveUserAddresses(userPersonalEntity.getUserId());
    }

    @Override
    public List<OrderCategoryEntity> getCategories(Long parentId) {
        return orderCategoryDao.getOrderCategoryByParentId(parentId);
    }

    @Override
    public List<UserAddressEntity> getUserAddresses() {
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        return userAddressDao.retrieveUserAddresses(currentUser.getUserId());
    }

    private UserOrderEntity prepareUserOrderEntity(UserOrderModel userOrderModel) {
        UserOrderEntity userOrderEntity = new UserOrderEntity();
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        userOrderEntity.setUserId(currentUser.getUserId());
        userOrderEntity.setOrderStatus(UserOrderStatus.SIGN.getUserOrderStatus());
        userOrderEntity.setDescription(userOrderModel.getDescription());
        userOrderEntity.setCategoryId(userOrderModel.getCategoryId());
        userOrderEntity.setAddressId(userOrderModel.getAddressId());
        String orderNumber = createOrderNumber(currentUser.getNationalCode());
        userOrderEntity.setOrderNumber(orderNumber);
        return userOrderEntity;
    }

    private UserOrderEntity prepareUserOrderEntity(AdminOrderModel adminOrderModel, UserPersonalEntity userPersonalEntity) {
        UserOrderEntity userOrderEntity = new UserOrderEntity();
        userOrderEntity.setUserId(userPersonalEntity.getUserId());
        userOrderEntity.setCategoryId(adminOrderModel.getCategoryId());
        userOrderEntity.setOrderStatus(UserOrderStatus.SIGN.getUserOrderStatus());
        userOrderEntity.setDescription(adminOrderModel.getDescription());
        userOrderEntity.setAddressId(adminOrderModel.getAddressId());
        String orderNumber = createOrderNumber(userPersonalEntity.getNationalCode());
        userOrderEntity.setOrderNumber(orderNumber);

        return userOrderEntity;
    }

    private String createOrderNumber(String nationalCode) {
        String nationalCodeSubString = nationalCode.substring(4, 8);
        String orderNumberFirstPart = nationalCodeSubString.substring(0, 2);
        Random random = new Random();
        String orderNumberSecondPart = String.format("%04d", random.nextInt(10000));
        String orderNumberThirdPart = DateUtil.getFormattedCurrentDateForOrder();
        String orderNumber = orderNumberFirstPart + orderNumberSecondPart + orderNumberThirdPart;
        return orderNumber;
    }

    private AdminNotificationEntity prepareAdminNotificationEntity() {
        AdminNotificationEntity adminNotificationEntity = new AdminNotificationEntity();
        adminNotificationEntity.setNotificationType(NotificationType.ORDER.getNotificationType());
        UserProfile currentUser = AuthenticationUtil.getCurrentUser();
        String userName = currentUser.getFirstNameFa() + " " + currentUser.getLastNameFa();
        AdminNotificationTypeEntity adminNotificationTypeEntity =
                adminNotificationTypeDao.getAdminNotificationType(NotificationType.ORDER.getNotificationType());

        String notificationText = adminNotificationTypeEntity.getNotificationTemplate().replace("{param1}", userName);
        adminNotificationEntity.setNotificationText(notificationText);
        Date expireDate = DateUtil.addDays(new Date(), 1);
        adminNotificationEntity.setExpireDate(expireDate);
        return adminNotificationEntity;
    }

    private SmsModel prepareSms(Long orderId, Long status) {
        SmsModel smsModel = new SmsModel();
        UserOrderEntity userOrderEntity = userOrderDao.retrieveUserOrderById(orderId);
        smsModel.setDestinationNumber(userOrderEntity.getPhoneNumber());
        String messageText = prepareMessage(status);
        smsModel.setContent(messageText);

        return smsModel;
    }

    private String prepareMessage(Long status) {
        String message = null;
        if (status.equals(UserOrderStatus.SIGN.getUserOrderStatus())) {
            message = notificationMessageTemplateDao
                    .retrieveTemplateById(UserNotificationMessageType.SUBMIT_ORDER.getId())
                    .getMessageTemplate();
        }

        return message;
    }
}
