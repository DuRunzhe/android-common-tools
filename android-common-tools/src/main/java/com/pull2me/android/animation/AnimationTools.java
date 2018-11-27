package com.pull2me.android.animation;

import android.content.Context;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;


/**
 * Created by Administrator on 2016/10/9 0009.
 */

public class AnimationTools {
//    public static final int DIRECTION_UP = 0;
//    public static final int DIRECTION_DOWN = 1;
//    public static final int DIRECTION_LEFT = 2;
//    public static final int DIRECTION_RIGHT = 3;
//
//    /**
//     * 透明渐变动画
//     *
//     * @param view
//     */
//    public static void alphaAnimation(View view) {
//        ViewHelper.setAlpha(view, 0.1f);
//        ViewPropertyAnimator.animate(view).alpha(1f).setDuration(1000).start();
//
//    }
//
//    /**
//     * 旋转动画
//     *
//     * @param view
//     * @param danwei
//     */
//    public static void rotationAnimation(View view, int danwei) {
//        ViewHelper.setRotationX(view, danwei * 25);
////        ViewHelper.setRotationY(view, -danwei * 30);
//        ViewPropertyAnimator.animate(view).rotationX(0f).rotationY(0f).setDuration(500).start();
//    }
//
//    /**
//     * 缩放动画
//     *
//     * @param view
//     */
//    public static void scaleAnimation(View view) {
//        ViewHelper.setScaleX(view, 0.85f);
//        ViewHelper.setScaleY(view, 0.85f);
//        ViewHelper.setAlpha(view, 0.3f);
//        ViewPropertyAnimator.animate(view).alpha(1f).setDuration(500).start();
//        ViewPropertyAnimator.animate(view).scaleX(1f).setDuration(500).start();
//        ViewPropertyAnimator.animate(view).scaleY(1f).setDuration(500).start();
//    }
//
//    /**
//     * 移动动画
//     *
//     * @param danwei
//     * @param view
//     */
//    public static void transAnimation(View view, int danwei) {
//        // 先下移100
//        ViewHelper.setTranslationY(view, danwei * 100);
//        // 再还原
//        ViewPropertyAnimator.animate(view).translationY(0).setDuration(500).start();
//    }
//
//    /**
//     * 上下浮动动画效果
//     *
//     * @param view
//     * @param direction  移动方向（非负数--向上，负数-向下）
//     * @param space      相对自身的长度（0-1）
//     * @param times      单次动画持续时间
//     * @param repeatTime 重复次数（0不重复）
//     */
//    public static void transUpDown(View view, int direction, float space, long times, int repeatTime) {
////        TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.2f, Animation.RELATIVE_TO_PARENT, 0.5f,
////                Animation.RELATIVE_TO_PARENT, 0.2f, Animation.RELATIVE_TO_PARENT, 0.5f);
//        direction = direction < 0 ? 1 : -1;
//        TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
//                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, direction * space);
//        loadAnimation(ta, times, repeatTime, Animation.REVERSE, view);
//    }
//
//    /**
//     * 移动动画
//     *
//     * @param view
//     * @param direction
//     * @param space
//     * @param times
//     */
//    public static void transView(View view, int direction, float space, long times) {
//        ViewPropertyAnimator animate = ViewPropertyAnimator.animate(view);
//        switch (direction) {
//            case DIRECTION_UP:
//                animate.translationY(-space).setDuration(times).start();
//                break;
//            case DIRECTION_DOWN:
//                animate.translationY(space).setDuration(times).start();
//                break;
//            case DIRECTION_LEFT:
//                animate.translationX(space).setDuration(times).start();
//                break;
//            case DIRECTION_RIGHT:
//                animate.translationX(-space).setDuration(times).start();
//                break;
//            default:
//                break;
//        }
//    }
//
//    /**
//     * @param animation   动画效果对象
//     * @param duration    设置动画执行时间
//     * @param repeatCount 设置重复次数
//     * @param repeatMode  设置动画执行的模式
//     * @param imageView   imageView控件
//     */
//    public static void loadAnimation(Animation animation, long duration, int repeatCount, int repeatMode, View imageView) {
//        // AlphaAnimation aa = new AlphaAnimation(1.0f, 0f);
//        animation.setDuration(duration);// 设置动画执行时间
//        animation.setRepeatCount(repeatCount);// 设置重复次数
//        animation.setRepeatMode(repeatMode);// 设置动画执行的模式
//        imageView.startAnimation(animation);
//    }
//
//    /**
//     * 抖动动画
//     *
//     * @param context
//     * @param view
//     * @param animResId
//     */
//    public static void shakeAnimation(Context context, View view, int animResId) {
//        Animation animation = AnimationUtils.loadAnimation(context, animResId);
//        view.startAnimation(animation);
//    }
}
