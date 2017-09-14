package com.lidegou.lideshangmeng.mobile.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.lidegou.lideshangmeng.mobile.App;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Y on 2017/2/8.
 */

public class MyImageAsync {

    public static void downPic(String url) {
        String needPath = Environment.getExternalStorageDirectory().getPath() + "/Test";
        File needFile = new File(needPath);
        //文件夹不存在，则创建它
        if (!needFile.exists()) {
            needFile.mkdirs();
        }

        needPath = needPath + "/" + System.currentTimeMillis() + ".jpg";

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                InputStream is = entity.getContent();

                File f = new File(needPath);
                f.createNewFile();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(f);
                    byte[] buf = new byte[1024];
                    int length;
                    while ((length = is.read(buf)) != -1) {
                        fos.write(buf, 0, length);
                    }
                    insertImageToSystemGallery(App.getApp(), needPath, BitmapFactory.decodeFile(needPath));
                    is.close();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void insertImageToSystemGallery(Context context, String filePath, Bitmap bitmap) {
        MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "", "");
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setData(uri);
        context.sendBroadcast(intent);
    }
}
