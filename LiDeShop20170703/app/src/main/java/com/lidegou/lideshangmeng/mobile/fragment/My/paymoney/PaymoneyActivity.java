package com.lidegou.lideshangmeng.mobile.fragment.My.paymoney;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.allinpay.appayassistex.APPayAssistEx;
import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.PayText;
import com.lidegou.lideshangmeng.mobile.pay.tonly.PaaCreator;
import com.lidegou.lideshangmeng.mobile.pay.treasure.PayTreasure;
import com.lidegou.lideshangmeng.mobile.pay.wechat.PayWeChat;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.util.dialog.LoadingDialog;
import com.lidegou.lideshangmeng.mobile.util.dialog.select.SelectDialog;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Y on 2017/5/18.
 */

public class PaymoneyActivity extends BaseActivity {
    private boolean  flag=true;
    double lo_last;
    double lo_yan;
    private double last;
    private TextView tv,yuan,lastyuan,number;
    private Spinner spinner;
    private Button btn;
    private LinearLayout btn_backs;
    private CheckBox checkBox;
    private LinearLayout fl;
    private IMyDaoImpl iMyDao;
    private LoadingDialog loadingDialog;
    private SelectDialog payTypeDialog;
    DecimalFormat    df   = new DecimalFormat("######0.00");
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_paymoney;
    }
    String str;
    @Override
    protected void init() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        loadingDialog = LoadingDialog.getInstance(this);
        loadingDialog.show("请稍后");
        str = getIntent().getStringExtra("id");


        number = (TextView) findViewById(R.id.number);
        tv = (TextView) findViewById(R.id.tv);
        yuan = (TextView) findViewById(R.id.yuan);
        lastyuan = (TextView) findViewById(R.id.lastyuan);
        spinner = (Spinner) findViewById(R.id.spinner);
        btn = (Button) findViewById(R.id.btn_pay);
        checkBox = (CheckBox) findViewById(R.id.cvv);
        fl = (LinearLayout) findViewById(R.id.fl);
        btn_backs = (LinearLayout) findViewById(R.id.btn_backs);
        initOnClickListener();
        iMyDao = new IMyDaoImpl();
        iMyDao.getBayType(App.getApp().getUid(),str, new ResponseCallback<String>() {
            @Override
            public void onSuccess(String str) {

            }

            @Override
            public void onFailure(int code, String msg) {

            }
        },this);

    }
    int payTyte=-1;
    private void initOnClickListener() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                payTyte=position-1;
                if(position==0)
                {
                    fl.setVisibility(View.INVISIBLE);
                    tv.setVisibility(View.VISIBLE);
                }
                else if(position==1){
                    fl.setVisibility(View.INVISIBLE);
                    tv.setVisibility(View.INVISIBLE);
                }
                else{
                    fl.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.INVISIBLE);
                }
                if(checkBox.isChecked()){
                    number.setText("是否使用余额支付");
                    yuan.setText(df.format(lo_yan)+"");
                    checkBox.setChecked(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    flag=false;
                    double yan= toDouble(yuan.getText().toString());
                    lo_last = last;
                    lo_yan = yan;
                    if(yan>last){
                        yan=yan-last;
                        number.setText("余额支付"+df.format(last)+"元");
                    }else{
                        last = last-yan;
                        double d = yan ;
                        yan=0.00;
                        number.setText("余额支付"+df.format(d)+"元");
                    }
                    yuan.setText(df.format(yan)+"");
                    //  lastyuan.setText("("+df.format(last)+"元)");
                    flag=true;
                }else{
                    number.setText("是否使用余额支付");
                    yuan.setText(df.format(lo_yan)+"");


                }
            }
        });


        yuan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(flag) {

                    number.setText("是否使用余额支付");
                    last = lo_last;
                   // lastyuan.setText("(" + last + "元)");
                    checkBox.setChecked(false);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn.setOnClickListener(this);
        btn_backs.setOnClickListener(this);
    }


    public double  toDouble(String str){
        if(str==null||"".equals(str)){
            return 0;
        }
        return Double.parseDouble(str);
    }
    @Override
    protected void viewClick(View view) {
        switch (view.getId()){
            case R.id.btn_pay:
                if(payTyte==-1){
                    showToast("请选择支付方式");
                    return ;
                }
                int stat =checkBox.isChecked()?1:0;
                Log.i("whfyy","支付方式"+list.get(payTyte).getPay_name());
                double t =0;
                if(stat==0){
                    t=toDouble(yuan.getText().toString());
                }else{
                    t=lo_yan;
                }
                if(t<=0){
                    Toast.makeText(this,"请输入金额",Toast.LENGTH_SHORT).show();
                    return;
                }
                loadingDialog.show("支付中...");
                btn.setClickable(false);
                loadingDialog = LoadingDialog.getInstance(this);

                double tt=toDouble(yuan.getText().toString());
                if(stat==1&&tt==0){
                    Log.i("whfyy","是否余额"+stat+"金额"+t);
                    t=toDouble(df.format(t));
                    iMyDao.gettoPay(stat + "", t + "", list.get(0).getPay_id(), App.getApp().getUid(), str, new ResponseCallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                        }
                        @Override
                        public void onFailure(int code, String msg) {
                        }
                    },this);
                }else{

                    t=toDouble(df.format(t));
                    Log.i("whfyy","回传参数"+"usemoney="+stat+"  jine="+t+"  type="+list.get(payTyte).getPay_id()+" uid="+ App.getApp().getUid()+"  shop_uid="+str);

                    iMyDao.gettoPay(stat + "", t + "", list.get(payTyte).getPay_id(), App.getApp().getUid(), str, new ResponseCallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                        }
                        @Override
                        public void onFailure(int code, String msg) {
                        }
                    },this);
                }

            break;
            case R.id.btn_backs:
                finish();
                break;
        }
    }
    List<PayText.PaymentListBean> list;
    public  void seSeccess(final PayText payText){
        list  =  payText.getPayment_list();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> lists = new ArrayList<>();
                lists.add("请选择支付方式");
                //适配器
                for (int i =0;i<list.size();i++){
                    lists.add(list.get(i).getPay_name());
                }
                ArrayAdapter arr_adapter= new ArrayAdapter<String>(PaymoneyActivity.this, android.R.layout.simple_spinner_item, lists);
                //设置样式
                arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //加载适配器
                spinner.setAdapter(arr_adapter);
                last= toDouble(payText.getUser_money());
                lastyuan.setText("(可用余额:"+last+"元)");
                lo_last=last;
                loadingDialog.dismiss();
            }
        });
    };

    public  void onFailure(int code, String errorMsg){

    }
    public  void onSuccess(OtherPayEntity entity){
             colesRun();
            if (list.get(payTyte).getPay_id().equals("12")) {
                OtherPayEntity.PaaCreatorEntity paaCreatorEntity = entity.getPaaCreatorEntity();
                if (paaCreatorEntity != null) {
                    JSONObject payData = PaaCreator.randomPaa(paaCreatorEntity);
                    APPayAssistEx.startPay(this, payData.toString(), APPayAssistEx.MODE_PRODUCT);
                }
            }
            if (list.get(payTyte).getPay_id().equals("13")) {
                OtherPayEntity.WeChatEntity weChatEntity = entity.getWeChatEntity();
                if (weChatEntity != null) {
                    PayWeChat payWeChat = new PayWeChat(this, App.getApp(), new PayHandler());
                    payWeChat.payBack(weChatEntity);
                }
            }
            if (list.get(payTyte).getPay_id().equals("14")) {
                OtherPayEntity.TreasureEntity treasureEntity = entity.getTreasureEntity();
                if (treasureEntity != null) {
                    PayTreasure payTreasure = new PayTreasure(this, new PayHandler());
                    payTreasure.payBack(treasureEntity.getPayInfo());
                }
            }
        btn.setClickable(true);
    };

    private boolean isSuccess = false;
    public class PayHandler extends Handler {
        @Override
        public void handleMessage(final Message msg) {


        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (APPayAssistEx.REQUESTCODE == requestCode) {
            if (null != data) {
                String payRes = null;
                try {
                    JSONObject resultJson = new JSONObject(data.getExtras().getString("result"));
                    payRes = resultJson.getString(APPayAssistEx.KEY_PAY_RES);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (null != payRes && payRes.equals(APPayAssistEx.RES_SUCCESS)) {
                    isSuccess = true;
                    showToast("支付成功!");
                    finish();
                } else {
                    showToast("支付失败");
                }
            }
        }
    }
    public  void onSuccessLoast(){
        showToast("支付成功!");
        finish();
    }


    public  void onErrorLoast(String msg){
        colesRun();
        showToast("支付失败:"+msg);

    }

    public void colesRun(){
        btn.setClickable(true);
        loadingDialog.dismiss();

    }
}
