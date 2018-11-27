package com.pull2me.android.shape;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;

/**
 * Created by drz on 2016/1/22.
 */
public class ShapeGenerateUtils {

    private static final String DEF_STROKE_COLOR = "#6087E1";
    private static final String DEF_FILL_COLOR = "#6087E1";

    /**
     * 生成Shape
     *
     * @param gradientType Shape的类型  GradientDrawable.RECTANGLE,GradientDrawable.LINEAR_GRADIENT,GradientDrawable.RADIAL_GRADIENT,
     * @param strokeWidth  边框宽度
     * @param roundRadius  圆角半径
     * @param strokeColor  边框颜色
     * @param fillColor    填充颜色
     * @return
     */
    public static GradientDrawable generateShape(int gradientType, int strokeWidth, int roundRadius, String strokeColor, String fillColor) {
        int length = strokeColor.length();
        int fillLength = fillColor.length();
        if (!TextUtils.isEmpty(strokeColor) && (length == 7 || length == 9)) {

        } else {
            strokeColor = DEF_STROKE_COLOR;
        }
        if (!TextUtils.isEmpty(fillColor) && (fillLength == 7 || fillLength == 9)) {

        } else {
            fillColor = DEF_FILL_COLOR;
        }
        int sColor = Color.parseColor(strokeColor);//边框颜色
        int solidColor = Color.parseColor(fillColor);//内部填充颜色

        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setGradientType(gradientType);
        gd.setColor(solidColor);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(strokeWidth, sColor);

        return gd;
    }

    public static GradientDrawable generateShape(int gradientType, int strokeWidth, int roundRadius, int strokeColor, int fillColor) {

        int sColor = strokeColor;//边框颜色
        int solidColor = fillColor;//内部填充颜色

        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setGradientType(gradientType);
        gd.setColor(solidColor);
        gd.setCornerRadius(roundRadius);
        gd.setStroke(strokeWidth, sColor);

        return gd;
    }
}
