package com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsIn;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInContract;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInFourActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInPresenter;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.MerchantsInTwoActivity;
import com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.loac.ShopLoactionActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.util.BitmapUtils;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;
import com.lidegou.lideshangmeng.mobile.util.dialog.Choose;

import java.io.File;

@SuppressLint("ValidFragment")
public class MerchantsInThreeFragment extends BaseFragment implements MerchantsInContract.ViewThree {

    EditText edCompanyName;
    EditText edRegisteredNumber;
    EditText edRepresentative;
    EditText edScope;
    EditText edAddress;
    EditText edShopPhone;
    Button btnUp;
    Button btnNext;
    RelativeLayout rlLocation;
    TextView tvCity;
    RelativeLayout rePic;
    RelativeLayout reImagePic;
    ImageView ivImagePic;
    TextView isLoc;

    private double myLongitude;
    private double mylatitude;

    private MerchantsInContract.Presenter presenter;
    private ProvinceCallBackCallBack provinceCallBack;

    private Integer strProvince, strCity, strCounty;
    private Choose choosePictureDialog;
    private String license_fileImg;

    private String province;

    private String needPath = null;

    public MerchantsInThreeFragment(ProvinceCallBackCallBack provinceCallBack) {
        this.provinceCallBack = provinceCallBack;
    }

    public MerchantsInThreeFragment() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_merchants_in_three;
    }

    @Override
    protected void init() {
        choosePictureDialog = new Choose(getContext());
        edCompanyName = (EditText) getRootView().findViewById(R.id.ed_company_name);
        edRegisteredNumber = (EditText) getRootView().findViewById(R.id.ed_registered_number);
        edRepresentative = (EditText) getRootView().findViewById(R.id.ed_representative);
        edScope = (EditText) getRootView().findViewById(R.id.ed_scope);
        edAddress = (EditText) getRootView().findViewById(R.id.ed_address);
        edShopPhone = (EditText) getRootView().findViewById(R.id.ed_shop_phone);
        btnUp = (Button) getRootView().findViewById(R.id.btn_up);
        btnNext = (Button) getRootView().findViewById(R.id.btn_next);
        rlLocation = (RelativeLayout) getRootView().findViewById(R.id.rl_location);
        tvCity = (TextView) getRootView().findViewById(R.id.tv_city);
        rePic = (RelativeLayout) getRootView().findViewById(R.id.re_pic);
        reImagePic = (RelativeLayout) getRootView().findViewById(R.id.re_imagePic);
        ivImagePic = (ImageView) getRootView().findViewById(R.id.iv_imagePic);
        isLoc = (TextView) getRootView().findViewById(R.id.is_loc);
        btnUp.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        rlLocation.setOnClickListener(this);
        rePic.setOnClickListener(this);
        tvCity.setOnClickListener(this);

        presenter = new MerchantsInPresenter(this);
        presenter.MerchantsInThree();
    }


    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_up:
                startActivity(new Intent(getActivity(), MerchantsInTwoActivity.class));
                break;
            case R.id.btn_next:
                presenter.MerchantsInThreedone(edCompanyName.getText().toString(), edRegisteredNumber.getText().toString(), edRepresentative.getText().toString(), edScope.getText().toString(), license_fileImg + "", edAddress.getText().toString(), edShopPhone.getText().toString(), strProvince + "", strCity + "", strCounty + "", myLongitude + "", mylatitude + "");
                break;
            case R.id.rl_location:
                if (tvCity.getText().toString().length() <= 0) {
                    showToast("请选择地区");
                    return;
                }
                if (edAddress.getText().toString().length() <= 0) {
                    showToast("请输入详细地址");
                    return;
                }
                Intent intent = new Intent(getActivity(), ShopLoactionActivity.class);
                intent.putExtra("city", province);
                intent.putExtra("address", tvCity.getText().toString() + edAddress.getText().toString());
                startActivityForResult(intent, 100);
                break;
            case R.id.re_pic:
                choosePictureDialog.show();
                break;
            case R.id.tv_city:
                provinceCallBack.provinceCallBack(1);
                break;
        }
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void callbackMerchantsInThreeSuccess(MerchantsIn.MerchantsInCompany merchantsInCompany) {
        if (merchantsInCompany != null && !merchantsInCompany.getCompanyName().equals("")) {
            edCompanyName.setText(merchantsInCompany.getCompanyName());
            edRegisteredNumber.setText(merchantsInCompany.getBusiness_license_id());
            edRepresentative.setText(merchantsInCompany.getLegal_person());
            edScope.setText(merchantsInCompany.getBusines_scope());
            MyImageLoader.getInstance().loaderImage(merchantsInCompany.getLicense_fileImg(), ivImagePic);
            reImagePic.setVisibility(View.VISIBLE);
            tvCity.setText(merchantsInCompany.getProvince_name() + merchantsInCompany.getCity_name() + merchantsInCompany.getDistrict_name());
            province = merchantsInCompany.getProvince_name();
            edAddress.setText(merchantsInCompany.getCompany_adress());
            edShopPhone.setText(merchantsInCompany.getCompany_contactTel());
            strProvince = Integer.parseInt(merchantsInCompany.getProvince());
            strCity = Integer.parseInt(merchantsInCompany.getCity());
            strCounty = Integer.parseInt(merchantsInCompany.getDistrict());
            mylatitude = Double.parseDouble(merchantsInCompany.getLatitude());
            myLongitude = Double.parseDouble(merchantsInCompany.getLongitude());
            isLoc.setText("已设置");
            license_fileImg = merchantsInCompany.getLicense_fileImg();
        }
    }

    @Override
    public void callbackMerchantsInThreedoneSuccess(String msg) {
        showToast(msg);
        startActivity(new Intent(getActivity(), MerchantsInFourActivity.class));
    }

    public void setAddress(int provinceid, int cityid, int areaid, String province, String city, String area) {
        if (province != null && city != null && area != null) {
            tvCity.setText(province + city + area);
            this.province = province;
            this.strProvince = provinceid;
            this.strCity = cityid;
            this.strCounty = areaid;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 100) {
            if (data != null) {
                double latitude = data.getDoubleExtra("latitude", -1);
                double longitude = data.getDoubleExtra("longitude", -1);
                if (latitude != -1 && longitude != -1) {
                    mylatitude = latitude;
                    myLongitude = longitude;
                    showToast("设置成功");
                    isLoc.setText("已设置");
                }
            }
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
                    Bitmap bitmap1 = BitmapUtils.compressBitmap(needPath);
                    license_fileImg = BitmapUtils.bitmapToBase64(bitmap1);
                    reImagePic.setVisibility(View.VISIBLE);
                    ivImagePic.setImageBitmap(bitmap1);
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
        getActivity().startActivityForResult(intent, 1234);
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    public interface ProvinceCallBackCallBack {
        void provinceCallBack(int data);
    }
}
