package com.lidegou.lideshangmeng.mobile.fragment.SearchStoreShop;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.ShopsStore;

import java.util.List;

public class SearchStoreShopCatsAdapter extends BaseAdapter {

	private Context mContext;
	private List<ShopsStore.ShopsStoreClassify> mdata;
	private OnClickListener onClickListener;
	int mSelect = 0;   //选中项
	public SearchStoreShopCatsAdapter(List<ShopsStore.ShopsStoreClassify> data, Context context) {
		this.mContext = context;
		this.mdata = data;
	}

	public void setOnClickListener(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}

	public void refresh(List<ShopsStore.ShopsStoreClassify> data) {
		this.mdata = data;
		notifyDataSetChanged();
	}

	public void changeSelected(int positon) { //刷新方法
		if (positon != mSelect) {
			mSelect = positon;
			notifyDataSetChanged();
		}
	}

	@Override
	public int getCount() {
		return mdata.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(mContext,
					R.layout.item_shops_classify, null);
			holder = new ViewHolder();
			holder.textView = (TextView) convertView
					.findViewById(R.id.tv_shops_class);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (mSelect == position) {
			holder.textView.setBackgroundResource(R.drawable.lin_border_red);   //选中项背景
			holder.textView.setTextColor(Color.parseColor("#FF0000"));
		} else {
			holder.textView.setBackgroundResource(R.drawable.lin_border);  //其他项背景
			holder.textView.setTextColor(Color.parseColor("#000000"));
		}
		ShopsStore.ShopsStoreClassify shopsStoreClassify=mdata.get(position);
		holder.textView.setText(shopsStoreClassify.getCat_alias_name()+"");
		holder.textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onClickListener!=null){
					onClickListener.ShopsStoreClassify(v,position);
					changeSelected(position);
				}
			}
		});
		return convertView;
	}

	private static class ViewHolder {
		TextView textView;
	}

	public interface OnClickListener {
		void ShopsStoreClassify(View view, int position);
	}

}
