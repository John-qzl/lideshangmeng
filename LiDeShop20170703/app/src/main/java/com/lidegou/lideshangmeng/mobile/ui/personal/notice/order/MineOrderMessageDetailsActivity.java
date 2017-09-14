package com.lidegou.lideshangmeng.mobile.ui.personal.notice.order;

import android.view.View;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MessageOrderntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;

public class MineOrderMessageDetailsActivity extends BaseActivity implements MineOrderMessageDetailsContract.View {
    private MineOrderMessageDetailsContract.Presenter presenter;
    private MessageOrderntity.Data data;
    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_order_message_details;
    }

    @Override
    protected void init() {
        data = (MessageOrderntity.Data) getIntent().getSerializableExtra("data");
        if (data != null) {
            id = data.getId();
            TextView tv_content = (TextView) findViewById(R.id.tv_content);
            TextView tv_time = (TextView) findViewById(R.id.tv_time);
            tv_content.setText(data.getText());
            tv_time.setText(data.getTime());
        } else {
            id = getIntent().getStringExtra("id");
        }
        findViewById(R.id.lin_back).setOnClickListener(this);

        presenter = new MineOrderMessageDetailsPresenter(this);
        presenter.OrderMsgDes();
    }

    @Override
    protected void viewClick(View view) {
        finish();
    }

    @Override
    public String Id() {
        return id;
    }

    @Override
    public void OrderMsgDesuccess(MessageOrderntity.Data data) {
        TextView tv_content = (TextView) findViewById(R.id.tv_content);
        TextView tv_time = (TextView) findViewById(R.id.tv_time);
        tv_content.setText(data.getText());
        tv_time.setText(data.getTime());
    }


    @Override
    public void showError(String msg) {
        showToast(msg);
    }
}
