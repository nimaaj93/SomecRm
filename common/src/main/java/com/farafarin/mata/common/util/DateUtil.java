package com.farafarin.mata.common.util;

import com.farafarin.mata.common.exception.UnknownSystemException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Asus on 11/5/2017.
 */
public class DateUtil {

    public static Date convertStringToDate(String dateInString) {
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            date = formatter.parse(dateInString);
        } catch (ParseException px) {
            px.printStackTrace();
            throw new UnknownSystemException(px);
        }
        return date;
    }

    public static Date convertStringToDateTime(String dateInString) {
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            date = formatter.parse(dateInString);
        } catch (ParseException px) {
            px.printStackTrace();
            throw new UnknownSystemException(px);
        }
        return date;
    }

    public static Date convertStringJalahiDateToGregorianDate(String jalaliDate) {
        try {
            String gregorianDateInString = JalaliCalendar.jalaliToGregorianString(jalaliDate);
            Date gregorianDate = convertStringToDate(gregorianDateInString);
            return gregorianDate;
        } catch (Exception ex) {
            throw new UnknownSystemException(ex);
        }
    }

    public static String convertDateToString(Date date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String DateInString = simpleDateFormat.format(date);
            return DateInString;
        } catch (Exception ex) {
            throw new UnknownSystemException(ex);
        }
    }

    public static String convertGregorianDateToJalaliString(Date date) {
        try {
            String gregorianDate = convertDateToString(date);
            String jalaliDate = JalaliCalendar.stringGregorianToJalali(gregorianDate);
            return jalaliDate;
        } catch (Exception ex) {
            throw new UnknownSystemException(ex);
        }
    }

    public static String convertGregorianStringToJalaliString(String date){
        String stringDate  = date.split(" ")[0];
        String jalaliInString = JalaliCalendar.stringGregorianToJalali(stringDate);
        return jalaliInString;
    }

    public static Date subtractDaysFromDate(int days){
        Calendar cal = GregorianCalendar.getInstance();
        cal.add( Calendar.DAY_OF_YEAR, days);
        Date daysAgo = cal.getTime();
        return daysAgo;
    }

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static String getFormattedCurrentDateForOrder() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMdd");
        return dateFormat.format(new Date());
    }

}
