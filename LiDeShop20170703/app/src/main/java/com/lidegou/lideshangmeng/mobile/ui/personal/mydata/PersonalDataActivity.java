package com.lidegou.lideshangmeng.mobile.ui.personal.mydata;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.UserInfo;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.login.LoginActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.mydata.address.AddressListActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.mydata.manAndgril.SetGenderActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.mydata.updateEmail.EmailActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.updatePwd.UpdatePwdActivity;
import com.lidegou.lideshangmeng.mobile.util.BitmapUtils;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;
import com.lidegou.lideshangmeng.mobile.util.Prefs;
import com.lidegou.lideshangmeng.mobile.util.dialog.Choose;
import com.lidegou.lideshangmeng.mobile.util.dialog.PromptDialog;
import com.lidegou.lideshangmeng.mobile.util.view.CircleImage;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Y on 2016/12/3.
 * 个人资料
 */
public class PersonalDataActivity extends BaseActivity implements PersonalDataContract.View {

    private CircleImage user_img;
    private TextView user_phone, user_sex, user_telephone, user_email, user_idcard;
    private PersonalDataContract.Presenter presenter;
    private Choose choosePictureDialog;
    private String picText;
    private UserInfo.UserInfoBean userInfoBean;
    private PromptDialog promptDialog;

