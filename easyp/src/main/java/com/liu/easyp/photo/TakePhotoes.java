package com.liu.easyp.photo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class TakePhotoes implements CreatePictureDialog.ResultListener {

    private String TAG = "";
    private Activity mContext;
    private String mPicPath;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    public TakePhotoes(Activity context,String picPath){
        this.mContext = context;
        this.mPicPath = picPath;
    }
    private CreatePictureDialog picture_dialog;

    public  void showPictureDialog(){
        if (picture_dialog == null) {
            this.picture_dialog = new CreatePictureDialog(mContext);
            this.picture_dialog.setResultListener(this);
        }
        if (!picture_dialog.isShowing()) {
            picture_dialog.show();
        }
    }
    @Override
    public void camera() {
        this.mPicPath = FileUtils.getBitmapDiskFile(mContext);
        CameraUtils.openCamera(mContext, C.PICTURE_CODE, this.mPicPath);
    }

    @Override
    public void photoAlbum() {
        CameraUtils.openGallery(mContext,C.GALLERY_CODE);
    }


    /**
     * 当程序重建此activity后，从onCreate参数中获取图片路径
     * @param saveInstanceState
     * @param t
     * 此方法必须在onCreate方法中实现
     *且需要在activity中onSaveInstanceState方法中实现
     * outState.putString(TAG,mPicPath)
     */
    public void recoverState(Bundle saveInstanceState,Class<?> t){
        if (saveInstanceState!=null){
            TAG = t.getSimpleName();
            this.mPicPath = saveInstanceState.getString(TAG);
        }
    }

    private void loadGalleryBitmap(Uri uri, ImageView view) {
        Observable<Bitmap> bitmapObservable=ObservableUtils.loadGalleryBitmap(mContext.getApplicationContext(),uri,view);
        executeObservableTask(bitmapObservable,view);
    }
    private void loadPictureBitmap(ImageView view) {
        Observable<Bitmap> bitmapObservable= ObservableUtils.loadPictureBitmap(mContext.getApplicationContext(), mPicPath, view);
        executeObservableTask(bitmapObservable,view);
    }
    private void executeObservableTask(Observable<Bitmap> observable,ImageView view) {
        Subscription subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bitmap ->
                                view.setImageBitmap(bitmap)
                        , error ->
                                Toast.makeText(mContext, "加载图片出错",Toast.LENGTH_SHORT).show()
                );
        this.compositeSubscription.add(subscription);
    }

    public void clearCompositeSubscription(){
        compositeSubscription.clear();
    }

}
