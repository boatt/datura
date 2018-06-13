package com.kcr.common.util;

import android.app.Application;
import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;



/**
 * Created by Liang_Lu on 2017/9/7.
 */

public class ToastUtils {
    private static Context mContext;
    private static Toast toast;

    public static void show(@StringRes int resId) {
        show(mContext.getResources().getString(resId));
    }

    public static void init(Application context) {
       mContext = context;
    }

    public static void show(CharSequence text) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
