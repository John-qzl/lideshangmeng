package com.lidegou.lideshangmeng.mobile.fragment.ShopCar;

import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.ICommodityDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.ICommodityDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.ShoppingCart;

/**
 * Created by Administrator on 2016/12/15.
 */

public class MallShopCarPresenter implements MallShopCarContract.Presenter {

    private MallShopCarContract.View view;

    private ICommodityDao iCommodityDao;
    private int smsCode;
    private String phone;

    public MallShopCarPresenter(MallShopCarContract.View view) {
        this.view = view;
        this.iCommodityDao = new ICommodityDaoImpl();
    }

    @Override
    public void start() {
        carList();
    }


    @Override
    public void carList() {
        if (view.getApp().getUid() == null) {
            return;
        }
        iCommodityDao.catList(view.getApp().getUid(), new ResponseCallback<ShoppingCart>() {
            @Override
            public void onSuccess(ShoppingCart data) {
                view.callbackCarListSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }

    @Override
    public void deleteShopCar() {
        iCommodityDao.deleteCar(view.getApp().getUid(), view.rec_id(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.callbackDeleteShopCarListSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }

    @Override
    public void batchDeleteShopCar() {
        iCommodityDao.batchDeleteCar(view.getApp().getUid(), view.batchID() + ",", new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.callbackDeleteShopCarListSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }

    @Override
    public void updateGoodNumebr() {
        iCommodityDao.updateGoodNumber(view.getApp().getUid(), view.rec_id(), view.number(), view.arr(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                view.callbackUpdateGoodNumebrSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }

    @Override
    public void isAddress() {
        iCommodityDao.isAddress(view.getApp().getUid(), new ResponseCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean isAddress) {
                view.isAddressSuccess(isAddress);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
    }

}
