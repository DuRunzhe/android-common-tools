package com.pull2me.android.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.Set;

/**
 * @Author: runzhedu {durunzhe666@gmail.com}
 * @Time: 2019/3/10 下午6:39
 **/
public class SpUtils {

    private SpUtils() {

    }


    private static SharedPreferences sp;

    public static void initSharedPreferences(Context context) {
        if (sp == null) {
            synchronized (SpUtils.class) {
                sp = context.getSharedPreferences(context.getApplicationInfo().packageName + "_AppSharedPreferences", Context.MODE_PRIVATE);
            }
        }
    }

    /**
     * @param key         key of sp
     * @param value       value of sp
     * @param synchronous 是否同步提交,true=同步
     */
    public static void put(@NonNull String key, @NonNull String value, boolean synchronous) {
        checkSp();
        if (synchronous) {
            sp.edit().putString(key, value).commit();
        } else {
            sp.edit().putString(key, value).apply();
        }
    }

    /**
     * @param key   key of sp
     * @param value value of sp
     */
    public static void put(@NonNull String key, @NonNull String value) {
        checkSp();
        sp.edit().putString(key, value).apply();
    }

    /**
     * @param key   key of sp
     * @param value value of sp
     */
    public static void putLong(@NonNull String key, @NonNull Long value) {
        checkSp();
        sp.edit().putLong(key, value).apply();
    }

    /**
     * @param key         key of sp
     * @param value       value of sp
     * @param synchronous 是否同步提交,true=同步
     */
    public static void putLong(@NonNull String key, @NonNull Long value, boolean synchronous) {
        checkSp();
        if (synchronous) {
            sp.edit().putLong(key, value).commit();
        } else {
            sp.edit().putLong(key, value).apply();
        }
    }

    /**
     * @param key   key of sp
     * @param value value of sp
     */
    public static void putInt(@NonNull String key, @NonNull Integer value) {
        checkSp();
        sp.edit().putInt(key, value).apply();
    }

    /**
     * @param key         key of sp
     * @param value       value of sp
     * @param synchronous 是否同步提交,true=同步
     */
    public static void putInt(@NonNull String key, @NonNull Integer value, boolean synchronous) {
        checkSp();
        if (synchronous) {
            sp.edit().putInt(key, value).commit();
        } else {
            sp.edit().putInt(key, value).apply();
        }
    }

    /**
     * @param key   key of sp
     * @param value value of sp
     */
    public static void putBoolean(@NonNull String key, @NonNull Boolean value) {
        checkSp();
        sp.edit().putBoolean(key, value).apply();
    }

    /**
     * @param key         key of sp
     * @param value       value of sp
     * @param synchronous 是否同步提交,true=同步
     */
    public static void putBoolean(@NonNull String key, @NonNull Boolean value, boolean synchronous) {
        checkSp();
        if (synchronous) {
            sp.edit().putBoolean(key, value).commit();
        } else {
            sp.edit().putBoolean(key, value).apply();
        }
    }

    /**
     * @param key   key of sp
     * @param value value of sp
     */
    public static void putFloat(@NonNull String key, @NonNull Float value) {
        checkSp();
        sp.edit().putFloat(key, value).apply();
    }

    /**
     * @param key         key of sp
     * @param value       value of sp
     * @param synchronous 是否同步提交,true=同步
     */
    public static void putFloat(@NonNull String key, @NonNull Float value, boolean synchronous) {
        checkSp();
        if (synchronous) {
            sp.edit().putFloat(key, value).commit();
        } else {
            sp.edit().putFloat(key, value).apply();
        }
    }

    /**
     * @param key   key of sp
     * @param value value of sp
     */
    public static void putStringSet(@NonNull String key, @NonNull Set<String> value) {
        checkSp();
        sp.edit().putStringSet(key, value).apply();
    }

    /**
     * @param key         key of sp
     * @param value       value of sp
     * @param synchronous 是否同步提交,true=同步
     */
    public static void putStringSet(@NonNull String key, @NonNull Set<String> value, boolean synchronous) {
        checkSp();
        if (synchronous) {
            sp.edit().putStringSet(key, value).commit();
        } else {
            sp.edit().putStringSet(key, value).apply();
        }
    }

    /**
     * @param key      key of sp
     * @param defValue 默认值
     * @return
     */
    public static String get(@NonNull String key, @NonNull String defValue) {
        return sp.getString(key, defValue);
    }

    /**
     * @param key key of sp
     * @return
     */
    public static String get(@NonNull String key) {
        return sp.getString(key, "");
    }

    /**
     * @param key      key of sp
     * @param defValue 默认值
     * @return
     */
    public static long getLong(@NonNull String key, @NonNull Long defValue) {
        return sp.getLong(key, defValue);
    }

    /**
     * @param key key of sp
     * @return
     */
    public static long getLong(@NonNull String key) {
        return sp.getLong(key, 0);
    }

    /**
     * @param key      key of sp
     * @param defValue 默认值
     * @return
     */
    public static long getInt(@NonNull String key, @NonNull Integer defValue) {
        return sp.getInt(key, defValue);
    }

    /**
     * @param key key of sp
     * @return
     */
    public static long getInt(@NonNull String key) {
        return sp.getInt(key, 0);
    }

    /**
     * @param key      key of sp
     * @param defValue 默认值
     * @return
     */
    public static boolean getBoolean(@NonNull String key, @NonNull Boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    /**
     * @param key key of sp
     * @return
     */
    public static boolean getBoolean(@NonNull String key) {
        return sp.getBoolean(key, false);
    }

    /**
     * @param key      key of sp
     * @param defValue 默认值
     * @return
     */
    public static float getFloat(@NonNull String key, @NonNull Float defValue) {
        return sp.getFloat(key, defValue);
    }

    /**
     * @param key key of sp
     * @return
     */
    public static float getFloat(@NonNull String key) {
        return sp.getFloat(key, 0);
    }

    /**
     * @param key      key of sp
     * @param defValue 默认值
     * @return
     */
    public static Set<String> getStringSet(@NonNull String key, @NonNull Set<String> defValue) {
        return sp.getStringSet(key, defValue);
    }

    /**
     * @param key key of sp
     * @return
     */
    public static Set<String> getStringSet(@NonNull String key) {
        return sp.getStringSet(key, Collections.EMPTY_SET);
    }


    private static void checkSp() {
        if (sp == null) {
            throw new IllegalArgumentException("You must call initSharedPreferences(Contexg) first");
        }
    }

    static final class Holder {
        private static final SpUtils instance = new SpUtils();
    }
}
