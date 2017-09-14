package com.lidegou.lideshangmeng.mobile.ui.register.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.CountDownTimer;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.City;
import com.lidegou.lideshangmeng.mobile.data.entity.County;
import com.lidegou.lideshangmeng.mobile.data.entity.Province;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.ui.register.RegisterContract;
import com.lidegou.lideshangmeng.mobile.ui.register.RegisterIdCordActivity;
import com.lidegou.lideshangmeng.mobile.ui.register.RegisterPresenter;
import com.lidegou.lideshangmeng.mobile.util.EncryptUtils;
import com.lidegou.lideshangmeng.mobile.util.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.TELEPHONY_SERVICE;

@SuppressLint("ValidFragment")

public class FragmentOne extends BaseFragment implements RegisterContract.View, View.OnFocusChangeListener {

    private LoadingDialog loadingDialog;
    private ProvinceCallBackCallBack provinceCallBack;
    private TextView tvCity;
    private Button btnSmsCode;
    private RegisterPresenter presenter;
    private EditText re_telephone, re_mobile_code, re_name, re_repassword, re_checkpassword, re_referees;
    private ImageView loginuser01, img_code, registername01, loginpass01, cloginpass01, im_referees, iv_address01;
    private Button btu_register;
    //倒计时
    private TimeCount timeCount;

    private Spinner province_spinner;
    private Spinner city_spinner;
    private Spinner county_spinner;

    private Integer strProvince, strCity, strCounty;


    ArrayAdapter<Province> provinceAdapter = null;  //省级适配器
    ArrayAdapter<City> cityAdapter = null;    //地级适配器
    ArrayAdapter<County> countyAdapter = null;    //县级适配器
    static int provincePosition = 0;
    static int cityPosition = 0;

    private List<Province> provinceList = new ArrayList<>();
    private List<City> cityList = new ArrayList<>();
    private List<County> countyList = new ArrayList<>();

    public FragmentOne(ProvinceCallBackCallBack provinceCallBack) {
        this.provinceCallBack = provinceCallBack;
    }

