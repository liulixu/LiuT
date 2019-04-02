package com.liu.easyp;

import android.content.Context;
import android.widget.Toast;

public class LiuPermissions {
    private static Toast t;
    public static void show(Context context,String string){
        if (t == null){
            t = Toast.makeText(context,string,Toast.LENGTH_SHORT);
        }else {
            t.setText(string);
        }
        t.show();
    }
}
