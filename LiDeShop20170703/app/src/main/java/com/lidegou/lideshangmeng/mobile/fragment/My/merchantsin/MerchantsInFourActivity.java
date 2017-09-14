package com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.adapter.MerchantsInFourListAdapter;
import com.lidegou.lideshangmeng.mobile.adapter.MerchantsInFourSpinnerAdapter;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsInFourEntity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.util.dialog.AddClassDialog;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MerchantsInFourActivity extends BaseActivity implements MerchantsInContract.ViewFour {

    @InjectView(R.id.lin_back)
    LinearLayout linBack;
    @InjectView(R.id.spinner3)
    Spinner spinner3;
    @InjectView(R.id.addClass)
    Button addClass;
    @InjectView(R.id.lv_dian)
    ListView lvDian;
    @InjectView(R.id.btn_up)
    Button btnUp;
    @InjectView(R.id.btn_next)
    Button btnNext;
    @InjectView(R.id.ed_antistop)
    EditText edAntistop;
    @InjectView(R.id.ed_shop_name)
    EditText edShopName;

    private MerchantsInContract.Presenter presenter;
    private MerchantsInFourEntity entity;
    private MerchantsInFourSpinnerAdapter spinnerAdapter;

    private String shop_categoryMain;

    private MerchantsInFourListAdapter listAdapter;
    private ArrayList<MerchantsInFourEntity.Cats_Son> select_cats_sons = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_merchants_in_four;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);
        linBack.setOnClickListener(this);
        btnUp.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        addClass.setOnClickListener(this);

        listAdapter = new MerchantsInFourListAdapter(this, deleteCallack);
        listAdapter.setData(select_cats_sons);
        lvDian.setAdapter(listAdapter);

        presenter = new MerchantsInPresenter(this);
        presenter.MerchantsInFour();
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                finish();
                break;
            case R.id.btn_up:
                startActivity(new Intent(this, MerchantsInThreeActivity.class));
                break;
            case R.id.btn_next:
                presenter.MerchantsInFourdone(edAntistop.getText().toString(), edShopName.getText().toString(), shop_categoryMain, getCatsSonIds());
                break;
            case R.id.addClass:
                AddClassDialog addClassDialog = new AddClassDialog(this);
                addClassDialog.show(select_cats_sons, callBack, entity);
        }
    }

    @Override
    public void callbackMerchantsInFourSuccess(MerchantsInFourEntity entity) {
        this.entity = entity;
        spinnerAdapter = new MerchantsInFourSpinnerAdapter(this);
        spinnerAdapter.setData(entity.getCats());
        spinner3.setAdapter(spinnerAdapter);
        spinner3.setOnItemSelectedListener(listener);

        if (entity.getCategory_info() != null && entity.getShop_info() != null) {

            for (int position = 0; position < entity.getCats().size(); position++) {
                MerchantsInFourEntity.CatsData catsData = entity.getCats().get(position);
                if (catsData.getCat_id().equals(entity.getShop_info().getShop_categoryMain())) {
                    spinner3.setSelection(position);
                }
            }

            select_cats_sons.addAll(entity.getCategory_info());
            listAdapter.setData(select_cats_sons);
            listAdapter.notifyDataSetChanged();

            edAntistop.setText(entity.getShop_info().getShop_class_keyWords());
            edShopName.setText(entity.getShop_info().getRz_shopName());


        }
    }

    @Override
    public void callbackMerchantsInFourdoneSuccess(String msg) {
        showToast(msg);
        startActivity(new Intent(this, MerchantsInFiveActivity.class));
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            shop_categoryMain = entity.getCats().get(position).getCat_id();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    AddClassDialog.AddClassDialogCallBack callBack = new AddClassDialog.AddClassDialogCallBack() {
        @Override
        public void callBack(ArrayList<MerchantsInFourEntity.Cats_Son> cats_sons) {
            for (MerchantsInFourEntity.Cats_Son cats_son : cats_sons) {
                if (!select_cats_sons.contains(cats_son)) {
                    select_cats_sons.add(cats_son);
                }
            }
            listAdapter.setData(select_cats_sons);
            listAdapter.notifyDataSetChanged();
        }
    };


    MerchantsInFourListAdapter.MerchantsInFourListAdapterCallBack deleteCallack = new MerchantsInFourListAdapter.MerchantsInFourListAdapterCallBack() {
        @Override
        public void deleteCallBack(MerchantsInFourEntity.Cats_Son cats_son) {
            select_cats_sons.remove(cats_son);
            listAdapter.setData(select_cats_sons);
            listAdapter.notifyDataSetChanged();
        }
    };

    private String getCatsSonIds() {
        StringBuilder builder = new StringBuilder();
        for (MerchantsInFourEntity.Cats_Son select_cats_son : select_cats_sons) {
            builder.append(select_cats_son.getCat_id());
            builder.append(",");
        }
        return builder.toString();
    }
}
