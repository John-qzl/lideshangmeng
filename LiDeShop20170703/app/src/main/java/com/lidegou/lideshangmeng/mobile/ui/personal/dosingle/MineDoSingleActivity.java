package com.lidegou.lideshangmeng.mobile.ui.personal.dosingle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;
import com.lidegou.lideshangmeng.mobile.data.entity.Single;
import com.lidegou.lideshangmeng.mobile.scancodedemo.MipcaActivityCapture;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.ui.personal.dosingle.paySingle.SinlePayActivity;
import com.lidegou.lideshangmeng.mobile.util.BitmapUtils;
import com.lidegou.lideshangmeng.mobile.util.dialog.Choose;

import java.io.File;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Y on 2016/12/3.
 * 我的做单
 */
public class MineDoSingleActivity extends BaseActivity implements MineSingleContract.View {
    public static final int CODE = 10000;
    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.ed_phone)
    EditText edPhone;
    @InjectView(R.id.img_code)
    TextView imgCode;
    @InjectView(R.id.ed_price)
    EditText edPrice;
    @InjectView(R.id.ed_name)
    EditText edName;
    @InjectView(R.id.ed_number)
    EditText edNumber;
    @InjectView(R.id.ed_serial_number)
    EditText edSerialNumber;
    @InjectView(R.id.ed_note)
    EditText edNote;
    @InjectView(R.id.rl_credentials)
    RelativeLayout rlCredentials;
    @InjectView(R.id.re_imagePic)
    RelativeLayout reImagePic;
    @InjectView(R.id.iv_imagePic)
    ImageView ivImagePic;
    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.iv_no)
    ImageView ivNo;
    @InjectView(R.id.iv_yes)
    ImageView ivYes;
    @InjectView(R.id.btn_single_submit)
    Button btnSingleSubmit;
    @InjectView(R.id.tv_single_warm)
    TextView tvSingleWarm;
    @InjectView(R.id.scrollView)
    ScrollView scrollView;
    @InjectView(R.id.tv_xingming)
    TextView tvXingming;
    @InjectView(R.id.lin_Name)
    LinearLayout linName;
    @InjectView(R.id.view1)
    View view1;
    @InjectView(R.id.save_pic)
    ImageView savePic;
    @InjectView(R.id.pictures)
    ImageView pictures;
    @InjectView(R.id.tv_lastmoney)
    TextView tv_lastmoney;
    private MineSingleContract.Presenter presenter;
    private boolean yesis = true;
    private String isMe = "1";
    private double money_limit;
    public static final String OUT_PUT = Config.ROOT.URL + "/img/portrait.jpg";
    private Choose choosePictureDialog;
    private String picType = "";
    private String picText = "";

    private String needPath = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_do_single;
    }

    protected void init() {


        choosePictureDialog = new Choose(this);
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        btnSingleSubmit.setOnClickListener(this);
        ivYes.setOnClickListener(this);
        ivNo.setOnClickListener(this);
        rlCredentials.setOnClickListener(this);
        pictures.setOnClickListener(this);
        if (yesis) {
            ivYes.setImageResource(R.drawable.yes);
        }

        edPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged:" + s + "-" + start + "-" + count + "-" + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged:" + s + "-" + "-" + start + "-" + before + "-" + count);
                if (!edPrice.getText().toString().equals("")) {
                    if (Double.parseDouble(edPrice.getText().toString()) >= money_limit) {
                        rlCredentials.setVisibility(View.VISIBLE);
                    } else {
                        rlCredentials.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable edt) {
                String temp = edt.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - posDot - 1 > 2) {
                    edt.delete(posDot + 3, posDot + 4);
                }
            }
        });

        edPhone.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                } else {
                    if (!edPhone.getText().toString().equals("")) {
                        presenter.getMcheckuser();
                        // 此处为失去焦点时的处理内容
                    }
                }
            }
        });

        presenter = new MineSinglePresenter(this);
        presenter.start();


    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.iv_yes:
                ivYes.setImageResource(R.drawable.yes);
                ivNo.setImageResource(R.drawable.no2);
                isMe = "1";
                break;
            case R.id.iv_no:
                ivNo.setImageResource(R.drawable.no);
                ivYes.setImageResource(R.drawable.yes2);
                isMe = "0";
                break;
            case R.id.rl_credentials:
                choosePictureDialog.show();
                break;
            case R.id.pictures:
                Intent intent = new Intent(this, MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, CODE);
                break;
            case R.id.btn_single_submit:
                btnSingleSubmit.setClickable(false);
                presenter.AddSingle();
                break;
        }
    }


    @Override
    public String getPhone() {
        return edPhone.getText().toString().trim();
    }

    @Override
    public String getPrice() {
        return edPrice.getText().toString();
    }

    @Override
    public String getGoodsname() {
        return edName.getText().toString();
    }

    @Override
    public String getGoodsnum() {
        String string = edNumber.getText().toString();
        if( string==null||string.equals("")){
            return "";
        }
        return  string;
    }



    @Override
    public String getGoodsinfo() {
        return edNote.getText().toString();
    }

    @Override
    public String getQingdanbianhao() {
        return edSerialNumber.getText().toString();
    }

    @Override
    public String getIs_me() {
        return isMe;
    }

    @Override
    public String getZuodan_img() {
        return picText;
    }

    @Override
    public String getPicType() {
        return picType;
    }

    @Override
    public void callbackCeilingMoneySuccess(Single.Data.CeilingMoney ceilingMoney) {
        money_limit = Double.parseDouble(ceilingMoney.getMoney_limit());
        tvSingleWarm.setText(ceilingMoney.getText());
    }

    @Override
    public void callbackMcheckuserStringSuccess(String name) {
        if (name != null) {
            linName.setVisibility(View.VISIBLE);
            view1.setVisibility(View.VISIBLE);
            tvXingming.setText(name);
        } else {
            linName.setVisibility(View.GONE);
        }
    }

    @Override
    public void callbackMcheckuserListSuccess(ArrayList<String> list) {
        if (list != null) {
            linName.setVisibility(View.VISIBLE);
            view1.setVisibility(View.VISIBLE);
            edPhone.setText(list.get(0));
            tvXingming.setText(list.get(1));
        } else {
            linName.setVisibility(View.GONE);
        }
    }

    @Override
    public void callbackAddSingleSuccess(String id) {
        showToast("做单成功");
        Intent intent = new Intent(this, SinlePayActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
        finish();
    }

    @Override
    public void callbackMoneyManageSuccess(MoneyManage moneyManage) {
        tv_lastmoney.setText(moneyManage.getSurplus_amount()+"元");
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
        btnSingleSubmit.setClickable(true);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE) {
            if (resultCode != 0) {
                if (data != null) {
                    String result = data.getStringExtra("result");
                    if (result != null) {
                        presenter.getMcheckuser(result);
                    }
                }
            }
            return;
        }

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
                    Bitmap bitmap1 = BitmapFactory.decodeFile(needPath);
                    reImagePic.setVisibility(View.VISIBLE);
                    ivImagePic.setImageBitmap(bitmap1);
                    choosePictureDialog.dismiss();
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
