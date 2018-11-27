package com.pull2me.javase.date;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class DateUtils {
    public static final String PATTERN_MIN = "yyyy-MM-dd HH:mm";
    public static final String PATTERN = "yyyy-MM-dd HH:mm:ss.SSSZ";
    public static final String PATTERN_ATTRIBUTE = "yyyy/MM/dd HH:mm";
    public static final String PATTERN_YEAR_MONTH_DAY="yyyy/MM/dd";

    /**
     * 转换毫秒Date为本地字符串(精确到分钟)
     *
     * @param date
     * @return
     */
    public static String convertDateStr(Long date) {
        SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_ATTRIBUTE);
        String dateString;
        if (date==null){
            dateString = formatter.format(new Date());
        }else {
            dateString = formatter.format(new Date(date));
        }
        return dateString;
    }

    public static String convertDateStr(String date) {

        SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_ATTRIBUTE);
        String dateString;
        if (TextUtils.isEmpty(date)){
            dateString = "";
        }else {
            try {
                BigDecimal bd = new BigDecimal(date);
                dateString = formatter.format(new Date(Long.parseLong(bd.toPlainString())));
            }catch (Exception e){
                dateString ="";
            }

        }
        return dateString;
    }

    /**
     * 转换Date为本地字符串(精确到分钟)
     *
     * @param date
     * @return
     */
    public static String convertDateStr(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_MIN);
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String convertDateStr(Date date,String formatStr) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 对日期进行格式化输出
     */
    public static String getStatus(String dateString) {

        String time_show = null;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            date = sdf.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Date curDate = new Date(System.currentTimeMillis());// 当前时间
        long diff = curDate.getTime() - date.getTime();

        SimpleDateFormat sdf3 = new SimpleDateFormat("MM-dd HH:mm");
        time_show = sdf3.format(date);

        if (date.getYear() < curDate.getYear()) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            time_show = sdf1.format(date);
        } else if (diff < 1000) {
            time_show = "刚刚";
        } else if (diff < 60 * 1000) {
            time_show = (int) Math.floor(diff / 1000) + " 秒前";
        } else if (diff < 3600 * 1000) {
            time_show = (int) Math.floor(diff / 60 / 1000) + " 分钟前";
        } else if (date.getDate() == curDate.getDate()) {
            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
            time_show = "今天 " + sdf2.format(date);
        }
        return time_show;
    }

    /**
     * 获得当前时间
     */
    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 对当前时间进行格式化
     */
    public static String getFomatCurrentTime() {
        return getStatus(getCurrentTime());
    }
}
