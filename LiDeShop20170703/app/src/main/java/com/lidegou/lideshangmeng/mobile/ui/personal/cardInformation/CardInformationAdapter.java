package com.lidegou.lideshangmeng.mobile.ui.personal.cardInformation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.BankCard;
import com.lidegou.lideshangmeng.mobile.event.OnRecyclerItemClickListener;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseRecyclerAdapter;

/**
 * Created by Administrator on 2016/12/16.
 */

public class CardInformationAdapter extends BaseRecyclerAdapter<BankCard, CardInformationAdapter.MallSubClassifyHolder> {
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    private OnClickListener onClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public void setOnClickListener(CardInformationAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_card_list;
    }

    @Override
    public MallSubClassifyHolder getViewHolder(View view) {
        return new MallSubClassifyHolder(view);
    }

    @Override
    public void onBindViewHolder(MallSubClassifyHolder holder, final BankCard data, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onRecyclerItemClickListener != null) {
                    onRecyclerItemClickListener.recyclerItemClick(view, position);
                }
            }
        });
        holder.tv_bank_code.setText(data.getBank_name());
        holder.tv_bank_card.setText("尾号：" + data.getBank_card());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.cardListDelete(v, position);
                }
            }
        });
    }

    class MallSubClassifyHolder extends RecyclerView.ViewHolder {
        ImageView delete;
        TextView tv_bank_code;
        TextView tv_bank_card;

        public MallSubClassifyHolder(View itemView) {
            super(itemView);
            delete = (ImageView) itemView.findViewById(R.id.delete);
            tv_bank_code = (TextView) itemView.findViewById(R.id.tv_bank_code);
            tv_bank_card = (TextView) itemView.findViewById(R.id.tv_bank_card);
        }
    }

    public interface OnClickListener {
        void cardListDelete(View view, int position);
    }
}
