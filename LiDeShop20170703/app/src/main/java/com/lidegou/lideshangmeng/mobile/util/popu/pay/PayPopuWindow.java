package com.lidegou.lideshangmeng.mobile.util.popu.pay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation.chongzhi.PayType;

import java.util.ArrayList;

/**
 * Created by Y on 2017/1/21.
 */

public class PayPopuWindow implements AdapterView.OnItemClickListener {
    private PopupWindow popupWindow;
    private PayPopuWindowAdapter adapter;
    private PayPopuWindowCallBack callBack;

    public PayPopuWindow(Context context, PayPopuWindowCallBack callBack) {
        this.callBack = callBack;
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View popuView = LayoutInflater.from(context).inflate(R.layout.popu_recharge, null);
        ListView popu_recharge_list = (ListView) popuView.findViewById(R.id.popu_recharge_list);
        adapter = new PayPopuWindowAdapter(context);
        popu_recharge_list.setAdapter(adapter);
        popu_recharge_list.setOnItemClickListener(this);
        popupWindow.setContentView(popuView);
    }

    public void show(View view, ArrayList<PayType> datas) {
        adapter.setData(datas);
        adapter.notifyDataSetChanged();
        popupWindow.showAsDropDown(view);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        callBack.payTypeCallBack(position);
        popupWindow.dismiss();
    }

    public interface PayPopuWindowCallBack {
        void payTypeCallBack(int position);
    }
}
