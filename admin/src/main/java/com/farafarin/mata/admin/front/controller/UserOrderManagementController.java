package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.common.dto.AdminOrderLogDto;
import com.farafarin.mata.common.entity.UserAddressEntity;
import com.farafarin.mata.common.exception.UnknownSystemException;
import com.farafarin.mata.common.model.AdminOrderLog;
import com.farafarin.mata.common.model.SmsModel;
import com.farafarin.mata.common.model.UserOrderUpdateModel;
import com.farafarin.mata.common.searchobject.UserOrderSO;
import com.farafarin.mata.admin.back.service.UserOrderManagementService;
import com.farafarin.mata.common.dto.UserOrderDto;
import com.farafarin.mata.common.service.FileUploadService;
import com.farafarin.mata.common.service.UserAddressService;
import com.farafarin.mata.common.util.DateUtil;
import com.farafarin.mata.common.util.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

/**
 * Created by Asus on 11/16/2017.
 */
@RestController
@RequestMapping("s/userorder")
public class UserOrderManagementController {

    @Autowired
    private UserOrderManagementService userOrderManagementService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long getUserOrderCount(@RequestParam(value = "nationalCode", required = false) String nationalCode,
                                  @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                  @RequestParam(value = "fromDate", required = false) String fromDate,
                                  @RequestParam(value = "toDate", required = false) String toDate,
                                  @RequestParam(value = "orderStatus", required = false) Long orderStatus,
                                  @RequestParam(value = "firstNameFa", required = false) String firstNameFa,
                                  @RequestParam(value = "lastNameFa", required = false) String lastNameFa) {
        UserOrderSO userOrderSO = prepareUserOrderSO(nationalCode, phoneNumber,
                fromDate, toDate, orderStatus, null, null, firstNameFa, lastNameFa);
        return userOrderManagementService.getUserOrderCount(userOrderSO);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserOrderDto> getUserOrders(@RequestParam(value = "nationalCode", required = false) String nationalCode,
                                            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                            @RequestParam(value = "fromDate", required = false) String fromDate,
                                            @RequestParam(value = "toDate", required = false) String toDate,
                                            @RequestParam(value = "orderStatus", required = false) Long orderStatus,
                                            @RequestParam("page") @NotNull Integer pageNumber,
                                            @RequestParam("size") @NotNull Integer pageSize,
                                            @RequestParam(value = "firstNameFa", required = false) String firstNameFa,
                                            @RequestParam(value = "lastNameFa", required = false) String lastNameFa) {
        UserOrderSO userOrderSO = prepareUserOrderSO(nationalCode, phoneNumber,
                fromDate, toDate, orderStatus, pageSize, pageNumber, firstNameFa, lastNameFa);
        return userOrderManagementService.getUserOrders(userOrderSO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateUserOrder(@RequestBody @Valid UserOrderUpdateModel userOrderUpdateModel) {
        userOrderManagementService.updateUserOrder(userOrderUpdateModel);

    }

    @RequestMapping(value = "/useraddress", method = RequestMethod.GET)
    public List<UserAddressEntity> getUserAddresses(@RequestParam(value = "userId") Long userId) {
        return userAddressService.getUserAddress(userId);
    }

    //todo pay amount step should be deleted--test

//    @RequestMapping(value = "/pay", method = RequestMethod.POST)
//    public void payOrder(@RequestParam(value = "orderId") Long orderId) {
//        userOrderManagementService.payOrder(orderId);
//    }

    private UserOrderSO prepareUserOrderSO(String nationalCode, String phoneNumber, String fromDate,
                                           String toDate, Long orderStatus, Integer pageSize,
                                           Integer pageNumber, String firstNameFa, String lastNameFa) {
        Date fromDateGregorian = null;
        Date toDateGregorian = null;
        if (Empty.isNotEmpty(fromDate)) {
            fromDateGregorian = DateUtil.convertStringJalahiDateToGregorianDate(fromDate);
        }
        if (Empty.isNotEmpty(toDate)) {
            toDateGregorian = DateUtil.convertStringJalahiDateToGregorianDate(toDate);
        }
        UserOrderSO userOrderSO = new UserOrderSO();
        userOrderSO.setNationalCode(nationalCode);
        userOrderSO.setPhoneNumber(phoneNumber);
        userOrderSO.setFromDate(fromDateGregorian);
        userOrderSO.setToDate(toDateGregorian);
        userOrderSO.setOrderStatus(orderStatus);
        userOrderSO.setPageSize(pageSize);
        userOrderSO.setPageNumber(pageNumber);
        userOrderSO.setFirstNameFa(firstNameFa);
        userOrderSO.setLastNameFa(lastNameFa);

        return userOrderSO;
    }

    @RequestMapping(value = "/addlog", method = RequestMethod.POST)
    public void addOrderLog(@RequestBody @Valid AdminOrderLog adminOrderLog) {
        userOrderManagementService.addOrderLog(adminOrderLog);
    }

    @RequestMapping(value = "/getlog", method = RequestMethod.GET)
    public List<AdminOrderLogDto> getOrderLogList(@RequestParam(value = "orderId", required = false) Long orderId) {
        return userOrderManagementService.getOrderLogs(orderId);
    }

    @RequestMapping(value = "/download/{orderId}/{fileName:.+}", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response
                            ,@PathVariable("orderId") Long orderId
                            ,@PathVariable("fileName") String fileName) throws IOException {

        if (orderId == null || fileName == null) {
            throw new IllegalArgumentException("orderId and fileName must be set to download attachment.");
        }

        File file = fileUploadService.getFile(orderId,fileName);

        if (!file.exists()) {
            String errorMessage = "Sorry. The file you are looking for does not exist";
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            throw new UnknownSystemException(errorMessage);
        }

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
        response.setContentLength((int) file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

}
