package com.lidegou.lideshangmeng.mobile.ui.personal.contect;

import android.view.View;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.HttpInterface;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.SetEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

public class ContectActivity extends BaseActivity {
    public static String TYPE = "type";
    public static String NAME = "name";
    public static String TYPE_CONTECT = "Contect";
    public static String TYPE_SERVICE = "Service";
    public static String TYPE_DISCLAIMER = "Disclaimer";
    public static String TYPE_INTRODUCTION = "AppIntroduction";

    private String type;
    private String name;
    private TextView tvName;
    private TextView title;
    private TextView content;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contect;
    }

    @Override
    protected void init() {
        type = getIntent().getStringExtra(TYPE);
        name = getIntent().getStringExtra(NAME);

        tvName = (TextView) findViewById(R.id.tv_name);
        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
        findViewById(R.id.lin_back).setOnClickListener(this);
        tvName.setText(name);
        getData();
    }

    @Override
    protected void viewClick(View view) {
        finish();
    }

    public void getData() {
        IMyDaoImpl iMyDao = new IMyDaoImpl();
        String url = "";
        if (type.equals(TYPE_CONTECT)) {
            url = HttpInterface.Contect();
        } else if (type.equals(TYPE_SERVICE)) {
            url = HttpInterface.Service();
        } else if (type.equals(TYPE_DISCLAIMER)) {
            url = HttpInterface.Disclaimer();
        } else if (type.equals(TYPE_INTRODUCTION)) {
            url = HttpInterface.AppIntroduction();
        }
        iMyDao.set(url, new ResponseCallback<SetEntity>() {
            @Override
            public void onSuccess(SetEntity data) {
                title.setText(data.getTitle());
                content.setText(data.getContent());
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    showToast(msg);
                } else {
                    showToast("网络异常");
                }
            }
        });
    }
}
