package com.pull2me.android.date;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 计时工具类 Created by drz on 2016/2/4.
 */
public class TimeHelper {
    public static final String PATTERN_DAY = "yyyy-MM-dd";
    public static final String PATTERN3 = "yyyy/MM/dd HH:mm";
    public static final String PATTERN = "yyyy-MM-dd HH:mm:ss.SSSZ";
    public static final String PATTERN2 = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_NOT_NULL = "yyyy-MM-dd-HH:mm:ss";
    private volatile static Long[] startTimes = new Long[100];
    private volatile static int sIndex = 0;
    private static final String TAG = TimeUtilsHolder.class.getSimpleName();
    private long currentCostTime;

    /**
     * 获取当前点消耗时间
     *
     * @return
     */
    public long getCurrentCostTime() {
        return currentCostTime;
    }

    private TimeHelper() {
    }

    public synchronized static TimeHelper getInstance() {
        return TimeUtilsHolder.instance;
    }

    /**
     * 计算毫秒时间的秒值
     *
     * @param milliSecondTime
     * @return
     */
    public static String calculateTime(long milliSecondTime) {
        if (milliSecondTime < 1000) {
            return "00:00:00." + milliSecondTime;
        }

        long hour = milliSecondTime / (60 * 60 * 1000);
        long minute = (milliSecondTime - hour * 60 * 60 * 1000) / (60 * 1000);
        long seconds = (milliSecondTime - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000;
        long milli = milliSecondTime % 1000;

        if (seconds >= 60) {
            seconds = seconds % 60;
            minute += seconds / 60;
        }
        if (minute >= 60) {
            minute = minute % 60;
            hour += minute / 60;
        }

        String sh;
        String sm;
        String ss;
        String mi = "";
        if (hour < 10) {
            sh = "0" + String.valueOf(hour);
        } else {
            sh = String.valueOf(hour);
        }
        if (minute < 10) {
            sm = "0" + String.valueOf(minute);
        } else {
            sm = String.valueOf(minute);
        }
        if (seconds < 10) {
            ss = "0" + String.valueOf(seconds);
        } else {
            ss = String.valueOf(seconds);
        }
        if (milli < 10) {
            mi += "0" + milli;
        } else if (milli < 100) {
            mi += "00" + milli;
        } else {
            mi = String.valueOf(milli);
        }

        return sh + ":" + sm + ":" + ss + "." + mi;
    }

    /**
     * 转换Date为本地字符串
     *
     * @param date
     * @return
     */
    public static String convertDateStr(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(PATTERN);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 转换毫秒Date为本地字符串
     *
     * @param date
     * @return
     */
    public static String convertDateStr(Long date) {
        SimpleDateFormat formatter = new SimpleDateFormat(PATTERN);
        String dateString = formatter.format(new Date(date));
        return dateString;
    }

    /**
     * 转换毫秒Date为本地字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String convertDateStr(Long date, String pattern) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            String dateString = formatter.format(new Date(date));
            return dateString;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static String convertDateStr(Date date, String pattern) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            String dateString = formatter.format(date);
            return dateString;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "";
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String converNowStr() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyyMMdd_hhmmss
     */
    public static String converNowStrNormal() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_hhmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public TimeHelper init() {
        synchronized (TimeHelper.class) {
            startTimes = new Long[10];
            sIndex = 0;
            return this;
        }
    }

    /**
     * 开始计时
     *
     * @param title 标题
     * @return
     */
    public int start(String title) {
        try {
            synchronized (TimeHelper.class) {
                init();
                long startMill = System.currentTimeMillis();
                Log.i(TAG, title + "--startTime:[" + startMill + "] " + convertDateStr(startMill));
                startTimes = ArrayManager.safeLength(startTimes, sIndex);
                startTimes[sIndex] = startMill;
            }
        } catch (Exception e) {
            Log.i(TAG, "- - - - - 计时异常!!!!!");
        }
        return sIndex++;
    }

    /**
     * 结束计时
     *
     * @param sIndex 起始时间编号
     * @param title
     * @return
     */
    public int end(int sIndex, String title) {
        try {
            synchronized (TimeHelper.class) {
                if (sIndex < startTimes.length) {
                    long endMill = System.currentTimeMillis();
                    startTimes = ArrayManager.safeLength(startTimes, TimeHelper.sIndex);
                    startTimes[TimeHelper.sIndex] = endMill;
                    Long startTime = startTimes[sIndex];
                    if (startTime == null) {
                        Log.i(TAG, title + "--endTime:引用了一个错误的开始时间索引" + sIndex + ",startTimes[" + sIndex + "]=null");
                        return -1;
                    }
                    long time = endMill - startTime;
                    Log.i(TAG, title + "--endTime:[" + endMill + "] " + convertDateStr(endMill) + ",用时：" + time + "毫秒=" + calculateTime(time));
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "- - - - - -计时异常!!!!!");
        }
        return TimeHelper.sIndex++;
    }

    /**
     * 距离上次打点的时间，且本次不打点
     *
     * @param title
     */
    public void preEndNunClick(String title) {
        try {
            synchronized (TimeHelper.class) {
                if (startTimes.length > 0) {
                    long endMill = System.currentTimeMillis();
                    startTimes = ArrayManager.safeLength(startTimes, TimeHelper.sIndex);
                    long time = endMill - startTimes[sIndex];
                    Log.i(TAG, title + "--endTime[" + endMill + "] " + convertDateStr(endMill) + ",用时：" + time + "毫秒=" + calculateTime(time));
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "- - - - - 计时异常!!!!");
        }
    }

    /**
     * 距离上次打点的时间，且本次打点
     *
     * @param title
     * @return
     */
    public int preEndClick(String title) {
        try {
            synchronized (TimeHelper.class) {
                if (startTimes.length > 0) {
                    long endMill = System.currentTimeMillis();
                    startTimes = ArrayManager.safeLength(startTimes, TimeHelper.sIndex);
                    startTimes[TimeHelper.sIndex] = endMill;
                    if (sIndex < 1) {
                        throw new RuntimeException("If you call method start() before call preEndClick() at first time! ");
                    }
                    currentCostTime = endMill - startTimes[sIndex - 1];
                    Log.i(TAG, title + "--endTime:[" + endMill + "] " + convertDateStr(endMill) + ",用时：" + currentCostTime + "毫秒=" + calculateTime(currentCostTime));
                    return TimeHelper.sIndex++;
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "- - - - 计时异常！！");
        }
        return -1;
    }

    /**
     * 距离起点的计时，本次不打点
     *
     * @param title
     */
    public void wholeEndNunClick(String title) {
        try {
            synchronized (TimeHelper.class) {
                if (startTimes.length > 0) {
                    long endMill = System.currentTimeMillis();
                    Long startTime = startTimes[0];
                    if (null == startTime) {
                        return;
                    }
                    long time = endMill - startTime;
                    Log.i(TAG, title + "--endTime:[" + endMill + "] " + convertDateStr(endMill) + ",用时：" + time + "毫秒=" + calculateTime(time));
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "- - - - - - - 计时异常!!!!!");
        }
    }

    /**
     * 距离起点的计时，本次打点
     *
     * @param title
     * @return
     */
    public int wholeEndClick(String title) {
        synchronized (TimeHelper.class) {
//        if (startTimes.length > 0) {
            startTimes = ArrayManager.safeLength(startTimes, TimeHelper.sIndex);
            long endMill = System.currentTimeMillis();
            startTimes[TimeHelper.sIndex] = endMill;
            long time = endMill - startTimes[0];
            Log.i(TAG, title + "--endTime:[" + endMill + "] " + convertDateStr(endMill) + ",用时：" + time + "毫秒=" + calculateTime(time));
            return TimeHelper.sIndex++;
//        } else {
//            return -1;
//        }
        }
    }

    public static long parseString2Mill(String timeStr) {
        Date date;
        try {
            date = SimpleDateFormat.getInstance().parse(timeStr);
        } catch (ParseException e) {
            return -1;
        }
        return date.getTime();
    }

    private static class TimeUtilsHolder {
        private static final TimeHelper instance = new TimeHelper();
    }

    static final class ArrayManager {
        public static <Q> Q[] safeLength(Q[] q, int index) {
            while (index >= q.length) {
                q = Arrays.copyOf(q, q.length * 2);
            }
            return q;
        }
    }
}
