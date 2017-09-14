package com.lidegou.lideshangmeng.mobile.ui.personal.mydata.address;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Address;
import com.lidegou.lideshangmeng.mobile.event.OnRecyclerItemClickListener;
import com.lidegou.lideshangmeng.mobile.util.BaseRecyclerAdapter;

/**
 * Created by Administrator on 2016/12/7.
 */

public class AddressAdapter extends BaseRecyclerAdapter<Address.ConsigneeListBean, AddressAdapter.AddressHolder> {

    private OnAddressClickListener onAddressClickListener;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    private Address address;

    public AddressAdapter(Address address) {
        this.address = address;
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public void setOnAddressClickListener(OnAddressClickListener onAddressClickListener) {
        this.onAddressClickListener = onAddressClickListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_address_list;

    }

    @Override
    public AddressHolder getViewHolder(View view) {
        return new AddressHolder(view);
    }

    @Override
    public void onBindViewHolder(final AddressHolder holder, final Address.ConsigneeListBean data, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onRecyclerItemClickListener != null) {
                    onRecyclerItemClickListener.recyclerItemClick(view, position);
                }
            }
        });
        if (address.getAddress_id() != null && address.getAddress_id().equals(data.getAddress_id())) {
            holder.ivSelete.setImageResource(R.drawable.automaticlogin02);
            holder.tvDefault.setText("默认地址");
        } else {
            holder.tvDefault.setText("设置地址");
            holder.ivSelete.setImageResource(R.drawable.automaticlogin01);
        }
        holder.tvConsignee.setText(data.getConsignee());
        holder.tvMobile.setText(data.getMobile());
        holder.tvAddress.setText(data.getAddress());
        holder.reDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAddressClickListener != null) {
                    onAddressClickListener.addressDelete(v, position);
                }
            }
        });
        holder.reEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAddressClickListener != null) {
                    onAddressClickListener.addressUpdate(v, position);
                }
            }
        });
        holder.ivSelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ivSelete.setImageResource(R.drawable.automaticlogin02);
                if (onAddressClickListener != null) {
                    onAddressClickListener.addressDefault(data.getAddress_id(), position);
                }
            }
        });
    }

    class AddressHolder extends RecyclerView.ViewHolder {

        ImageView ivSelete;
        TextView tvDefault;
        LinearLayout reEditor;
        LinearLayout reDelete;
        TextView tvConsignee;
        TextView tvMobile;
        TextView tvAddress;

        public AddressHolder(View itemView) {
            super(itemView);
            ivSelete = (ImageView) itemView.findViewById(R.id.im_select);
            tvDefault = (TextView) itemView.findViewById(R.id.tv_default);
            reEditor = (LinearLayout) itemView.findViewById(R.id.re_editor);
            reDelete = (LinearLayout) itemView.findViewById(R.id.re_delete);
            tvConsignee = (TextView) itemView.findViewById(R.id.tv_consignee);
            tvMobile = (TextView) itemView.findViewById(R.id.tv_mobile);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
        }
    }

    interface OnAddressClickListener {
        void addressDelete(View view, int position);

        void addressUpdate(View view, int position);

        void addressDefault(String addressid, int position);
    }

}
