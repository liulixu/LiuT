package com.liu.easyp.photo;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.DIRECTORY_PICTURES;

public class FileUtils {

    /**
     * 获取存储bitmap的文件
     * getExternalFileDir()提供的是私有的目录，在卸载APP后会删除
     * @param context
     * @return
     */
    public static String getBitmapDiskFile(Context context){
        String cachPath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())||!Environment.isExternalStorageRemovable()){
            cachPath = context.getExternalFilesDir(DIRECTORY_PICTURES).getAbsolutePath();
        }else cachPath = context.getFilesDir().getAbsolutePath();
        return new File(cachPath+File.separator+getBitmapDiskFileName()).getAbsolutePath();
    }

    /**
     * 生成Bitmap的文件名：日期，md5加密
     * @return
     */
    public static final String bitmapFormat = ".png";
    private static String getBitmapDiskFileName() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            String currentDate = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            mDigest.update(currentDate.getBytes("utf-8"));
            byte[] b = mDigest.digest();
            for (int i=0;i<b.length;i++){
                String hex = Integer.toHexString(0xff & b[i]);
                if (hex.length()==1){
                    stringBuilder.append('0');
                }
                stringBuilder.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Log.e("TAG","getBitmapDiskFileName_error:"+e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("TAG","getBitmapDiskFileName_error:"+e);
        }
        String fileName = stringBuilder.toString()+bitmapFormat;
        return fileName;
    }
}