    public FragmentOne() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_one;
    }

    @Override
    protected void init() {
        loadingDialog = new LoadingDialog(getContext());
        tvCity = (TextView) getRootView().findViewById(R.id.tv_city);
        btnSmsCode = (Button) getRootView().findViewById(R.id.bu_send_code);
        re_telephone = (EditText) getRootView().findViewById(R.id.re_telephone);
        re_mobile_code = (EditText) getRootView().findViewById(R.id.re_mobile_code);
        re_name = (EditText) getRootView().findViewById(R.id.re_name);
        re_repassword = (EditText) getRootView().findViewById(R.id.re_repassword);
        re_checkpassword = (EditText) getRootView().findViewById(R.id.re_checkpassword);
        re_referees = (EditText) getRootView().findViewById(R.id.re_referees);
        btu_register = (Button) getRootView().findViewById(R.id.btu_register);

        loginuser01 = (ImageView) getRootView().findViewById(R.id.loginuser01);
        img_code = (ImageView) getRootView().findViewById(R.id.img_code);
        registername01 = (ImageView) getRootView().findViewById(R.id.registername01);
        loginpass01 = (ImageView) getRootView().findViewById(R.id.loginpass01);
        cloginpass01 = (ImageView) getRootView().findViewById(R.id.cloginpass01);
        im_referees = (ImageView) getRootView().findViewById(R.id.im_referees);
        iv_address01 = (ImageView) getRootView().findViewById(R.id.iv_address01);

        btnSmsCode.setOnClickListener(this);
        btu_register.setOnClickListener(this);
        tvCity.setOnClickListener(this);
        re_telephone.setOnFocusChangeListener(this);
        re_mobile_code.setOnFocusChangeListener(this);
        re_name.setOnFocusChangeListener(this);
        re_repassword.setOnFocusChangeListener(this);
        re_checkpassword.setOnFocusChangeListener(this);
        re_referees.setOnFocusChangeListener(this);
        re_telephone.setFocusable(true);
        re_telephone.setFocusableInTouchMode(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new RegisterPresenter(this);
        }
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.tv_city:
                provinceCallBack.provinceCallBack(1);
                if (!tvCity.getText().toString().equals("")) {
                    iv_address01.setImageResource(R.drawable.address02);
                } else {
                    iv_address01.setImageResource(R.drawable.address01);
                }
                break;
            case R.id.bu_send_code:
                presenter.isRegister();
                break;
            case R.id.btu_register:
                presenter.register();
                break;
        }
    }

    @Override
    protected void lazyLoad() {

    }


    @Override
    public String getData() {
        String data = EncryptUtils.AES_Encrypt(re_telephone.getText().toString().trim());
        return data;
    }

    @Override
    public String getTelephone() {
        return re_telephone.getText().toString().trim();
    }

    @Override
    public String getSmsCode() {
        return re_mobile_code.getText().toString().trim();
    }

    @Override
    public String getName() {
        return re_name.getText().toString();
    }

    @Override
    public String getPassword() {
        return re_repassword.getText().toString();
    }

    @Override
    public String getCheckPassword() {
        return re_checkpassword.getText().toString();
    }

    @Override
    public String getReferees() {
        return re_referees.getText().toString();
    }

    @Override
    public String getProvince() {
        return strProvince + "";
    }

    @Override
    public String getCity() {
        return strCity + "";
    }

    @Override
    public String getCounty() {
        return strCounty + "";
    }

    @Override
    public Integer region_id() {
        return provincePosition;
    }

    @Override
    public Integer region_idcity() {
        return cityPosition;
    }

    @Override
    public String getVersion() {
        PackageManager pm = getActivity().getPackageManager();
        final PackageInfo pi;
        try {
            pi = pm.getPackageInfo(getActivity().getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            return pi.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void callbaclIsRegister(boolean isRegister) {
        if (isRegister) {
            showToast("该手机号已经注册");
        } else {
            presenter.smsCode();
        }
    }

    @Override
    public void callbackSmsCodeSuccess() {
        if (timeCount == null) {
            timeCount = new TimeCount(60000, 1000);
        }
        timeCount.start();
        btnSmsCode.setClickable(false);
    }

    @Override
    public void callbackRegisterSuccess() {
        showToast("注册成功");
        Intent intent  = new Intent(getActivity(), RegisterIdCordActivity.class);
        startActivity(intent);
        showToast("已注册成功,请进行实名认证");
        getActivity().finish();

    }

    @Override
    public void callbackProvinceSuccess(List<Province> provinceList) {
        this.provinceList = provinceList;
    }

    @Override
    public void callbackCitySuccess(List<City> cityList) {
        this.cityList = cityList;
    }

    @Override
    public void callbackCountySuccess(List<County> countyList) {
        this.countyList = countyList;
    }

    @Override
    public String getMark() {
        TelephonyManager TelephonyMgr = (TelephonyManager) getActivity().getSystemService(TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        return szImei;
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.re_telephone:
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    im_referees.setImageResource(R.drawable.registerreferrals01);
                    loginpass01.setImageResource(R.drawable.loginpass01);
                    cloginpass01.setImageResource(R.drawable.loginpass01);
                    loginuser01.setImageResource(R.drawable.loginuser02);
                    img_code.setImageResource(R.drawable.registerverificationcode01);
                    registername01.setImageResource(R.drawable.registername01);
                } else {
                    // 此处为失去焦点时的处理内容
                    loginuser01.setImageResource(R.drawable.loginuser01);
                }
                break;
            case R.id.re_mobile_code:
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    im_referees.setImageResource(R.drawable.registerreferrals01);
                    loginpass01.setImageResource(R.drawable.loginpass01);
                    cloginpass01.setImageResource(R.drawable.loginpass01);
                    loginuser01.setImageResource(R.drawable.loginuser01);
                    img_code.setImageResource(R.drawable.registerverificationcode02);
                    registername01.setImageResource(R.drawable.registername01);
                } else {
                    // 此处为失去焦点时的处理内容
                    img_code.setImageResource(R.drawable.registerverificationcode01);
                }
                break;
            case R.id.re_name:
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    im_referees.setImageResource(R.drawable.registerreferrals01);
                    loginpass01.setImageResource(R.drawable.loginpass01);
                    cloginpass01.setImageResource(R.drawable.loginpass01);
                    loginuser01.setImageResource(R.drawable.loginuser01);
                    img_code.setImageResource(R.drawable.registerverificationcode01);
                    registername01.setImageResource(R.drawable.registername02);
                } else {
                    // 此处为失去焦点时的处理内容
                    registername01.setImageResource(R.drawable.registername01);
                }
                break;
            case R.id.re_repassword:
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    im_referees.setImageResource(R.drawable.registerreferrals01);
                    loginpass01.setImageResource(R.drawable.loginpass02);
                    cloginpass01.setImageResource(R.drawable.loginpass01);
                    loginuser01.setImageResource(R.drawable.loginuser01);
                    img_code.setImageResource(R.drawable.registerverificationcode01);
                    registername01.setImageResource(R.drawable.registername01);
                } else {
                    // 此处为失去焦点时的处理内容
                    loginpass01.setImageResource(R.drawable.loginpass01);
                }
                break;
            case R.id.re_checkpassword:
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    im_referees.setImageResource(R.drawable.registerreferrals01);
                    loginpass01.setImageResource(R.drawable.loginpass01);
                    cloginpass01.setImageResource(R.drawable.loginpass02);
                    loginuser01.setImageResource(R.drawable.loginuser01);
                    img_code.setImageResource(R.drawable.registerverificationcode01);
                    registername01.setImageResource(R.drawable.registername01);
                } else {
                    // 此处为失去焦点时的处理内容
                    cloginpass01.setImageResource(R.drawable.loginpass01);
                }
                break;
            case R.id.re_referees:
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    im_referees.setImageResource(R.drawable.registerreferrals02);
                    loginpass01.setImageResource(R.drawable.loginpass01);
                    cloginpass01.setImageResource(R.drawable.loginpass01);
                    loginuser01.setImageResource(R.drawable.loginuser01);
                    img_code.setImageResource(R.drawable.registerverificationcode01);
                    registername01.setImageResource(R.drawable.registername01);
                } else {
                    // 此处为失去焦点时的处理内容
                    im_referees.setImageResource(R.drawable.registerreferrals01);
                }
                break;

        }
    }

    public void setAddress(int provinceid, int cityid, int areaid, String province, String city, String area) {
        if (province != null && city != null && area != null) {
            tvCity.setText(province + city + area);
            this.strProvince = provinceid;
            this.strCity = cityid;
            this.strCounty = areaid;
        }
    }

    /**
     * 倒计时工具类
     */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btnSmsCode.setText((millisUntilFinished / 1000) + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            btnSmsCode.setClickable(true);
            btnSmsCode.setText("验证码");
        }
    }


    public interface ProvinceCallBackCallBack {
        void provinceCallBack(int data);
    }
}
