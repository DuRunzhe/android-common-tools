package com.pull2me.android.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Author: runzhedu {durunzhe666@gmail.com}
 * @Time: 2018/9/25 下午10:56
 **/
public class DrawableResTools {

    public static Drawable res2Drawable(Context context, int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(resId, context.getTheme());
        } else {
            return context.getResources().getDrawable(resId);
        }
    }

    public static Drawable res2Drawable(Context context, int resId, int width, int height) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawable = context.getResources().getDrawable(resId, context.getTheme());
            drawable.setBounds(0, 0, width, height);
            return drawable;
        } else {
            Drawable drawable = context.getResources().getDrawable(resId);
            drawable.setBounds(0, 0, width, height);
            return drawable;
        }
    }

    /**
     * @param context
     * @param resId
     * @param density 屏幕密度
     * @return
     */
    public static Drawable res2Drawable(Context context, int resId, int density) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawableForDensity(resId, density, context.getTheme());
        } else {
            return context.getResources().getDrawableForDensity(resId, density);
        }
    }

    /**
     * @param context
     * @param imageFile
     * @return
     */
    public static Drawable file2Drawable(Context context, File imageFile) throws FileNotFoundException {
        FileInputStream is;
        is = new FileInputStream(imageFile);
        Drawable drawable = Drawable.createFromResourceStream(context.getResources(), null, is, "default_drawable");
        return drawable;
    }


    /**
     * TODO 大小未解决
     *
     * @param imageFile
     * @param width
     * @param height
     * @return
     */
    public static Drawable file2Drawable(Context context, File imageFile, int width, int height) {
        FileInputStream is;
        try {
            is = new FileInputStream(imageFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        Drawable drawable = Drawable.createFromResourceStream(context.getResources(), null, is, "default_drawable");
//        drawable.setBounds(0, 0, width, height);
        drawable = zoomDrawable(context, drawable, width, height);
        return drawable;
    }

    public static Drawable is2Drawable(Context context, InputStream is, String srcName) {
//        Drawable drawable = Drawable.createFromStream(is, srcName);
        if (android.text.TextUtils.isEmpty(srcName)) {
            srcName = "default_drawable";
        }
        Drawable drawable = Drawable.createFromResourceStream(context.getResources(), null, is, srcName);
//        drawable.setBounds(0, 0, width, height);
        return drawable;
    }

    public static Drawable is2Drawable(Context context, InputStream is, int width, int height, String srcName) {
        if (android.text.TextUtils.isEmpty(srcName)) {
            srcName = "default_drawable";
        }
        Drawable drawable = Drawable.createFromResourceStream(context.getResources(), null, is, srcName);
        drawable.setBounds(0, 0, width, height);
        return drawable;
    }

    /**
     * Bitmap转换为Drawable
     *
     * @param context
     * @param srcBitmap
     * @return
     */
    public Drawable bitmap2Drawable(Context context, Bitmap srcBitmap) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), srcBitmap);
        return bitmapDrawable;
    }

    /**
     * Drawable转换为Bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawable2Bitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 缩放Drawable
     *
     * @param context
     * @param drawable
     * @param w
     * @param h
     * @return
     */
    public static Drawable zoomDrawable(Context context, Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        // drawable转换成bitmap
        Bitmap oldbmp = drawable2Bitmap(drawable);
        // 创建操作图片用的Matrix对象
        Matrix matrix = new Matrix();
        // 计算缩放比例
        float sx = ((float) w / width);
        float sy = ((float) h / height);
        // 设置缩放比例
        matrix.postScale(sx, sy);
        // 建立新的bitmap，其内容是对原bitmap的缩放后的图
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(context.getResources(), newbmp);
    }
}
