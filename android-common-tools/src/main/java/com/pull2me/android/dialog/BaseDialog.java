package com.pull2me.android.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by drz on 2016/3/25.
 */
public class BaseDialog extends Dialog implements View.OnClickListener {
    protected Context mContext;
    public View mView;//View对象
    private int mViewId;

    public BaseDialog(Context context, View view) {
        super(context);
        mContext = context;
        mView = view;
    }

    public BaseDialog(Context context, int theme, View view) {
        super(context, theme);
        mContext = context;
        mView = view;
    }

    /**
     * Create a Dialog window that uses the default dialog frame style.
     *
     * @param context The Context the Dialog is to run it.  In particular, it
     *                uses the window manager and theme in this context to
     *                present its UI.
     */
    public BaseDialog(Context context) {
        super(context);
        mContext = context;
    }

    /**
     * Create a Dialog window that uses a custom dialog style.
     *
     * @param context The Context in which the Dialog should run. In particular, it
     *                uses the window manager and theme from this context to
     *                present its UI.
     * @param theme   A style resource describing the theme to use for the
     *                window. See <a href="{@docRoot}guide/topics/resources/available-resources.html#stylesandthemes">Style
     *                and Theme Resources</a> for more information about defining and using
     *                styles.  This theme is applied on top of the current theme in
     */
    public BaseDialog(Context context, int theme) {
        super(context, theme);
        mContext = context;
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(mView, layoutParams);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }

    public void setStyle() {

    }

    /**
     * 设置显示样式（一定要在show方法之后调用）
     *
     * @param activity
     * @param percentWid 宽度占屏幕的百分比
     * @param height     高度（px）
     * @param left       坐偏移
     * @param top        右偏移
     */
    public void setShowStyle(Activity activity, float percentWid, int height, int left, int top) {
        //设置默认的显示效果
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //获取屏幕的宽和高
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        //设置相对父控件的位置
//        dialogWindow.setGravity( Gravity.CENTER);
        dialogWindow.setGravity(Gravity.TOP | Gravity.CENTER);
        lp.y = top;
        lp.x = left;
//        注意：该大小要在不小于布局文件的宽高时生效，可以把布局设置为匹配父窗体。
        Point outPoint = new Point();
        d.getSize(outPoint);
        lp.width = (int) (outPoint.x * percentWid); //宽度设置为屏幕的0.95
//        lp.width = (int) (d.getWidth() * percentWid); //宽度设置为屏幕的0.95
        lp.height = height; // 高度

        dialogWindow.setAttributes(lp);
    }

    public void setShowStyle(Activity activity, int location, float percentWid, int height, int left, int top) {
        //设置默认的显示效果
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //获取屏幕的宽和高
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        //设置相对父控件的位置
//        dialogWindow.setGravity( Gravity.CENTER);
        dialogWindow.setGravity(location);
        lp.y = top;
        lp.x = left;
//        注意：该大小要在不小于布局文件的宽高时生效，可以把布局设置为匹配父窗体。
        Point outPoint = new Point();
        d.getSize(outPoint);
        lp.width = (int) (outPoint.x * percentWid); //宽度设置为屏幕的0.95
//        lp.width = (int) (d.getWidth() * percentWid); //宽度设置为屏幕的0.95
        lp.height = height; // 高度

        dialogWindow.setAttributes(lp);
    } public void setShowStyle(Activity activity, int location, int width, int height, int left, int top) {
        //设置默认的显示效果
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //获取屏幕的宽和高
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        //设置相对父控件的位置
//        dialogWindow.setGravity( Gravity.CENTER);
        dialogWindow.setGravity(location);
        lp.y = top;
        lp.x = left;
//        注意：该大小要在不小于布局文件的宽高时生效，可以把布局设置为匹配父窗体。
        Point outPoint = new Point();
        d.getSize(outPoint);
        lp.width = width;
//        lp.width = (int) (d.getWidth() * percentWid); //宽度设置为屏幕的0.95
        lp.height = height; // 高度

        dialogWindow.setAttributes(lp);
    }

    /**
     * 绑定事件函数
     *
     * @param run
     */
    public void bindFunction(Runnable run) {
        run.run();
    }
}
