package com.lidegou.lideshangmeng.mobile.util.dialog;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;

/**
 * Created by GO on 2016/12/19.
 */
public class Uri2Path {
    public static String uri2filePath(Uri uri, Activity activity) {

        String path = "";

        if (DocumentsContract.isDocumentUri(activity, uri)) {

            String wholeID = DocumentsContract.getDocumentId(uri);

            String id = wholeID.split(":")[1];

            String[] column = { MediaStore.Images.Media.DATA };

            String sel = MediaStore.Images.Media._ID + "=?";

            Cursor cursor = activity.getContentResolver().query(

                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, sel,

                    new String[] { id }, null);

            int columnIndex = cursor.getColumnIndex(column[0]);

            if (cursor.moveToFirst()) {

                path = cursor.getString(columnIndex);

            }

            cursor.close();

        } else {

            String[] projection = { MediaStore.Images.Media.DATA };

            Cursor cursor = activity.getContentResolver().query(uri,

                    projection, null, null, null);

            int column_index = cursor

                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            cursor.moveToFirst();

            path = cursor.getString(column_index);

        }

        return path;
    }

    public static byte[] getImageBytes(Bitmap bmp) {

        if (bmp == null)
            return null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] imageBytes = baos.toByteArray();

        return imageBytes;

    }

    public static Uri geturi(android.content.Intent intent,Context context) {
        Uri uri = intent.getData();
        String type = intent.getType();
        if (uri.getScheme().equals("file") && (type.contains("image/"))) {
            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[] { MediaStore.Images.ImageColumns._ID },
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    // do nothing
                } else {
                    Uri uri_temp = Uri
                            .parse("content://media/external/images/media/"
                                    + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                    }
                }
            }
        }
        return uri;
    }
}
