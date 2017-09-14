package com.lidegou.lideshangmeng.mobile.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Y on 2017/3/16.
 */

public class BitmapUtils {
    public final static Bitmap compressBitmap(String path) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = caculateInSampleSize(options, 600, 600);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    private final static int caculateInSampleSize(BitmapFactory.Options options, int rqsW, int rqsH) {
        int w = options.outWidth;
        int h = options.outHeight;
        // 想要缩放的目标尺寸
        float hh = rqsH;// 设置高度为240f时，可以明显看到图片缩小了
        float ww = rqsW;// 设置宽度为120f，可以明显看到图片缩小了
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (w / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (h / hh);
        }
        if (be <= 0) be = 1;
        return be;
    }


    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.encodeToString(buffer, Base64.DEFAULT);
    }

    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
