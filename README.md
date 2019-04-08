# LiuT
[![](https://jitpack.io/v/liulixu/LiuT.svg)](https://jitpack.io/#liulixu/LiuT)
使用方法：
在app的build.gradle中添加依赖：
```
implementation 'com.github.liulixu:LiuT:v1.3.3.1'
```

ACtivity中使用

创建对象：
```
TakePhotoes takePhotoes = new TakePhotoes(this, picturePath);
```

2.在onCreate()中调用
```
takePhotoes.recoverState(savedInstanceState, MainActivity.class);
```
该方法作用为当重建时，从onCreate中获取图片路径

3.初始化用来装图片的  ImageView mImgView

4.实现onActivityResult（）
```
  switch (requestCode) {
            //图库返回
            case C.GALLERY_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();
                    takePhotoes.loadGalleryBitmap(uri, mImgView);
                }
                break;
            //拍照返回
            case C.PICTURE_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    takePhotoes.loadPictureBitmap(mImgView);
                }
            default:
                break;
        }
```

5.资源清理
```
 @Override
    protected void onDestroy() {
        super.onDestroy();
        takePhotoes.clearCompositeSubscription();
    }
```
