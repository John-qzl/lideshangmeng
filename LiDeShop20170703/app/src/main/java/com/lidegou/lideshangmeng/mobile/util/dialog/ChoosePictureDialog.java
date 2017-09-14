package com.lidegou.lideshangmeng.mobile.util.dialog;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;

import com.jiongbull.jlog.JLog;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图片选择
 *
 * @author Y
 * @version 0.1
 * @date 2016年8月22日09:33:14
 */

public class ChoosePictureDialog extends BaseDialog {
    public static final String TAG = ChoosePictureDialog.class.getSimpleName();
    public static final String OUT_PUT = Config.ROOT.URL + "/img/portrait.jpg";

    private OnChoosePictureListener onChoosePictureListener;

    public ChoosePictureDialog(Context context) {
        super(context);
    }

    public void setOnChoosePictureListener(OnChoosePictureListener onChoosePictureListener) {
        this.onChoosePictureListener = onChoosePictureListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_choose_picture;
    }

    @Override
    protected void init() {
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        setCanceledOnTouchOutside(true);

        findViewById(R.id.btn_camera).setOnClickListener(this);
        findViewById(R.id.btn_photo_album).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
    }

    @Override
    public void show() {
        super.show();

        File file = new File(Config.ROOT.URL);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(file.getPath() + "/img");
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(OUT_PUT);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                JLog.e((e != null || e.getMessage() != null) ? "Exception is null" : e.getMessage());
            }
        }
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_camera:
                camera();
                break;
            case R.id.btn_photo_album:
                photoAlbum();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
        }
    }

    public void camera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        getActivity().startActivityForResult(intent, 1);
    }

    public void photoAlbum() {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        getActivity().startActivityForResult(intent, 0);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        Bitmap bitmap = null;
        if (requestCode == 0) {
            //获取从相册界面返回的缩略图
            bitmap = data.getParcelableExtra("data");
            if (bitmap == null) {//如果返回的图片不够大，就不会执行缩略图的代码，因此需要判断是否为null,如果是小图，直接显示原图即可
                try {
//                    if (data.getData().toString().equals("content")){}

                    //通过URI得到输入流
                    InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                    //通过输入流得到bitmap对象
                    bitmap = BitmapFactory.decodeStream(inputStream);
//                    saveToSDCard(bitmap);

                    if (onChoosePictureListener != null) {
                        onChoosePictureListener.choosePicture(comp(bitmap), OUT_PUT);
                    }
                } catch (FileNotFoundException e) {
                    JLog.e((e == null || e.getMessage() == null) ? "" : e.getMessage());
                }
            }
        } else if (requestCode == 1) {
            bitmap = (Bitmap) data.getExtras().get("data");
            saveToSDCard(bitmap);

            if (onChoosePictureListener != null) {
                onChoosePictureListener.choosePicture(comp(bitmap), OUT_PUT);
            }
        }
    }

    public static String getFilePathFromContentUri(Uri selectedVideoUri,
                                                   ContentResolver contentResolver) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};

        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
//      也可用下面的方法拿到cursor
//      Cursor cursor = this.context.managedQuery(selectedVideoUri, filePathColumn, null, null, null);

        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    private Bitmap compressImage(Bitmap image, int kb) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > kb) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    private Bitmap comp(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 10, baos);//这里压缩(1-50%)，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 200f;//这里设置高度为800f
        float ww = 200f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressImage(bitmap, 100);//压缩好比例大小后再进行质量压缩
    }


    /**
     * 把拍的照片保存到SD卡
     */
    public void saveToSDCard(Bitmap bitmap) {
        //先要判断SD卡是否存在并且挂载
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(OUT_PUT);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);//把图片数据写入文件
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            showToast("SD卡不存在");
        }
    }

    public interface OnChoosePictureListener {

        void choosePicture(Bitmap bitmap, String url);

    }

}
