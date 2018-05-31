package com.farafarin.mata.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * Created by K550 VX on 11/12/2017.
 */
public abstract class Empty {

    public static boolean isNotEmpty(Object o) {
        if (o instanceof String) {
            return isNotEmpty((String)o);
        }
        if (o instanceof Collection) {
            return isNotEmpty((Collection)o);
        }
        if (o instanceof Map) {
            return isNotEmpty((Map)o);
        }
        return o != null;
    }

    public static boolean isNotEmpty(String s) {
        return s != null && !s.trim().equals("");
    }

    public static boolean isNotEmpty(Collection collection) {
        return collection != null && collection.size() > 0;
    }

    public static boolean isNotEmpty(Map map) {
        return map != null && map.entrySet().size() > 0;
    }

}
