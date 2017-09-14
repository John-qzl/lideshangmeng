package com.lidegou.lideshangmeng.mobile.ui.personal.about;

import android.view.View;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.HttpInterface;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.SetEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

public class AboutMyActivity extends BaseActivity {
    private TextView title;
    private TextView content;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_my;
    }

    @Override
    protected void init() {
        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
        findViewById(R.id.lin_back).setOnClickListener(this);

        getData();
    }

    @Override
    protected void viewClick(View view) {
        finish();
    }

    public void getData() {
        IMyDaoImpl iMyDao = new IMyDaoImpl();
        iMyDao.set(HttpInterface.AboutUs(), new ResponseCallback<SetEntity>() {
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
