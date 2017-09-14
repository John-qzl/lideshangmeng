package com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin.loac;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;


public class ShopLoactionActivity extends BaseActivity implements BaiduMap.OnMapClickListener {
    private MapView mapView;
    private BaiduMap baiduMap;
    private double latitude;
    private double longitude;

    @Override

    protected int getLayoutId() {
        return R.layout.activity_shop_loaction;
    }

    @Override
    protected void init() {
        String city = getIntent().getStringExtra("city");
        String address = getIntent().getStringExtra("address");
        mapView = (MapView) findViewById(R.id.store_street_mapView);
        baiduMap = mapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        baiduMap.setOnMapClickListener(this);

        findViewById(R.id.lin_back).setOnClickListener(this);
        findViewById(R.id.lin_save).setOnClickListener(this);

        GeoCoder geoCoder = GeoCoder.newInstance();
        geoCoder.setOnGetGeoCodeResultListener(listener);
        geoCoder.geocode(new GeoCodeOption()
                .city(city)
                .address(address));
    }

    OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
        public void onGetGeoCodeResult(GeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有检索到结果
                return;
            }
            latitude = result.getLocation().latitude;
            longitude = result.getLocation().longitude;
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.merchants_loac);
            MarkerOptions options = new MarkerOptions().position(new LatLng(latitude, longitude))
                    .icon(bitmap);
            baiduMap.addOverlay(options);

            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 13);
            baiduMap.animateMapStatus(mapStatusUpdate);

            //获取地理编码结果
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有找到检索结果
            }
            //获取反向地理编码结果
        }
    };

    @Override
    public void onMapClick(LatLng latLng) {
        baiduMap.clear();
        this.latitude = latLng.latitude;
        this.longitude = latLng.longitude;
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.merchants_loac);
        MarkerOptions options = new MarkerOptions().position(latLng)
                .icon(bitmap);
        baiduMap.addOverlay(options);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(100);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void viewClick(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                setResult(100);
                finish();
                break;
            case R.id.lin_save:
                setResult();
                finish();
                break;
        }
    }

    private void setResult() {
        Intent intent = new Intent();
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        setResult(100, intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }
}
