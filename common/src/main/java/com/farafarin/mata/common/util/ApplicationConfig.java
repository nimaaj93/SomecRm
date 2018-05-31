package com.farafarin.mata.common.util;

import com.farafarin.mata.common.exception.PropertyNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asus on 11/7/2017.
 */
@Component
public class ApplicationConfig {

    private final static Map<String,String> values = new HashMap<>();

    static {
        values.put("expire.date.range","1");
        values.put("super.admin.role.id","1");
        values.put("admin.role.id","2");
        values.put("user.role.id","3");
        values.put("authorization.code.length","5");
    }

    public static String getProperty(String key){
        if (values.containsKey(key)){
            return values.get(key);
        }
        throw new PropertyNotFoundException();
    }




}
