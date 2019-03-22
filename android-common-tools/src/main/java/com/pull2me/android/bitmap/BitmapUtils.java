package com.pull2me.android.bitmap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.io.File;

/**
 * Created by drz on 2016/4/3.
 */
public class BitmapUtils {
    /**
     * 从drawable资源文件中获取一个缩放后的Bitmap
     *
     * @param context
     * @param resId         图片资源ID
     * @param displayWidth  显示宽度
     * @param displayHeight
     * @return 缩放后的Bitmap对象
     */
    public static Bitmap getScaleBitmap(Context context, int resId, int displayWidth, int displayHeight) {
        Bitmap result = null;
        //获取图片缩放前的内存大小

        //创建bitmap工厂的配置参数
        BitmapFactory.Options opts = new BitmapFactory.Options();

        //返回一个null 没有bitmap   不去真正解析位图 但是能返回图片的一些信息(宽和高)
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resId, opts);
        //[3]获取图片原始的像素宽和高
        float originWidth = opts.outWidth;
        float originHeight = opts.outHeight;

        //[4]计算缩放比
        int scale = 1;//默认的缩放比
        float scalex = originWidth / displayWidth;
        float scaley = originHeight / displayHeight;
        if (scalex >= scaley && scalex > scale) {
            scale = (int) (scalex + 1);
        }
        if (scaley > scalex && scaley > scale) {
            scale = (int) (scaley + 1);
        }
        //[5]按照缩放比显示图片
        opts.inSampleSize = scale;

        //[6]开始真正的解析位图 加载到内存
        opts.inJustDecodeBounds = false;
        try {
            result = BitmapFactory.decodeResource(context.getResources(), resId, opts);
            int byteCount = result.getByteCount();
            Log.i("debug", resId + ":缩放后的Bitmap size byte=" + byteCount);
        } catch (OutOfMemoryError error) {
            Log.d("debug", resId + ":Outoffmemory error when decode bitmap in Class " + BitmapUtils.class.getSimpleName() + ",error=" + error.getMessage());
            error.printStackTrace();
        }

        return result;
    }

    /**
     * @param file
     * @param scale
     * @return
     */
    public static Bitmap resizeBitmap(File file, float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale); //长和宽放大缩小的比例
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }

    public static Bitmap resizeBitmap(Bitmap srcBitmap, float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, true);
        return resizeBmp;
    }

    public static Bitmap getScaleBitmap(File file, int displayWidth, int displayHeight) {
        Bitmap result = null;
        //获取图片缩放前的内存大小
//        long bitmapSize = getBitmapSize(context, resId);
//        Log.i("debug", resId + ":图片缩放前的内存大小=" + FileCalculateTool.getDataSize(bitmapSize));

        //创建bitmap工厂的配置参数
        BitmapFactory.Options opts = new BitmapFactory.Options();

        //返回一个null 没有bitmap   不去真正解析位图 但是能返回图片的一些信息(宽和高)
        opts.inJustDecodeBounds = true;
        String pathName = file.getAbsolutePath();
        BitmapFactory.decodeFile(pathName, opts);
        //[3]获取图片原始的像素宽和高
        float originWidth = opts.outWidth;
        float originHeight = opts.outHeight;


        //[4]计算缩放比
        int scale = 1;//默认的缩放比
        float scalex = originWidth / displayWidth;
        float scaley = originHeight / displayHeight;
        if (scalex >= scaley && scalex > scale) {
            scale = (int) (scalex + 1);
        }
        if (scaley > scalex && scaley > scale) {
            scale = (int) (scaley + 1);
        }
        //[5]按照缩放比显示图片
        opts.inSampleSize = scale;
        opts.outWidth = displayWidth;
        opts.outHeight = displayHeight;

        //[6]开始真正的解析位图 加载到内存
        opts.inJustDecodeBounds = false;
        try {
            result = BitmapFactory.decodeFile(pathName, opts);
            int byteCount = result.getByteCount();
            Log.i("debug", pathName + ":缩放后的Bitmap size byte=" + byteCount);
        } catch (OutOfMemoryError error) {
            Log.d("debug", pathName + ":Outoffmemory error when decode bitmap in Class " + BitmapUtils.class.getSimpleName() + ",error=" + error.getMessage());
            error.printStackTrace();
        }

        return result;
    }

    /**
     * 仅供调试
     *
     * @param activity
     * @param resId
     * @return
     */
    private static long getBitmapSize(Activity activity, int resId) {
        try {
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            float dpi = metrics.densityDpi;
            Log.i("debug", "-----dpi=" + dpi);
            BitmapFactory.Options opts = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), resId, opts);
            Log.i("debug", resId + ":图片的真实宽度 outWidth" + opts.outWidth + " ,outHeight" + opts.outHeight);
            return bitmap.getByteCount();
        } catch (OutOfMemoryError error) {
            Log.d("debug", resId + ":解析原始图片出错 Outoffmemory error when decode bitmap in Class " + BitmapUtils.class.getSimpleName() + ",error=" + error.getMessage());
            error.printStackTrace();
        }
        return -1;
    }

    /**
     * 计算将图片resId显示到disView中的缩放比
     *
     * @param context
     * @param disView ImageView控件
     * @param resId   图片资源iD
     * @return 缩放比
     */
    private static int calculateInSampleSize(Context context, View disView, int resId) {
        //创建bitmap工厂的配置参数
        BitmapFactory.Options opts = new BitmapFactory.Options();

        //返回一个null 没有bitmap   不去真正解析位图 但是能返回图片的一些信息(宽和高)
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resId, opts);
        //[3]获取图片原始的像素宽和高
        float originWidth = opts.outWidth;
        float originHeight = opts.outHeight;

        //[4]计算缩放比
        int scale = 1;//默认的缩放比
        float displayWidth = disView.getMeasuredWidth();
        float displayHeight = disView.getMeasuredHeight();
        if (displayHeight <= 0 || displayHeight <= 0) {
            throw new RuntimeException("无法获取控件的宽度");
        }

        float scalex = originWidth / displayWidth;
        float scaley = originHeight / displayHeight;
        if (scalex >= scaley && scalex > scale) {
            scale = (int) (scalex + 1);
        }
        if (scaley > scalex && scaley > scale) {
            scale = (int) (scaley + 1);
        }
        return scale;
    }

    /**
     * 计算缩放比例
     *
     * @param outWidth
     * @param outHeight
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    @Deprecated
    private static int calculateInSampleSize(float outWidth, float outHeight, float reqWidth, float reqHeight) {
        int inSampleSize = 1;
        if (reqHeight == 0 || reqWidth == 0) {
            return inSampleSize;
        }
        if (outHeight > reqHeight || outWidth > reqWidth) {
            final int heightRatio = Math.round(outHeight / reqHeight);
            final int widthRatio = Math.round(outWidth / reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        final float totalPixels = outWidth * outHeight;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;

        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }
}
