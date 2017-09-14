package com.lidegou.lideshangmeng.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.MessageSysEntity;
import com.lidegou.lideshangmeng.mobile.util.MyImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14.
 */

public class MineSysNoticeAdapter extends BaseAdapter {

    private List<MessageSysEntity.Data> messageList;
    private Context context;

    public MineSysNoticeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setDataList(List<MessageSysEntity.Data> dataList) {
        this.messageList = dataList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_list_sys_message, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        MessageSysEntity.Data data = messageList.get(position);
        holder.tvTitle.setText(data.getTitle());
        holder.tvTime.setText(data.getTime());
        holder.tvContent.setText(data.getContent());
        if (data.getPic() == null || data.getPic().equals("")) {
            holder.ivImg.setVisibility(View.GONE);
        } else {
            holder.ivImg.setVisibility(View.VISIBLE);
            //初始化图片加载
            MyImageLoader.getInstance().loaderImage(
                    data.getPic(),
                    holder.ivImg
            );
        }
        if (data.getStatus().equals("0")) {
            holder.messRead.setVisibility(View.GONE);
        } else {
            holder.messRead.setVisibility(View.VISIBLE);
        }
        return view;
    }

    class ViewHolder {

        View itemView;
        ImageView ivImg;
        TextView tvTitle;
        TextView tvTime;
        TextView tvContent;
        TextView messRead;

        public ViewHolder(View itemView) {
            this.itemView = itemView;

            ivImg = (ImageView) itemView.findViewById(R.id.mess_img);
            tvTitle = (TextView) itemView.findViewById(R.id.mess_title);
            tvTime = (TextView) itemView.findViewById(R.id.mess_time);
            tvContent = (TextView) itemView.findViewById(R.id.mess_content);
            messRead = (TextView) itemView.findViewById(R.id.mess_read);

        }

    }
}
