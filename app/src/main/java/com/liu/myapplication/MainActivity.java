package com.liu.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.liu.easyp.LiuPermissions;
import com.liu.easyp.photo.C;
import com.liu.easyp.photo.TakePhotoes;

public class MainActivity extends AppCompatActivity {
    private Button mBtnShowDialog;
    private ImageView mImgView;

    private String picturePath = null;
    TakePhotoes takePhotoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LiuPermissions.show(this, "123");
        mBtnShowDialog = findViewById(R.id.show_dialog);
        mImgView = findViewById(R.id.img_);
        takePhotoes = new TakePhotoes(this, picturePath);
        takePhotoes.recoverState(savedInstanceState, MainActivity.class);
        mBtnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhotoes.showPictureDialog();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case C.GALLERY_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();
                    takePhotoes.loadGalleryBitmap(uri, mImgView);
                }
                break;
            case C.PICTURE_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    takePhotoes.loadPictureBitmap(mImgView);
                }
            default:
                break;
        }
    }
}
