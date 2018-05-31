package com.farafarin.mata.common.validation.validator;

import com.farafarin.mata.common.validation.annotation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Asus on 11/5/2017.
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone paramA) {
    }

    @Override
    public boolean isValid(String phoneNo, ConstraintValidatorContext ctx) {
        if(phoneNo == null){
            return false;
        }
        if (!phoneNo.startsWith("09")){
            return false;
        } else {
            return true;
        }

    }
}
