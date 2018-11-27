package com.pull2me.android.color;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Locale;

/**
 * 颜色相关工具类
 * Created by drz on 2016/1/18.
 */
public class ColorUtils {
    public static final int BLACK = 0xFF000000;
    public static final int DKGRAY = 0xFF444444;
    public static final int GRAY = 0xFF888888;
    public static final int LTGRAY = 0xFFCCCCCC;
    public static final int WHITE = 0xFFFFFFFF;
    public static final int RED = 0xFFFF0000;
    public static final int GREEN = 0xFF00FF00;
    public static final int BLUE = 0xFF0000FF;
    public static final int YELLOW = 0xFFFFFF00;
    public static final int CYAN = 0xFF00FFFF;
    public static final int MAGENTA = 0xFFFF00FF;
    public static final int TRANSPARENT = 0;
    private static final HashMap<String, Integer> sColorNameMap;

    static {
        sColorNameMap = new HashMap<String, Integer>();
        sColorNameMap.put("black", BLACK);
        sColorNameMap.put("darkgray", DKGRAY);
        sColorNameMap.put("gray", GRAY);
        sColorNameMap.put("lightgray", LTGRAY);
        sColorNameMap.put("white", WHITE);
        sColorNameMap.put("red", RED);
        sColorNameMap.put("green", GREEN);
        sColorNameMap.put("blue", BLUE);
        sColorNameMap.put("yellow", YELLOW);
        sColorNameMap.put("cyan", CYAN);
        sColorNameMap.put("magenta", MAGENTA);
        sColorNameMap.put("aqua", 0xFF00FFFF);
        sColorNameMap.put("fuchsia", 0xFFFF00FF);
        sColorNameMap.put("darkgrey", DKGRAY);
        sColorNameMap.put("grey", GRAY);
        sColorNameMap.put("lightgrey", LTGRAY);
        sColorNameMap.put("lime", 0xFF00FF00);
        sColorNameMap.put("maroon", 0xFF800000);
        sColorNameMap.put("navy", 0xFF000080);
        sColorNameMap.put("olive", 0xFF808000);
        sColorNameMap.put("purple", 0xFF800080);
        sColorNameMap.put("silver", 0xFFC0C0C0);
        sColorNameMap.put("teal", 0xFF008080);

    }

    /**
     * 合并透明度和无透明颜色
     *
     * @param alpha
     * @param color
     * @return
     */
    public static int combineAlphaAndColor(int alpha, int color) {
        if (alpha > 255) {
            alpha = 255;
        } else if (alpha < 0) {
            alpha = 0;
        }
        int result = Color.argb(alpha, tryRed(color), tryGreen(color), tryBlue(color));
        return result;
    }

    public static int tryRed(int color) {
        int red = Color.red(color);
        if (red > 255 || red < 0) {
            red = 255;
        }
        return red;
    }

    public static int tryGreen(int color) {
        int green = Color.green(color);
        if (green > 255 || green < 0) {
            green = 255;
        }
        return green;
    }

    public static int tryBlue(int color) {
        int blue = Color.blue(color);
        if (blue > 255 || blue < 0) {
            blue = 255;
        }
        return blue;
    }

    public static int parseColor(String colorString) {
        if (colorString.charAt(0) == '#') {
            // Use a long to avoid rollovers on #ffXXXXXX
            long color = Long.parseLong(colorString.substring(1), 16);
            if (colorString.length() == 7) {
                color |= 0x0000000000ff0000;
            } else if (colorString.length() == 9) {
                // Set the alpha value
                color |= 0x00000000ff000000;
            } else {
                throw new IllegalArgumentException("Unknown color");
            }
            return (int) color;
        } else {
            Integer color = sColorNameMap.get(colorString.toLowerCase(Locale.ROOT));
            if (color != null) {
                return color;
            }
        }
        throw new IllegalArgumentException("Unknown color");
    }

    /**
     * 尝试解析字符串颜色值为int值，失败返回默认值
     * 默认 #3a6bdb
     *
     * @param color    颜色字符串
     * @param defColor 默认颜色字符串
     * @return
     */
    public static int tryParseColor(String color, String defColor) {
        if (TextUtils.isEmpty(defColor)) {
            defColor = "#3a6bdb";
        }
       if (TextUtils.isEmpty(color)){
           color=defColor;
       }
        try {
            int parseColor = Color.parseColor(color.trim());
            return parseColor;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                int parseColor = Color.parseColor(defColor);
                return parseColor;
            } catch (Exception e1) {
                e1.printStackTrace();
                defColor = "#3a6bdb";
                return Color.parseColor(defColor);
            }

        }
    }

    /**
     * 从资源文件夹里读取颜色值
     * @param context
     * @param Res
     * @return
     */
    public static int getResClolor(Context context,int Res){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           return context.getColor(Res);
        }else {
          return   context.getResources().getColor(Res);
        }
    }
}
