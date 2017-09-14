package com.lidegou.lideshangmeng.mobile.ui.register;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.Config;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IUserInfoDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IUserDaoImpl;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.util.IdCardUtil;
import com.lidegou.lideshangmeng.mobile.util.Prefs;



/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class RegisterIdCordActivity extends BaseActivity {

    private EditText et_name;
    private EditText et_idcard;
    private EditText et_r_idcard;
    private Button btn_login;


    @Override
    protected int getLayoutId() {
       return R.layout.activity_register_id;
    }

    @Override
    protected void init() {

        et_name = (EditText) findViewById(R.id.ed_name);
        et_idcard = (EditText) findViewById(R.id.ed_idcard);
        et_r_idcard = (EditText) findViewById(R.id.ed_ridcard);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        findViewById(R.id.lin_back).setOnClickListener(this);
        this.iUserInfoDao = new IUserDaoImpl();

    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String name = et_name.getText().toString();
                String idcard = et_idcard.getText().toString().trim();
                String r_idcard = et_r_idcard.getText().toString().trim();

                if (name == null || "".equals(name)) {
                    showToast("请填写真实姓名");
                    return;
                }
                if (idcard == null || "".equals(idcard)) {
                    showToast("请填写身份证信息");
                    return;
                }
                if (r_idcard == null || "".equals(r_idcard)) {
                    showToast("请再次填写身份证信息");
                    return;
                }
                if (!r_idcard.equals(idcard)) {
                    showToast("两次输入身份证信息必须保持一致");
                    return;
                }
                IdCardUtil id = new IdCardUtil();

                if(idcard.length()==15||idcard.length()==18){

                    iUserInfoDao.RegisterIdCard(App.getApp().getUid(),name,idcard,responseCallback);

                }
                else{
                    showToast("对不起,身份证信息不合法");
                    return;
                }
                break;
            case R.id.lin_back:
                finish();
                break;

        }
    }
    boolean flag=true;
    ResponseCallback responseCallback = new ResponseCallback() {
        @Override
        public void onSuccess(Object data) {

                boolean datas = (boolean) data;
            if(datas){
                showToast("提交成功");
                flag=false;
                finish();
            }else{
                showToast("请输入正确的身份证号码");
            }
        }

        @Override
        public void onFailure(int code, String msg) {
            Log.i("whfyy","msg:"+msg);
        }
    };
    private IUserInfoDao iUserInfoDao;

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

    public void loginout(){

        getApp().setUid("");
        Config.User.STATUS = false;
        Prefs.with(getContext()).writeBoolean(Config.User.AUTOLOGIN, false);
        iUserInfoDao.loginout(getApp().getUid(), new ResponseCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                showToast("请重新登录");
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {

                }
            }
        });
    }

    @Override
    protected void onDestroy() {

        if(flag){
            loginout();
        }

        super.onDestroy();

    }
}
