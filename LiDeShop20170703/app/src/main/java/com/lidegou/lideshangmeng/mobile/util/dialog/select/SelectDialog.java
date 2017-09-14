package com.lidegou.lideshangmeng.mobile.util.dialog.select;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.util.dialog.BaseDialog;

import java.util.ArrayList;

/**
 * Created by Y on 2017/1/22.
 */

public class SelectDialog extends BaseDialog {
    private TextView dialog_select_title;
    private ListView dialog_select_listView;
    private SelectDialogAdapter adapter;

    public SelectDialog(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_select;
    }

    @Override
    protected void init() {
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setCanceledOnTouchOutside(true);
        dialog_select_title = (TextView) findViewById(R.id.dialog_select_title);
        dialog_select_listView = (ListView) findViewById(R.id.dialog_select_listView);
        adapter = new SelectDialogAdapter(getContext());

    }

    public void show(String title, ArrayList<String> list, AdapterView.OnItemClickListener clickListener) {
        super.show();
        dialog_select_title.setText(title);
        adapter.setData(list);
        dialog_select_listView.setAdapter(adapter);
        dialog_select_listView.setOnItemClickListener(clickListener);

    }

    @Override
    protected void viewClick(View view) {

    }
}
