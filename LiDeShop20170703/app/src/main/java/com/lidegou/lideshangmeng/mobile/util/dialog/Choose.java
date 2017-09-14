package com.lidegou.lideshangmeng.mobile.util.dialog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;

import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 图片选择
 *
 * @author Y
 * @version 0.1
 * @date 2016年8月22日09:33:14
 */

public class Choose extends BaseDialog {
    public static final String TAG = ChoosePictureDialog.class.getSimpleName();
    public static final String OUT_PUT = Config.ROOT.URL + "/img/";
    public String savePath = "";

    public Choose(Context context) {
        super(context);
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
            }
        }
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_camera:
                camera();
                dismiss();
                break;
            case R.id.btn_photo_album:
                photoAlbum();
                dismiss();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
        }
    }

    public void camera() {
        savePath = OUT_PUT + System.currentTimeMillis() + ".png";
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(savePath)));
        getActivity().startActivityForResult(intent, 1);
    }

    public void photoAlbum() {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        getActivity().startActivityForResult(intent, 2);
    }


    /**
     * 把拍的照片保存到SD卡
     */
    public void saveToSDCard(Bitmap bitmap, String picName) {
        savePath = OUT_PUT + picName + ".png";
        //先要判断SD卡是否存在并且挂载
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(savePath);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);//把图片数据写入文件
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

    public String getSavePath() {
        if (savePath.equals("")) {

        }
        return savePath;
    }

    public String getNowPath() {
        return OUT_PUT + System.currentTimeMillis() + ".png";
    }
}