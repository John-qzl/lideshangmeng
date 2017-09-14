package com.lidegou.lideshangmeng.mobile.fragment.SearchCommodity.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.adapter.PPAdapter;
import com.lidegou.lideshangmeng.mobile.data.entity.PPEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseFragment;
import com.lidegou.lideshangmeng.mobile.util.SwitchButton;
import com.lidegou.lideshangmeng.mobile.util.dialog.LoadingDialog;
import com.lidegou.lideshangmeng.mobile.util.view.RangeSeekBar;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class FragmentOne extends BaseFragment implements AdapterView.OnItemClickListener, SearchFragmentOneContract.View {

    private LoadingDialog loadingDialog;
    private FragmentOneCallBack callBack;
    private ProvinceCallBackCallBack provinceCallBack;
    private TextView tvCity;
    private String isself = "0";//自营
    private String hasgoods = "0";//仅看有货
    private String promotion = "0";//促销

    private Button btnOnly;
    private Button btnPromotion;
    private boolean isOnly = true;
    private boolean isPromotion = true;

    private ImageView ivSelf;

    private TextView tvPriceMin;
    private TextView tvPriceMax;

    private ListView ppListView;
    private PPAdapter adapter;
    private ArrayList<PPEntity> ppEntities = new ArrayList<>();
    private String selectId = "0";
    private TextView pinpai;

    private RangeSeekBar<Integer> seekBar;
    private SwitchButton seeBar;

    public FragmentOne(FragmentOneCallBack callBack, ProvinceCallBackCallBack provinceCallBack) {
        this.callBack = callBack;
        this.provinceCallBack = provinceCallBack;
    }

    public FragmentOne() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu_good;
    }

    @Override
    protected void init() {
        loadingDialog = new LoadingDialog(getContext());
        tvCity = (TextView) getRootView().findViewById(R.id.tv_city);
        getRootView().findViewById(R.id.lin_delivery).setOnClickListener(this);
        getRootView().findViewById(R.id.clear_options).setOnClickListener(this);
        getRootView().findViewById(R.id.confirm_options).setOnClickListener(this);
        getRootView().findViewById(R.id.RelativeLayout1).setOnClickListener(this);

        ivSelf = (ImageView) getRootView().findViewById(R.id.iv_self);
        pinpai = (TextView) getRootView().findViewById(R.id.pinpai);
        ppListView = (ListView) getRootView().findViewById(R.id.ppListView);
        tvPriceMin = (TextView) getRootView().findViewById(R.id.tv_min_price);
        tvPriceMax = (TextView) getRootView().findViewById(R.id.tv_max_price);
        btnOnly = (Button) getRootView().findViewById(R.id.btn_only);
        btnPromotion = (Button) getRootView().findViewById(R.id.btn_promotion);
        ivSelf.setOnClickListener(this);
        btnOnly.setOnClickListener(this);
        btnPromotion.setOnClickListener(this);

        setSeekBar();

        adapter = new PPAdapter(getActivity());
        PPEntity entity = new PPEntity();
        entity.setId("0");
        entity.setName("全部");
        ppEntities.add(entity);
        adapter.setData(ppEntities);
        ppListView.setAdapter(adapter);
        ppListView.setOnItemClickListener(this);
        SearchFragmentOnePresenter presenter = new SearchFragmentOnePresenter(this);
        presenter.BrandsList();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_delivery:
                provinceCallBack.provinceCallBack(1);
                break;
            case R.id.iv_self:
                if (isself.equals("0")) {
                    isself = "1";
                    ivSelf.setImageResource(R.drawable.self_open);
                    callBack.IsselfCallBack(isself);
                } else {
                    isself = "0";
                    ivSelf.setImageResource(R.drawable.self_close);
                    callBack.IsselfCallBack(isself);
                }
                break;
            case R.id.clear_options:
                //自营
                isself = "0";
                ivSelf.setImageResource(R.drawable.self_close);

                //仅有货
                btnOnly.setTextColor(Color.parseColor("#000000"));
                btnOnly.setBackgroundResource(R.drawable.good_ed_background);
                isOnly = true;
                hasgoods = "0";
                callBack.OnlyCallBack(hasgoods);

                //促销
                btnPromotion.setTextColor(Color.parseColor("#000000"));
                btnPromotion.setBackgroundResource(R.drawable.good_ed_background);
                isPromotion = true;
                promotion = "0";

                //城市
                pinpai.setText("全部");
                selectId = "0";
                adapter.setSelectId(selectId);


                //价格
                tvPriceMin.setText("0");
                tvPriceMax.setText("10000");
                seekBar.setSelectedMaxValue(10000);
                seekBar.setSelectedMinValue(0);
                callBack.PriceMinCallBack("0");
                callBack.PriceMaxCallBack("10000");

                break;
            case R.id.confirm_options:
                callBack.BrandCallBack(selectId);
                callBack.PromotionCallBack(promotion);
                callBack.OnlyCallBack(hasgoods);
                callBack.PriceMinCallBack(tvPriceMin.getText().toString());
                callBack.PriceMaxCallBack(tvPriceMax.getText().toString());
                callBack.confirmCallBack("yes");
                break;
            case R.id.btn_only:
                if (isOnly) {
                    btnOnly.setTextColor(Color.parseColor("#FF0000"));
                    btnOnly.setBackgroundResource(R.drawable.button_red_circle);
                    isOnly = false;
                    hasgoods = "1";
                    callBack.OnlyCallBack(hasgoods);
                } else {
                    btnOnly.setTextColor(Color.parseColor("#000000"));
                    btnOnly.setBackgroundResource(R.drawable.good_ed_background);
                    isOnly = true;
                    hasgoods = "0";
                    callBack.OnlyCallBack(hasgoods);
                }
                break;
            case R.id.btn_promotion:
                if (isPromotion) {
                    btnPromotion.setTextColor(Color.parseColor("#FF0000"));
                    btnPromotion.setBackgroundResource(R.drawable.button_red_circle);
                    isPromotion = false;
                    promotion = "1";
                } else {
                    btnPromotion.setTextColor(Color.parseColor("#000000"));
                    btnPromotion.setBackgroundResource(R.drawable.good_ed_background);
                    isPromotion = true;
                    promotion = "0";
                }
                break;
            case R.id.RelativeLayout1:
                ppListView.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void lazyLoad() {

    }


    public void setAddress(String province, String city, String area) {
        if (province != null && city != null && area != null) {
            tvCity.setText(province + city + area);
        }
    }

    public void setSeekBar() {

        seekBar = new RangeSeekBar<Integer>(0, 10000, getActivity());

        // seekBar.setNotifyWhileDragging(true);
        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                // handle changed range values
                Log.i(TAG, "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
                tvPriceMin.setText(minValue + "");
                tvPriceMax.setText(maxValue + "");
            }
        });
        // add RangeSeekBar to pre-defined layout
        LinearLayout layout = (LinearLayout) getRootView().findViewById(R.id.rl_all);
        layout.addView(seekBar);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PPEntity entity = ppEntities.get(position);
        pinpai.setText(entity.getName());
        selectId = entity.getId();
        adapter.setSelectId(selectId);
        adapter.notifyDataSetChanged();
        ppListView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    public void BrandsListSuccess(ArrayList<PPEntity> entities) {
        ppEntities.addAll(entities);
        adapter.notifyDataSetChanged();
    }


    public interface FragmentOneCallBack {
        void confirmCallBack(String msg);

        void IsselfCallBack(String isself);

        void OnlyCallBack(String hasgoods);

        void PromotionCallBack(String promotion);

        void PriceMinCallBack(String min);

        void PriceMaxCallBack(String max);

        void BrandCallBack(String brandId);
    }

    public interface ProvinceCallBackCallBack {
        void provinceCallBack(int data);
    }
}
