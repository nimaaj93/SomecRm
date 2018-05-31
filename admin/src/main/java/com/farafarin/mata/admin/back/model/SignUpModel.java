package com.farafarin.mata.admin.back.model;

import com.farafarin.mata.common.model.UserAddressModel;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Asus on 11/1/2017.
 */
public class SignUpModel {

    @Valid
    private UserPersonalModel userPersonalModel;
    @Valid
    private List<UserAddressModel> userAddressInfoList;


    public UserPersonalModel getUserPersonalModel() {
        return userPersonalModel;
    }

    public void setUserPersonalModel(UserPersonalModel userPersonalModel) {
        this.userPersonalModel = userPersonalModel;
    }

    public List<UserAddressModel> getUserAddressInfoList() {
        return userAddressInfoList;
    }

    public void setUserAddressInfoList(List<UserAddressModel> userAddressInfoList) {
        this.userAddressInfoList = userAddressInfoList;
    }
}
