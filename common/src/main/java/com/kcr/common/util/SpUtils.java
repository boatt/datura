package com.kcr.common.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * SP工具类
 * Created by Administrator on 2016/6/15.
 */
public class SpUtils {

    private static final String FILE_NAME = "gogohelp";
    private static final String FILE_NAME_EXT = "gogohelp_ext";
    private static Context mContext;

    public static void putBoolean(Context context, String key, boolean flag) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, flag);
        edit.commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        boolean b = sp.getBoolean(key, defaultValue);
        return b;
    }

    public static void putString(Context context, String key, String flag) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, flag);
        edit.commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public static void putInt(Context context, String key, int flag) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, flag);
        edit.commit();
    }

    public static void putLong(Context context, String key, long flag) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putLong(key, flag);
        edit.commit();
    }

    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static long getLong(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    /* writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
       * 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
         @param object 待加密的转换为String的对象
       * @return String   加密后的String
       */
    private static String Object2String(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            String string = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            objectOutputStream.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用Base64解密String，返回Object对象
     *
     * @param objectString 待解密的String
     * @return object      解密后的object
     */
    private static Object String2Object(String objectString) {
        byte[] mobileBytes = Base64.decode(objectString.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 使用SharedPreference保存对象
     *
     * @param key        储存对象的key
     * @param saveObject 储存的对象
     */
    public static void putObject(Context context, String key, Object saveObject) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME_EXT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String string = Object2String(saveObject);
        editor.putString(key, string);
        editor.commit();
    }

    /**
     * 获取SharedPreference保存的对象
     *
     * @param key 储存文件的key
     * @param key 储存对象的key
     * @return object 返回根据key得到的对象
     */
    public static Object getObject(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME_EXT, Context.MODE_PRIVATE);
        String string = sp.getString(key, null);
        if (string != null) {
            Object object = String2Object(string);
            return object;
        } else {
            return null;
        }
    }

    public static void clearData(Context context) {
        if (context == null) {
            return;
        }
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

    //清楚指定key
    public static void clearKey(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME_EXT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }


    public static void clearLogin() {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.commit();
    }

    public static void init(Context context) {
        mContext = context;

    }
}
