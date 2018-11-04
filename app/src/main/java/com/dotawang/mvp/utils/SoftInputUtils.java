package com.dotawang.mvp.utils;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 软键盘工具类
 * Created by Dota.Wang on 2018/11/4.
 */

public class SoftInputUtils {
    /**
     * 判定是否需要隐藏
     */
    public static boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 隐藏软键盘
     */
    public static void HideSoftInput(IBinder token, Context context) {

        if (token != null) {

            InputMethodManager manager = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

            manager.hideSoftInputFromWindow(token,

                    InputMethodManager.HIDE_NOT_ALWAYS);

        }

    }

    /**
     * 关闭键盘
     *
     * @param context
     */
    public static void closeSoftInputKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus()
                        .getApplicationWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示键盘
     */
    public static void openSoftInputKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


}
