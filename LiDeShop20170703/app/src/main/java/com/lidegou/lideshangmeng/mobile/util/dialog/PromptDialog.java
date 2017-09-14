package com.lidegou.lideshangmeng.mobile.util.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;


/**
 * 提示
 *
 * @author Y
 * @version 0.1
 * @date 2016年8月22日09:33:29
 */

public class PromptDialog extends BaseDialog {
    public static final String TAG = PromptDialog.class.getSimpleName();

    private TextView tvTitle;
    private TextView tvMsg;
    private Button btnConfirm;
    private Button btnCancel;

    private OnPromptClickListener onPromptClickListener;

    public PromptDialog(Context context) {
        super(context);
    }

    public void setOnPromptClickListener(OnPromptClickListener onPromptClickListener) {
        this.onPromptClickListener = onPromptClickListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_prompt;
    }

    @Override
    protected void init() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvMsg = (TextView) findViewById(R.id.tv_msg);

        btnConfirm = (Button) findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                if (onPromptClickListener != null) {
                    onPromptClickListener.promptConfirmClick(view);
                }
                dismiss();
                break;
            case R.id.btn_cancel:
                if (onPromptClickListener != null) {
                    onPromptClickListener.promptCancelClick(view);
                }
                dismiss();
                break;
        }
    }

    public void showConfirm(boolean isShow) {
        if (isShow) {
            btnConfirm.setVisibility(View.VISIBLE);
        } else {
            btnConfirm.setVisibility(View.GONE);
        }
    }

    public void setPromptText(String text) {
        tvMsg.setText(text);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setMsg(String msg) {
        tvMsg.setText(msg);
    }

    public void setConfirm(String text) {
        btnConfirm.setText(text);
    }

    public void setCancel(String text) {
        btnCancel.setText(text);
    }

    public interface OnPromptClickListener {

        void promptConfirmClick(View view);

        void promptCancelClick(View view);

    }

}