    private String needPath = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_data;
    }

    protected void init() {
        choosePictureDialog = new Choose(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
        findViewById(R.id.update_password).setOnClickListener(this);
        findViewById(R.id.re_email).setOnClickListener(this);
        findViewById(R.id.exit).setOnClickListener(this);
        findViewById(R.id.rel_goods_address).setOnClickListener(this);
        findViewById(R.id.re_imei).setOnClickListener(this);
        user_img = (CircleImage) findViewById(R.id.user_img);
        user_phone = (TextView) findViewById(R.id.user_phone);
        user_sex = (TextView) findViewById(R.id.user_sex);
        user_telephone = (TextView) findViewById(R.id.user_telephone);
        user_email = (TextView) findViewById(R.id.user_email);
        user_idcard = (TextView) findViewById(R.id.user_idcard);
        user_sex.setOnClickListener(this);
        user_img.setOnClickListener(this);
        promptDialog = new PromptDialog(this);

    }

    @Override
    protected void viewClick(View v) {
        switch (v.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.update_password:
                startActivity(new Intent(PersonalDataActivity.this, UpdatePwdActivity.class));
                break;
            case R.id.re_email:
                startActivity(new Intent(PersonalDataActivity.this, EmailActivity.class));
                break;
            case R.id.exit:
                promptDialog.setMsg("是否退出登录");
                promptDialog.setOnPromptClickListener(new PromptDialog.OnPromptClickListener() {
                    @Override
                    public void promptConfirmClick(View view) {
                        presenter.loginout();
                        getApp().setUid("");
                        Config.User.STATUS = false;
                        Prefs.with(getContext()).writeBoolean(Config.User.AUTOLOGIN, false);
                    }

                    @Override
                    public void promptCancelClick(View view) {
                        promptDialog.dismiss();
                    }
                });
                promptDialog.show();
                break;
            case R.id.rel_goods_address:
                startActivity(new Intent(PersonalDataActivity.this, AddressListActivity.class));
                break;
            case R.id.user_sex:
                Intent intent = new Intent(PersonalDataActivity.this, SetGenderActivity.class);
                intent.putExtra("sex", Integer.valueOf(userInfoBean.getSex()));
                startActivity(intent);
                break;
            case R.id.user_img:
                choosePictureDialog.show();
                break;
            case R.id.re_imei:
                if (userInfoBean == null) {
                    return;
                }

                final PopupWindow popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                View view1 = LayoutInflater.from(this).inflate(R.layout.dialog, null);
                view1.findViewById(R.id.other).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                final ImageView im = (ImageView) view1.findViewById(R.id.iv_qr_code);
                ImageView download = (ImageView) view1.findViewById(R.id.download);
                download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bitmap bitmap  =((BitmapDrawable) ((ImageView) im).getDrawable()).getBitmap();

                            saveBitmap(bitmap,"qr_code"+userInfoBean.getUser_id()+".jpg","利得商盟",PersonalDataActivity.this);

                        Toast.makeText(PersonalDataActivity.this, "保存完成", Toast.LENGTH_SHORT).show();
                    }
                });
                MyImageLoader.getInstance().loadImageReturnBitMap(im, String.valueOf(userInfoBean.getQr_img()), 0);
                popupWindow.setContentView(view1);
                popupWindow.showAtLocation(findViewById(R.id.parent), Gravity.CENTER, 0, 0);
                break;
        }
    }

    private static final String SAVE_PIC_PATH = Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";//保存到SD卡
    private static final String SAVE_REAL_PATH = SAVE_PIC_PATH + "/";//保存的确切位置

    public void saveFile(Bitmap bm, String fileName, String path, Context context) throws IOException {
        String subForder = SAVE_REAL_PATH + path;
        File foder = new File(subForder);
        if (!foder.exists()) {
            foder.mkdirs();
        }
        File myCaptureFile = new File(subForder, fileName);
        if (!myCaptureFile.exists()) {
            myCaptureFile.createNewFile();
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
 /*       Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(myCaptureFile);
        intent.setData(uri);
        context.sendBroadcast(intent);*/


        context. sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(subForder))));
    }


    public void saveBitmap(Bitmap bm, String fileName, String path, Context context) {

        String subForder = SAVE_REAL_PATH + path;
        // 首先保存图片
        File appDir = new File(subForder);
        if (!appDir.exists()) {
            appDir.mkdir();
        }


        File pictureFile = new File(appDir, fileName);
        if (pictureFile.exists()) {
            pictureFile.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(pictureFile);

            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
       //     Snackbar.make(findViewById(android.R.id.content), "保存图片成功"+pictureFile.getAbsolutePath(), Snackbar.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(getContentResolver(),
                    pictureFile .getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(pictureFile);intent.setData(uri);
        context . sendBroadcast(intent);//这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！，记得要传你更新的file哦
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new PersonalDataPresenter(this);
        }
        presenter.start();
        if (Config.User.STATUS) {

        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    public void callbackLoginoutSuccess() {
        showToast("注销成功");
        Config.User.STATUS = false;
        App.getApp().setLoginOut(true);
        JPushInterface.setAlias(App.getApp(), "", null);
        Prefs.with(App.getApp()).write(Config.User.UID, null);
        App.getApp().setUid(null);
        finish();
    }

    @Override
    public void callbackUserInfoSuccess(UserInfo.UserInfoBean userInfoBean) {
        if (userInfoBean != null) {
            this.userInfoBean = userInfoBean;
            user_phone.setText(userInfoBean.getUser_name());
            if (userInfoBean.getSex() != null && userInfoBean.getSex().equals("1")) {
                user_sex.setText("男");
            } else {
                user_sex.setText("女");
            }
            user_telephone.setText(userInfoBean.getMobile_phone());

            user_email.setText(userInfoBean.getEmail());

            Log.i("whfyy", "身份证" + userInfoBean.getID_card());

            user_idcard.setText(userInfoBean.getID_card());
            if (!userInfoBean.getUser_picture().equals("") || userInfoBean.getUser_picture() != null) {
                MyImageLoader.getInstance().returnBitMap(userInfoBean.getUser_picture().replace("\"", ""), user_img, 0);
            }
            Log.i(TAG, userInfoBean.getUser_picture().replace("\"", ""));
        }
    }

    @Override
    public void callbackUpdateLogoSuccess() {
        presenter.getUserInfo();
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode != 0) {
                    needPath = choosePictureDialog.getSavePath() + 1;
                    cropBitmap(Uri.fromFile(new File(choosePictureDialog.getSavePath())), Uri.fromFile(new File(needPath)));
                }
                break;
            case 2:
                if (resultCode != 0) {
                    needPath = choosePictureDialog.getNowPath();
                    cropBitmap(data.getData(), Uri.fromFile(new File(needPath)));
                }
                break;
            case 1234:
                if (resultCode != 0) {
                    picText = BitmapUtils.bitmapToBase64(BitmapUtils.compressBitmap(needPath));
                    choosePictureDialog.dismiss();
                    if (!picText.equals("")) {
                        presenter.updateLogo(picText);
                        break;
                    }
                }
        }
    }

    public void cropBitmap(Uri inUri, Uri outUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(inUri, "image/*");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, 1234);
    }
}
