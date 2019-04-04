package com.liu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.liu.easyp.LiuPermissions;

public class MainActivity extends AppCompatActivity {
    private Button mBtnShowDialog;
    private ImageView mImgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LiuPermissions.show(this,"123");

        mBtnShowDialog = findViewById(R.id.show_dialog);
        mImgView = findViewById(R.id.img_);

        mBtnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
