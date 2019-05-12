package com.pull2me.android.dialog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

/**
 * @Author: 杜润哲(durunzhe@supermap.com)
 * @Time: 2017/6/27 10:35
 */

public abstract class BaseDialogBuilder<Q extends BaseDialog> {
    protected final LayoutInflater inflater;
    protected Activity activity;
    private Integer styleId;
    protected boolean cancelAble = true;
    protected View mDialogView;
    protected Q mDialog;

    public BaseDialogBuilder(Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }

    public BaseDialogBuilder anim(Integer styleId) {
        this.styleId = styleId;
        return this;
    }

    public BaseDialogBuilder cancelAble(boolean cancelAble) {
        this.cancelAble = cancelAble;
        return this;
    }

    public Q build() {
        mDialogView = initializeDialogView();
        mDialog = initializeDialog(mDialogView);
        mDialog.setCancelable(cancelAble);
        //设置出入动画
        Window window = mDialog.getWindow();
        if (styleId != null) {
            window.setWindowAnimations(styleId);
        }

//        final int measuredHeight = mDialogView.getMeasuredHeight();
//        mDialogView.post(new Runnable() {
//            @Override
//            public void run() {
//                dialog.setShowStyle(activity, 0.85f, LinearLayout.LayoutParams.WRAP_CONTENT, 0,
//                        AppUtils.getDeviceHeight(activity) / 2 - measuredHeight);
//            }
//        });
        return mDialog;
    }

    /**
     * 初始化dialog视图
     *
     * @return
     */
    protected abstract View initializeDialogView();

    /**
     * 初始化对话框
     *
     * @param dialogView
     * @return
     */
    protected abstract Q initializeDialog(View dialogView);
}
