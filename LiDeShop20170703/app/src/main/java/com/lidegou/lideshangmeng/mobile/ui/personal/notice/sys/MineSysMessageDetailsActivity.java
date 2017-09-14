package com.lidegou.lideshangmeng.mobile.ui.personal.notice.sys;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MessageSysEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

public class MineSysMessageDetailsActivity extends BaseActivity implements MineSysMessageDetailsContract.View {
    private MineSysMessageDetailsContract.Presenter presenter;
    private String id;
    private MessageSysEntity.Data data;

    private TextView message_detail_title;
    private TextView message_detail_time;
    private ImageView message_detail_image;
    private TextView message_detail_content;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_sys_message_details;
    }

    @Override
    protected void init() {
        data = (MessageSysEntity.Data) getIntent().getSerializableExtra("data");

        message_detail_title = (TextView) findViewById(R.id.message_detail_title);
        message_detail_time = (TextView) findViewById(R.id.message_detail_time);
        message_detail_image = (ImageView) findViewById(R.id.message_detail_image);
        message_detail_content = (TextView) findViewById(R.id.message_detail_content);
        findViewById(R.id.lin_back).setOnClickListener(this);

        if (data != null) {
            id = data.getId();
            message_detail_title.setText(data.getTitle());
            message_detail_time.setText(data.getTime());
            if (data.getPic() != null && !data.getPic().equals("")) {
                MyImageLoader.getInstance().loaderImage(data.getPic(), message_detail_image);
            } else {
                message_detail_image.setVisibility(View.GONE);
            }
            message_detail_content.setText(data.getContent());
        } else {
            id = getIntent().getStringExtra("id");
        }
        presenter = new MineSysMessageDetailsPresenter(this);
        presenter.sysMsgDes();
    }

    @Override
    protected void viewClick(View view) {
        finish();
    }

    @Override
    public void sysMsgDesSuccess(MessageSysEntity.Data data) {
        message_detail_title.setText(data.getTitle());
        message_detail_time.setText(data.getTime());
        if (data.getPic() != null && !data.getPic().equals("")) {
            MyImageLoader.getInstance().loaderImage(data.getPic(), message_detail_image);
        } else {
            message_detail_image.setVisibility(View.GONE);
        }
        message_detail_content.setText(data.getContent());
    }

    @Override
    public String Id() {
        return id;
    }


    @Override
    public void showError(String msg) {
        showToast(msg);
    }
}
