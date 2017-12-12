package com.stock.service.util;

import org.apache.tomcat.util.bcel.Const;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class DateHelper {
    private static final Logger LOGGER = Logger.getLogger(DateHelper.class.getName());

    public static String DateToString(Date date) {
        DateFormat formatter = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        return formatter.format(date);
    }

    public static Date stringToDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        Date date = new Date();
        try {
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.info("Parse exception");
        }
        return date;
    }
}
