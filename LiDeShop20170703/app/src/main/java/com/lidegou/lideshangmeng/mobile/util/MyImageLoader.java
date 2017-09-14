package com.lidegou.lideshangmeng.mobile.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Y on 2016/8/15.
 */

public class MyImageLoader {
    private static final String IMG_URL = "http://www.lidegou.com/";
    private Context context;
    private static MyImageLoader myImageLoader;
    private DisplayImageOptions options;

    private MyImageLoader() {
        options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.pic_loading) // resource or drawable
//                .showImageForEmptyUri(R.drawable.pic_fale) // resource or drawable
//                .showImageOnFail(R.drawable.pic_fale) // resource or drawable
//                .resetViewBeforeLoading(false)  // default
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.ARGB_4444) // default
//                .displayer(new FadeInBitmapDisplayer(500)) // default
                .build();
    }


//    private static class ImageLoaderHolder {
//
//        private  MyImageLoader instance = new MyImageLoader();
//    }

    public static MyImageLoader getInstance() {
        if (myImageLoader == null) {
            myImageLoader = new MyImageLoader();
        }
        return myImageLoader;
    }

    /**
     * 初始化图片加载
     */
    public void init(Context context) {
        this.context = context;
    }

    /**
     * 加载图片
     */
    public void loaderImage(int errDrawable, ImageView imageView) {
        Glide.with(context)
                .load(errDrawable)
                .into(imageView);
    }

    /**
     * 加载图片
     */
    public void loaderImage(String url, ImageView imageView) {
        ImageLoader.getInstance().displayImage(url, imageView, options);
//        Glide.with(context)
//                .load(url)
//                .centerCrop()
//                .into(imageView);
    }

    /**
     * 加载图片
     */
    public void loaderImageMy(String url, ImageView imageView) {
        ImageLoader.getInstance().displayImage(url, imageView, options);
//        Glide.with(context)
//                .load(url)
//                .into(imageView);
    }


    /**
     * 加载图片
     */
    public void loaderImage(String url, ImageView imageView, int errDrawable) {
        ImageLoader.getInstance().displayImage(IMG_URL + url, imageView, options);
//        Glide.with(context)
//                .load(IMG_URL + url)
//                .error(errDrawable)
//                .into(imageView);
    }

    /**
     * 加载图片
     */
    public void loaderImageAll(String url, ImageView imageView, int errDrawable) {
        ImageLoader.getInstance().displayImage(url, imageView, options);
//        Glide.with(context)
//                .load(IMG_URL + url)
//                .error(errDrawable)
//                .into(imageView);
    }

    /**
     * 加载图片
     */
    public void loaderImage(String url, ImageView imageView, int width, int height) {
        ImageLoader.getInstance().displayImage(IMG_URL + url, imageView, options);

//        Glide.with(context)
//                .load(IMG_URL + url)
//                .override(width, height)
//                .into(imageView);
    }

    /**
     * 加载图片
     */
//    public void loaderImage(String url, SimpleTarget<Bitmap> target) {
//        ImageLoader.getInstance().displayImage(IMG_URL + url,imageView,options);
//        Glide.with(context)
//                .load(IMG_URL + url)
//                .asBitmap()
//                .into(target);
//    }

//    public void clearImage(ImageView imageView) {
//        Glide.clear(imageView);
//    }


    /**
     * 获取网落图片资源
     */
    public void returnBitMap(String url, final ImageView imView, int type) {
        String imageUrl;
        if (type == 1) {
            imageUrl = IMG_URL + url;
        } else {
            imageUrl = url;
        }
        new AsyncTask<String, Void, Bitmap>() {
            //该方法运行在后台线程中，因此不能在该线程中更新UI，UI线程为主线程
            @Override
            protected Bitmap doInBackground(String... params) {
                Bitmap bitmap = null;
                try {
                    String url = params[0];
                    URL HttpURL = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) HttpURL.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bitmap;
            }

            //在doInBackground 执行完成后，onPostExecute 方法将被UI 线程调用，
            // 后台的计算结果将通过该方法传递到UI线程，并且在界面上展示给用户.
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    imView.setImageBitmap(bitmap);
                }
            }
        }.execute(imageUrl);
    }


    public void loadImageReturnBitMap(final ImageView imView, String url, int type) {
        String imageUrl;
        if (type == 1) {
            imageUrl = IMG_URL + url;
        } else {
            imageUrl = url;
        }
        ImageLoader.getInstance().displayImage(imageUrl, imView, options);
    }

    //获得圆角图片的方法
    public Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

}
