package com.lidegou.lideshangmeng.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MessageOrderntity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MineOrderNoticeAdapter extends BaseAdapter {

    private List<MessageOrderntity.Data> messageList;
    private Context context;

    public MineOrderNoticeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public MessageOrderntity.Data getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setDataList(List<MessageOrderntity.Data> dataList) {
        this.messageList = dataList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_list_order_message, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        MessageOrderntity.Data data = getItem(position);
        holder.tvContent.setText(data.getText());
        holder.tvTime.setText(data.getTime());
        if (data.getStatus().equals("0")) {
            holder.messRead.setVisibility(View.GONE);
        } else {
            holder.messRead.setVisibility(View.VISIBLE);
        }
        return view;
    }

    class ViewHolder {

        View itemView;
        TextView tvTime;
        TextView tvContent;
        TextView messRead;

        public ViewHolder(View itemView) {
            this.itemView = itemView;

            tvTime = (TextView) itemView.findViewById(R.id.mess_time);
            tvContent = (TextView) itemView.findViewById(R.id.mess_content);
            messRead = (TextView) itemView.findViewById(R.id.mess_read);
        }

    }
}
