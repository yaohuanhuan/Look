package com.look.yx.look.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by yx on 2016/11/16.
 * 检测网络工具类
 */

public class NetWorkUtil {

    /**
     * @param context 最好传getApplicationContext防止内存泄露
     * @return 网络是否可用
     */
    public static boolean isNetWorkAvailable(Context context){
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null){
                return networkInfo.isConnected();
            }
        }
        return false;
    }

    /**
     * @param context 最好传getApplicationContext防止内存泄露
     * @return 检测wifi是否连接
     */
    public static boolean isWifiConnected(Context context){
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            //这种写法有点意思更加简洁
            return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        }
        return false;
    }



}
