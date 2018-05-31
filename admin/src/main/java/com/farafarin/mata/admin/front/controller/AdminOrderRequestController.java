package com.farafarin.mata.admin.front.controller;

import com.farafarin.mata.common.dto.CategoryDto;
import com.farafarin.mata.common.entity.OrderCategoryEntity;
import com.farafarin.mata.common.model.UserAddressModel;
import com.farafarin.mata.common.entity.UserAddressEntity;
import com.farafarin.mata.common.entity.UserPersonalEntity;
import com.farafarin.mata.common.model.AdminOrderModel;
import com.farafarin.mata.common.service.UserAddressService;
import com.farafarin.mata.common.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Asus on 11/18/2017.
 */
@RestController
@RequestMapping(value = "s/adminorder")
public class AdminOrderRequestController {

    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private UserAddressService userAddressService;

    @RequestMapping(value = "/sbmitrequest",method = RequestMethod.POST)
    public void submitOrderRequest(@RequestBody AdminOrderModel adminOrderModel){
        userOrderService.submitUserOrderRequest(adminOrderModel);
    }

    @RequestMapping(value = "/userinfo",method = RequestMethod.GET)
    public UserPersonalEntity getUserInfo(@RequestParam( value = "nationalCode", required = false) String nationalCode,
                                          @RequestParam (value = "phoneNumber", required = false) String  phoneNumber){
        return userOrderService.getUserInfo(nationalCode,phoneNumber);
    }

    @RequestMapping(value = "/useraddress",method = RequestMethod.GET)
    public List<UserAddressEntity> getUserAddresses(@RequestParam( value = "nationalCode", required = false) String nationalCode,
                                                   @RequestParam (value = "phoneNumber", required = false) String  phoneNumber){
        return userOrderService.getUserAddresses(nationalCode,phoneNumber);
    }

    @RequestMapping(value = "/newaddress",method = RequestMethod.POST)
    public void insertNewUserAddress(@RequestBody @Valid UserAddressModel userAddressModel,
                                     @RequestParam(value = "userId") Long userId){

        userAddressService.insertUserAddress(userAddressModel,userId);
    }

    @RequestMapping(value = "/category",method = RequestMethod.GET)
    public List<OrderCategoryEntity> getCategories(@RequestParam ( value = "categoryId", required = false) Long categoryId){
        return userOrderService.getCategories(categoryId);
    }
}
