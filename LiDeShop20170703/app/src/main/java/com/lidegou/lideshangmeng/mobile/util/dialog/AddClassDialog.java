package com.lidegou.lideshangmeng.mobile.util.dialog;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.adapter.MerchantsInFourGridViewAdapter;
import com.lidegou.lideshangmeng.mobile.adapter.MerchantsInFourSpinnerAdapter;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsInFourEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Y on 2017/2/8.
 */

public class AddClassDialog extends BaseDialog {
    private AddClassDialogCallBack callBack;

    private MerchantsInFourEntity entity;
    private Spinner spinner4;
    private MerchantsInFourSpinnerAdapter spinnerAdapter2;

    private ArrayList<MerchantsInFourEntity.Cats_Son> cats_sons = new ArrayList<>();
    private GridView gridView;
    private MerchantsInFourGridViewAdapter gridViewAdapter;


    private ArrayList<MerchantsInFourEntity.Cats_Son> select_cats_sons = new ArrayList<>();
    private ArrayList<MerchantsInFourEntity.Cats_Son> before_cats_sons;

    public AddClassDialog(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_add_class;
    }

    @Override
    protected void init() {
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        spinner4 = (Spinner) findViewById(R.id.spinner4);
        gridView = (GridView) findViewById(R.id.gridView);
        findViewById(R.id.yes).setOnClickListener(this);
        findViewById(R.id.no).setOnClickListener(this);
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.yes:
                if (select_cats_sons.size() > 0) {
                    callBack.callBack(select_cats_sons);
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "请选择二级分类", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.no:
                dismiss();
                break;
        }
    }

    public void show(ArrayList<MerchantsInFourEntity.Cats_Son> before_cats_sons, AddClassDialogCallBack needCallBack, MerchantsInFourEntity needEntity) {
        super.show();
        callBack = needCallBack;
        entity = needEntity;
        this.before_cats_sons = before_cats_sons;
        select_cats_sons.addAll(before_cats_sons);

        spinnerAdapter2 = new MerchantsInFourSpinnerAdapter(getContext());
        spinnerAdapter2.setData(entity.getCats());
        spinner4.setAdapter(spinnerAdapter2);
        spinner4.setOnItemSelectedListener(listener2);

        gridViewAdapter = new MerchantsInFourGridViewAdapter(getContext());
        gridViewAdapter.setData(cats_sons);
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(gridListener);

        if (entity.getCats().size() > 0) {
            getCats_Son(entity.getCats().get(0).getCat_id());
        }
    }

    AdapterView.OnItemSelectedListener listener2 = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            getCats_Son(entity.getCats().get(position).getCat_id());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    AdapterView.OnItemClickListener gridListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MerchantsInFourEntity.Cats_Son cats_son = cats_sons.get(position);
            if (cats_son.isSelect()) {
                cats_son.setSelect(false);
                select_cats_sons.remove(cats_son);
            } else {
                cats_son.setSelect(true);
                select_cats_sons.add(cats_son);
            }
            gridViewAdapter.notifyDataSetChanged();
        }
    };

    private void getCats_Son(String id) {
        if (entity != null && entity.getCats_son() != null) {
            cats_sons.clear();
            select_cats_sons.clear();
            try {
                JSONObject jsonObject = new JSONObject(entity.getCats_son());
                JSONArray cats_son = jsonObject.getJSONArray(id);
                Gson gson = new Gson();
                for (int i = 0; i < cats_son.length(); i++) {
                    String cat_son = cats_son.getString(i);
                    MerchantsInFourEntity.Cats_Son cat = gson.fromJson(cat_son, MerchantsInFourEntity.Cats_Son.class);
                    cats_sons.add(cat);
                }
                for (MerchantsInFourEntity.Cats_Son cats_son1 : cats_sons) {
                    for (MerchantsInFourEntity.Cats_Son before_cats_son : before_cats_sons) {
                        if (cats_son1.getCat_id().equals(before_cats_son.getCat_id())) {
                            cats_son1.setSelect(true);
                        }
                    }
                }
                gridViewAdapter.setData(cats_sons);
                gridViewAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public interface AddClassDialogCallBack {
        void callBack(ArrayList<MerchantsInFourEntity.Cats_Son> cats_sons);
    }
}
