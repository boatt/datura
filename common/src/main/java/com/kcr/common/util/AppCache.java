package com.kcr.common.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.dtr.network.UserConstants;
import com.dtr.network.bean.LoginUserBean;
import com.orhanobut.hawk.Hawk;

public class AppCache {
    private static LoginUserBean user;

    /**
     * Desc: 获得本地登录用户ID
     *
     * @return string
     */
    public static String getUserId() {
        LoginUserBean user = getUser();
        if (null == user) {
            return "";
        }
        return user.getCustomerId() + "";
    }

    /**
     * Desc: 获得本地登录用户
     *
     * @return user
     */
    public static LoginUserBean getUser() {
        if (user == null) {
            try {
                user = Hawk.get(UserConstants.AUTH_USERBEAN_CACHE, null);
            } catch (Exception e) {
            }
        }
        return user;
    }

    /**
     * Desc: 保存本地用户数据
     *
     * @param user
     */
    public static void setUser(@NonNull LoginUserBean user) {
        Hawk.put(UserConstants.AUTH_USERBEAN_CACHE, user);

    }

    /**
     * Desc: 判断是否登录
     *
     * @return boolean
     */
    public static boolean isLogin() {
        String customerId = getUserId();
        return !TextUtils.isEmpty(customerId);
    }

    /**
     * Desc: 登出
     *
     * @return boolean
     */
    public static void loginOut() {
        user = null;
        Hawk.deleteAll();
    }


}
