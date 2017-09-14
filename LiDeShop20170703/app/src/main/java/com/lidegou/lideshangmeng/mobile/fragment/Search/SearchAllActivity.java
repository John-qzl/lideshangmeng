package com.lidegou.lideshangmeng.mobile.fragment.Search;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.data.entity.Contance;
import com.lidegou.lideshangmeng.mobile.data.entity.HistoryShowBean;
import com.lidegou.lideshangmeng.mobile.data.entity.Search;
import com.lidegou.lideshangmeng.mobile.db.HistoryHelper;
import com.lidegou.lideshangmeng.mobile.fragment.SearchCommodity.SearchCommodityActivity;
import com.lidegou.lideshangmeng.mobile.fragment.SearchStoreShop.SearchStoreShopActivity;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.util.view.SearchClearEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 搜索店铺/商品
 */
public class SearchAllActivity extends BaseActivity implements SearchAllContract.View, View.OnClickListener,
        AdapterView.OnItemClickListener, SearchHotKeyWordAdapter.OnClickListener {
    @InjectView(R.id.lin_back)
    LinearLayout lin_back;
    @InjectView(R.id.tv_search)
    Button tv_search;
    @InjectView(R.id.clear_et)
    SearchClearEditText clear_et;
    @InjectView(R.id.search_history_gv)
    GridView gv_search_history;
    @InjectView(R.id.search_xianshi_lv)
    ListView lv_search_xianshi;
    @InjectView(R.id.search_framelayout)
    FrameLayout searchFramelayout;
    private static final String[] m_Countries = {"店铺", "商品"}; //定义数组
    @InjectView(R.id.clear_search_history_btn)
    ImageView clear_search_history_btn;
    @InjectView(R.id.search_hot_lv)
    GridView searchHotLv;
    @InjectView(R.id.tv_goods)
    TextView tvGoods;
    @InjectView(R.id.tv_shops)
    TextView tvShops;
    private ArrayAdapter adapter; //存放数据
    // 初始化数据库打开器
    HistoryHelper helper;
    private SearchHistoryAdapter his_adapter;
    private SearchHotKeyWordAdapter searchHotKeyWordAdapter;

    private List<HistoryShowBean> his_data;
    private List<String> hotKeyWordList = new ArrayList<>();
    static List<String> list = new ArrayList<String>();

    private SearchAllContract.Presenter presenter;
    private int typeSelete = 2;
    private String keyword;

    private boolean isText = true;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    // 点击清除记录按钮，刷新界面
                    his_adapter.refresh(queryHistoryData());
                    break;

                default:
                    break;
            }
        }

        ;
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_all;
    }

    @Override
    protected void init() {
        ButterKnife.inject(this);

        String keyword = getIntent().getStringExtra("keyword");
        if (keyword != null) {
            clear_et.setText(keyword);
        }

        his_data = queryHistoryData();
        tv_search.setOnClickListener(this);
        clear_search_history_btn.setOnClickListener(this);
        lin_back.setOnClickListener(this);

        gv_search_history.setOnItemClickListener(this);
        lv_search_xianshi.setOnItemClickListener(this);
        tvGoods.setOnClickListener(this);
        tvShops.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new SearchAllPresenter(this);
        }
        presenter.start();

        his_data = queryHistoryData();
        his_adapter = new SearchHistoryAdapter(this);
        his_adapter.setData(his_data);
        gv_search_history.setAdapter(his_adapter);
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                App.getApp().closeSearch();
                break;
            case R.id.tv_search: // 点击搜索按钮
                keyword = clear_et.getText().toString().trim();
                if (keyword.length() <= 0) {
                    showToast("请输入搜索内容");
                    return;
                }
            /*
             * 点击搜索按钮先清空，是因为数据使用list集合保存的，如果搜索一个景点出来数据后，
			 * 直接搜索另一个，就会叠加，所以先清空，这样每次显示的都是最新的
			 */
                list.removeAll(list);
                // 点击搜索按钮，使得显示搜索结果的listview显示出来
                lv_search_xianshi.setVisibility(View.VISIBLE);
                gv_search_history.setVisibility(View.VISIBLE);
                clear_search_history_btn.setVisibility(View.GONE);

                int count = 0;
          /*      for (int i = 0; i < JingdianName.names.length; i++) {
                    if (JingdianName.names[i].contains(search) == true) {
                        list.add(JingdianName.names[i]);
                        count++;
                    }
                }*/
                if (count == 0) { // 如果一个也没有查到，就显示没有景点
                    list.removeAll(list); // 清空集合数据，重新查询
                    getData(list, lv_search_xianshi);
                } else { // 有数据就显示
                    getData(list, lv_search_xianshi);
                }

                // 插入数据库数据
                insertHistory(keyword);
                if (typeSelete == 2) {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), SearchCommodityActivity.class);
                    intent.putExtra("catID", 1);
                    intent.putExtra("typeSelete", "2");
                    intent.putExtra("keyword", keyword);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), SearchStoreShopActivity.class);
                    intent.putExtra("catID", 1);
                    intent.putExtra("typeSelete", "1");
                    intent.putExtra("keyword", keyword);
                    startActivity(intent);
                }
                break;

            case R.id.clear_search_history_btn: // 点击清除历史记录按钮
                deleteHistory();
                break;
            case R.id.tv_goods:
                if (isText) {
                    tvGoods.setVisibility(View.GONE);
                    tvShops.setVisibility(View.VISIBLE);
                    typeSelete = 1;
                    isText = false;
                }
                break;
            case R.id.tv_shops:
                if (!isText) {
                    tvGoods.setVisibility(View.VISIBLE);
                    tvShops.setVisibility(View.GONE);
                    typeSelete = 2;
                    isText = true;
                }
                break;
            default:
                break;
        }
    }

    /*
     * 删除历史记录
	 */
    private void deleteHistory() {
        helper = new HistoryHelper(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        // 删除表。
        db.execSQL("delete from " + Contance.HISTORY_TABLENAME);
        new Thread(new Runnable() {

            @Override
            public void run() {
                // 此处handler发送一个message，用来更新ui
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        }).start();
        // his_adapter.refresh(queryHistoryData());
        db.close();
    }

    /*
     * 插入历史记录，点击按钮，获得edittext的值，写到数据库
     */
    private void insertHistory(String search) {
        helper = new HistoryHelper(getApplicationContext());
        // 插入数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        Log.i("create", "数据库表history创建成功");
        int count = 0;
        // 查询数据库，判断edittext的内容是否已经存在，如果存在了，就不写了，如果不存在，就插入数据库
        // 取回查询存放history表的h_name列的list集合
        List<String> list = queryHistorySql();
        for (int i = 0; i < list.size(); i++) {
            // 获取搜索框的输入内容，和数据已经存在的记录比对，如果有一样的，就count增加；
            if (list.get(i).equals(search)) {
                count++;
            }
        }
        // 如果count == 0，说明没有重复的数据，就可以插入数据库history表中
        if (count == 0) {
            db.execSQL("insert into " + Contance.HISTORY_TABLENAME
                    + " values(?,?)", new Object[]{null, search});
            Log.i("create", "数据库表history数据插入成功");
        } else {
            // Toast.makeText(getApplicationContext(), "已存在",
            // Toast.LENGTH_SHORT)
            // .show();
        }

        db.close();
    }

    /*
     * 查询数据库的h_name一列，然后放到list集合中，用于判断是否插入数据
     */
    private List<String> queryHistorySql() {
        helper = new HistoryHelper(getApplicationContext());
        List<String> list = new ArrayList<String>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "
                + Contance.HISTORY_TABLENAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            // 查询数据库，取出h_name这一列，然后全部放到list集合中，在前面调用此方法的时候，用来判断
            String name = cursor.getString(cursor.getColumnIndex("h_name"));
            list.add(name);
            cursor.moveToNext();
        }

        db.close();
        // 返回一个list集合
        return list;
    }

    /*
     * 点击搜索按钮后，查询出来的结果
     */
    private void getData(List<String> list, ListView listView) {
//        sea_data = new ArrayList<SearchShowBean>();
//        for (int i = 0; i < list.size(); i++) {
//            SearchShowBean bean = new SearchShowBean();
//            bean.setJingdian(list.get(i));
//            bean.setImage(getResources().getDrawable(
//                    R.drawable.umeng_socialize_search_icon));
//            sea_data.add(bean);
//        }
//        listView.setAdapter(new SearchShowLvAdapter(this, sea_data));
    }

    // 将每一行的数据封装成一个HistoryShowBean对象，然后放到his_list中
    private List<HistoryShowBean> queryHistoryData() {
        helper = new HistoryHelper(getApplicationContext());
        List<HistoryShowBean> his_list = new ArrayList<HistoryShowBean>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor his_c = db.rawQuery("select * from "
                + Contance.HISTORY_TABLENAME, null);

        his_c.moveToFirst();
        while (!his_c.isAfterLast()) {
            int h_id = his_c.getInt(his_c.getColumnIndex("_id"));
            String h_name = his_c.getString(his_c.getColumnIndex("h_name"));

            // 用一个HistoryShowBean类来封装得到的数据
            final HistoryShowBean his_bean = new HistoryShowBean();
            his_bean.setJingdian(h_name);
            his_bean.setImage(getResources().getDrawable(
                    R.drawable.history_icon));
            his_list.add(his_bean);
            his_c.moveToNext();
        }
        if (his_list.size() == 0) {
            clear_search_history_btn.setVisibility(View.GONE);
        } else {
            clear_search_history_btn.setVisibility(View.VISIBLE);
        }
        db.close();
        return his_list;

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            App.getApp().closeSearch();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        switch (parent.getId()) { // 获得listview的id
            case R.id.search_history_gv:
                view = gv_search_history.getChildAt(position);
                TextView tv1 = (TextView) view.findViewById(R.id.search_history_tv);
                String str1 = tv1.getText().toString().trim();
                String string = null;
                int count1 = 0;
                if (count1 == 0) {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), SearchCommodityActivity.class);
                    intent.putExtra("catID", 1);
                    intent.putExtra("typeSelete", "2");
                    intent.putExtra("keyword", str1);
                    startActivity(intent);
                    clear_et.setText(str1);
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void callbackSearchHotkeywordSuccess(Search.HotKeyWord hotKeyWord) {
        hotKeyWordList = hotKeyWord.getKeyword();
        searchHotKeyWordAdapter = new SearchHotKeyWordAdapter(hotKeyWordList, this);
        searchHotKeyWordAdapter.setOnClickListener(this);
        searchHotLv.setAdapter(searchHotKeyWordAdapter);
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SearchAll Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void callbackClickListener(String keyword) {
        this.keyword = keyword;
        Intent intent = new Intent();
        intent.setClass(getContext(), SearchCommodityActivity.class);
        intent.putExtra("catID", 1);
        intent.putExtra("typeSelete", "2");
        intent.putExtra("keyword", keyword);
        startActivity(intent);
    }
}
