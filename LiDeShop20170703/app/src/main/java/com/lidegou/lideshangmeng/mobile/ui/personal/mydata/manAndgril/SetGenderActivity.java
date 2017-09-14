package com.lidegou.lideshangmeng.mobile.ui.personal.mydata.manAndgril;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SetGenderActivity extends BaseActivity implements SetGenderContract.View {


    String temp = "1";
    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.male)
    RadioButton male;
    @InjectView(R.id.female)
    RadioButton female;
    @InjectView(R.id.sex)
    RadioGroup sex;
    @InjectView(R.id.iv_man01)
    ImageView ivMan01;
    @InjectView(R.id.tv_man01)
    TextView tvMan01;
    @InjectView(R.id.iv_woman01)
    ImageView ivWoman01;
    @InjectView(R.id.tv_woman01)
    TextView tvWoman01;
    @InjectView(R.id.activity_set_gender)
    LinearLayout activitySetGender;
    private SetGenderContract.Presenter presenter;
    private boolean isExit = true;
    private int sexint;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_gender;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        ivMan01.setOnClickListener(this);
        ivWoman01.setOnClickListener(this);
        sexint=getIntent().getIntExtra("sex",1);
        if (sexint==1){
            ivMan01.setImageResource(R.drawable.man01);
            tvMan01.setTextColor(Color.parseColor("#1296DB"));
            ivWoman01.setImageResource(R.drawable.womman02);
            tvWoman01.setTextColor(Color.parseColor("#bfbfbf"));
            temp = "1";
        }else {
            ivMan01.setImageResource(R.drawable.man02);
            tvMan01.setTextColor(Color.parseColor("#bfbfbf"));
            ivWoman01.setImageResource(R.drawable.womman01);
            tvWoman01.setTextColor(Color.parseColor("#D4237A"));
            temp = "2";
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new SetGenderPresenter(this);
        }
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.iv_man01:
                ivMan01.setImageResource(R.drawable.man01);
                tvMan01.setTextColor(Color.parseColor("#1296DB"));
                ivWoman01.setImageResource(R.drawable.womman02);
                tvWoman01.setTextColor(Color.parseColor("#bfbfbf"));
                temp = "1";
                presenter.setGender();
                break;
            case R.id.iv_woman01:
                ivMan01.setImageResource(R.drawable.man02);
                tvMan01.setTextColor(Color.parseColor("#bfbfbf"));
                ivWoman01.setImageResource(R.drawable.womman01);
                tvWoman01.setTextColor(Color.parseColor("#D4237A"));
                temp = "2";
                presenter.setGender();
                break;
        }
    }

    @Override
    public void callbackSetGenderSuccess(String msg) {
        showToast(msg);
    }

    @Override
    public String sex() {
        return temp + "";
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


}
