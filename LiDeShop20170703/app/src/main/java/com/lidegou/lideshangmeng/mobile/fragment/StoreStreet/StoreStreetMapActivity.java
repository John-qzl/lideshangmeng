package com.lidegou.lideshangmeng.mobile.fragment.StoreStreet;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.lidegou.lideshangmeng.mobile.R;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.overlayutil.DrivingRouteOverlay;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.overlayutil.TransitRouteOverlay;
import com.lidegou.lideshangmeng.mobile.fragment.StoreStreet.overlayutil.WalkingRouteOverlay;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseActivity;
import com.lidegou.lideshangmeng.mobile.util.OpenBaidu;

import java.util.List;

public class StoreStreetMapActivity extends BaseActivity {

    private MapView mMapView;
    private RelativeLayout activityStoreStreetMap;
    private LinearLayout go_back;
    private TextView textView;
    private EditText start, end;
    private Button drive, walk, openBaidu;


    private BaiduMap mBaiduMap;
    private String longitude;//经度
    private String latitude;//纬度
    private String name;//商铺名字
    private RoutePlanSearch mPlanSearch;
    private PlanNode startPlan;
    private PlanNode endPlan;
    private double myLongitude;
    private double mylatitude;


    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private String city;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_store_street_map;
    }

    @Override
    protected void init() {
        mMapView = (MapView) findViewById(R.id.store_street_mapView);
        go_back = (LinearLayout) findViewById(R.id.go_back);
        start = (EditText) findViewById(R.id.start);
        end = (EditText) findViewById(R.id.end);
        drive = (Button) findViewById(R.id.drive);
        openBaidu = (Button) findViewById(R.id.openBaidu);
        walk = (Button) findViewById(R.id.walk);
        textView = (TextView) findViewById(R.id.my_title);

        longitude = getIntent().getStringExtra("longitude");
        latitude = getIntent().getStringExtra("latitude");
        if (longitude == null || latitude == null) {
            finish();
            return;
        }
        name = getIntent().getStringExtra("name");
        go_back.setOnClickListener(this);
        drive.setOnClickListener(this);
        openBaidu.setOnClickListener(this);
        walk.setOnClickListener(this);
        /**
         * 初始化titleBar的标题
         * */
        if (name != null && !name.equals("")) {
            textView.setText(name);
            end.setText(name);
        }


        /**
         * 路线规划初始化
         * */
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mPlanSearch = RoutePlanSearch.newInstance();
        /**
         * 显示商铺
         * */
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
                .fromAssetWithDpi("Icon_end.png");
        LatLng needLatLng = new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
        MarkerOptions options = new MarkerOptions().position(needLatLng).icon(bitmapDescriptor);
        mBaiduMap.addOverlay(options);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(needLatLng, 15);
        mBaiduMap.animateMapStatus(u);
        /**
         * 路线规划监听
         * */
        mPlanSearch.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                if (walkingRouteResult == null || walkingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(StoreStreetMapActivity.this, "当前输入错或无此路线", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (walkingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                    //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                    //result.getSuggestAddrInfo()
                    return;
                }

                //清除地图
                mBaiduMap.clear();

                //最佳路线
                WalkingRouteLine walkingRouteLine = walkingRouteResult.getRouteLines().get(0);
                //添加覆盖物
                WalkingRouteOverlay overlay = new WalkingRouteOverlay(mBaiduMap);
                // 设置覆盖物的点击事件
                mBaiduMap.setOnMarkerClickListener(overlay);
                //给覆盖物添加数据
                overlay.setData(walkingRouteLine);
                // 将覆盖物添加到地图上
                overlay.addToMap();
                // 设置缩放级别
                overlay.zoomToSpan();
            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
                if (transitRouteResult == null || transitRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(StoreStreetMapActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (transitRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                    //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                    //result.getSuggestAddrInfo()
                    return;
                }

                if (transitRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    //清除地图
                    mBaiduMap.clear();

                    //最佳路线
                    TransitRouteLine transitRouteLine = transitRouteResult.getRouteLines().get(0);
                    //添加覆盖物
                    TransitRouteOverlay overlay = new TransitRouteOverlay(mBaiduMap);
                    // 设置覆盖物的点击事件
                    mBaiduMap.setOnMarkerClickListener(overlay);
                    //给覆盖物添加数据
                    overlay.setData(transitRouteLine);
                    // 将覆盖物添加到地图上
                    overlay.addToMap();
                    // 设置缩放级别
                    overlay.zoomToSpan();
                }
            }

            @Override
            public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
                if (drivingRouteResult == null || drivingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(StoreStreetMapActivity.this, "当前输入错或无此路线", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (drivingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                    //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                    //result.getSuggestAddrInfo()
                    return;
                }
                //清除地图
                mBaiduMap.clear();
                //获取自驾最佳路线
                DrivingRouteLine drivingRouteLine = drivingRouteResult.getRouteLines().get(0);
                //创建覆盖物对象
                DrivingRouteOverlay overlay = new DrivingRouteOverlay(mBaiduMap);
                //点击事件
                mBaiduMap.setOnMarkerClickListener(overlay);
                //给覆盖物添加数据
                overlay.setData(drivingRouteLine);
                //添加到地图上
                overlay.addToMap();
                //设置缩放级别
                overlay.zoomToSpan();

            }

            @Override
            public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

            }
        });


        /**
         * 百度定位方法初始化
         * */
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        startLocation();//开始定位
    }

    @Override
    protected void viewClick(View view) {
        String startText = start.getText().toString();
        //String endText = end.getText().toString();
        if (startText.equals("")) {
            start.setHint("我的位置");
            startPlan = PlanNode.withLocation(new LatLng(mylatitude, myLongitude));
            endPlan = PlanNode.withLocation(new LatLng(Double.valueOf(latitude), Double.valueOf(longitude)));

        } else {
            startPlan = PlanNode.withCityNameAndPlaceName(city, startText);
            endPlan = PlanNode.withLocation(new LatLng(Double.valueOf(latitude), Double.valueOf(longitude)));
        }


        switch (view.getId()) {
            case R.id.go_back:
                finish();
                break;
            case R.id.drive:
                try {
                    mPlanSearch.drivingSearch(new DrivingRoutePlanOption()
                            .from(startPlan)
                            .to(endPlan));
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "超出范围或无最佳路线，请更换交通方式", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.openBaidu:
                OpenBaidu.start(this, latitude, longitude);
                break;
            case R.id.walk:
                try {
                    mPlanSearch.walkingSearch
                            (new WalkingRoutePlanOption()
                                    .from(startPlan)
                                    .to(endPlan));
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "超出范围或无最佳路线，请更换交通方式", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        if (mPlanSearch != null) {
            mPlanSearch.destroy();
        }
        if (mMapView != null) {
            mMapView.onDestroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mPlanSearch.destroy();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mPlanSearch.destroy();
        mMapView.onPause();
    }

    public void startLocation() {
        initLocation();

    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);

        //添加设置结果
        mLocationClient.setLocOption(option);

        //开始真正的定位
        mLocationClient.start();

    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            myLongitude = location.getLongitude();
            mylatitude = location.getLatitude();
            city = location.getCity();
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());
        }
    }
}
