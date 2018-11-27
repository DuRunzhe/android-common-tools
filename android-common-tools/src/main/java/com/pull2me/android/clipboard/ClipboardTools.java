package com.pull2me.android.clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * Created by drz on 2016/10/31.
 */

public class ClipboardTools {

    private static ClipboardManager mCbService;

    private static ClipboardManager init(Context context) {
        if (mCbService == null) {
            synchronized (ClipboardTools.class) {
                mCbService = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            }
        }
        return mCbService;
    }

    /**
     * 复制数据到剪贴板
     *
     * @param context
     * @param text
     */
    public static void copyTextClipboard(Context context, String text) {
        ClipboardManager manager = init(context);
        ClipData data = ClipData.newPlainText("text", text);
        manager.setPrimaryClip(data);
    }
}
