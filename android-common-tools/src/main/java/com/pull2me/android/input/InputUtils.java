package com.pull2me.android.input;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * Created by drz on 2016/1/25.
 */
public class InputUtils {
    private static String regEx = "[\u4e00-\u9fa5]"; // unicode编码，判断是否为汉字

    /**
     * 关闭或者开启输入法软键盘
     *
     * @param context
     */
    public static void toggleSoftKeyBord(Context context) {
//        --输入法软键盘的开启关闭--
        InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                InputMethodManager.HIDE_NOT_ALWAYS);
//        boolean acceptingText = imm.isAcceptingText();
//        boolean active = imm.isActive();
//        Log.d("debug", "- - - - - acceptingText:" + acceptingText + ",active:" + active);
    }


    /**
     * 只能在输入法键处于输入内容状态时可以关闭软键盘，其他时候无效
     *
     * @param context
     * @param contentView
     */
    public static void hideSoftKeyBoard(Context context, View contentView) {
        InputMethodManager im = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        if (im != null) {
            im.hideSoftInputFromWindow(contentView.getApplicationWindowToken(), 0);
        }
    }

    /**
     * @param context
     * @param contentView
     */
    public static void showSoftKeyBord(Context context, View contentView) {
        InputMethodManager im = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        if (im != null) {
            contentView.requestFocus();
            im.showSoftInput(contentView, 0);
        }
    }


    /**
     * 只有当正在输入内容时才返回true
     * 是否正在输入
     *
     * @param context
     * @return
     */
    public static boolean isSoftKeyBoardInputting(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        boolean acceptingText = imm.isAcceptingText();
        boolean immActive = imm.isActive();
        return acceptingText & immActive;
    }

    /**
     * 获取输入字符序列的长度，单位字符
     *
     * @param str
     * @return
     */
    public static int getInputLength(CharSequence str) {
        return str.length() + getChineseCount(str);
    }

    /**
     * 获取输入字符序列中中文字符的长度，一个中文占用两个字符长度
     *
     * @param str
     * @return
     */
    public static int getChineseCount(CharSequence str) {
        int count = 0;
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count = count + 1;
            }
        }
        return count;
    }

    /**
     *
     */
    private class NameLengthFilter implements InputFilter {
        int MAX_EN;// 最大英文/数字长度 一个汉字算两个字母
        String regEx = "[\u4e00-\u9fa5]"; // unicode编码，判断是否为汉字

        public NameLengthFilter(int mAX_EN) {
            super();
            MAX_EN = mAX_EN;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            int destCount = dest.toString().length() + getChineseCount(dest.toString());
            int sourceCount = source.toString().length() + getChineseCount(source.toString());
            if (destCount + sourceCount > MAX_EN) {
                return "";
            } else {
                return source;
            }
        }

        private int getChineseCount(String str) {
            int count = 0;
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            while (m.find()) {
                for (int i = 0; i <= m.groupCount(); i++) {
                    count = count + 1;
                }
            }
            return count;
        }
    }
}
