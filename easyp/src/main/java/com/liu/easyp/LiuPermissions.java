package com.liu.easyp;

import android.content.Context;
import android.widget.Toast;

public class LiuPermissions {
    private static Toast toast;
    public static void show(Context context, String str){
        /**
         * 为防止内存溢出，不直接使用context。此处改为getApplicationContext()
         */
        if (toast == null){
            toast = Toast.makeText(context.getApplicationContext(),str,Toast.LENGTH_SHORT);
        }else toast.setText(str);
        toast.show();
    }
}
