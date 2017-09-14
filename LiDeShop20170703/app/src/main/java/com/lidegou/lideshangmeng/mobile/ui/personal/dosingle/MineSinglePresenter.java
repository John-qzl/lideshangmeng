package com.lidegou.lideshangmeng.mobile.ui.personal.dosingle;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.MoneyManage;
import com.lidegou.lideshangmeng.mobile.data.entity.OtherPayEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.PaySinleEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.Single;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */

public class MineSinglePresenter implements MineSingleContract.Presenter {
    private MineSingleContract.View view;
    private MineSingleContract.SingleList viewSingleList;
    private MineSingleContract.PaySingle paySingle;
    private IMyDao iMyDao;
    private List<Single> singleList = new ArrayList<>();
    private boolean flag = false;

    public MineSinglePresenter(MineSingleContract.View view) {
        this.view = view;
        this.iMyDao = new IMyDaoImpl();
    }

    public MineSinglePresenter(MineSingleContract.SingleList viewSingleList) {
        this.viewSingleList = viewSingleList;
        this.iMyDao = new IMyDaoImpl();
    }

    public MineSinglePresenter(MineSingleContract.PaySingle paySingle) {
        this.paySingle = paySingle;
        this.iMyDao = new IMyDaoImpl();

    }

    @Override
    public void start() {
        getCeilingMoney();
        moneyManage();
    }


    @Override
    public void getCeilingMoney() {
        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return;
        }
        iMyDao.getSingleCeilingMoney(view.getApp().getUid(), new ResponseCallback<Single.Data.CeilingMoney>() {
            @Override
            public void onSuccess(Single.Data.CeilingMoney data) {
                if (data != null) {
                    view.callbackCeilingMoneySuccess(data);
                }
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
    public boolean getMcheckuser() {

        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return flag;
        }
        iMyDao.getSingleMcheckuser(App.getApp().getUid(), view.getPhone(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                if (data != null && !data.equals("")) {
                    view.callbackMcheckuserStringSuccess(data);
                    flag = true;
                }
            }

            @Override
            public void onFailure(int code, String msg) {

                if (code > 0) {
                    view.showError(msg);
                }
                flag = false;
            }
        });
        return flag;
    }
    public void moneyManage() {
        if (view.getApp().getUid() == null) {
            view.showError("请先登录");
            return;
        }
        iMyDao.getMoneyManage2(view.getApp().getUid(), new ResponseCallback<MoneyManage>() {
            @Override
            public void onSuccess(MoneyManage moneyManage) {
                view.callbackMoneyManageSuccess(moneyManage);
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
    public void getMcheckuser(String id) {
        iMyDao.getUserName(id, new ResponseCallback<ArrayList<String>>() {
            @Override
            public void onSuccess(ArrayList<String> list) {
                if (list != null && list.size() == 2) {
                    view.callbackMcheckuserListSuccess(list);
                }
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
    public boolean AddSingle() {
        if (view.getPhone() == null || view.getPhone().equals("")) {
            view.showError("手机不能为空");
            return false;
        }
        if (view.getPrice() == null || view.getPrice().equals("")) {
            view.showError("请填写购物金额");
            return false;
        }
        if (view.getGoodsname() == null || view.getGoodsname().equals("")) {
            view.showError("请填写商品名称");
            return false;
        }
        if (view.getGoodsnum() == null || view.getGoodsnum().equals("")) {
            view.showError("请填写商品数量");
            return false;
        }
        if (view.getQingdanbianhao() == null || view.getQingdanbianhao().equals("")) {
            view.showError("请填写消费清单编号");
            return false;
        }

        iMyDao.AddSingle(App.getApp().getUid(), view.getPhone(), view.getPrice(), view.getGoodsname(), view.getGoodsnum(), view.getGoodsinfo(), view.getQingdanbianhao(), view.getIs_me(), view.getZuodan_img(), view.getPicType(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                if (data != null || !data.equals("")) {
                    view.callbackAddSingleSuccess(data);
                } else {
                    view.showError("做单失败");
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    view.showError(msg);
                }
            }
        });
        return true;
    }

    @Override
    public void getSingleList() {
        iMyDao.SingleList(App.getApp().getUid(), viewSingleList.page(), viewSingleList.status(), new ResponseCallback<Single>() {
            @Override
            public void onSuccess(Single data) {
                viewSingleList.callbackSingleListSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    viewSingleList.showError(msg);
                }
            }
        });
    }

    @Override
    public void getPaySingle() {
        iMyDao.PaySingle(App.getApp().getUid(), paySingle.Id(), new ResponseCallback<PaySinleEntity>() {
            @Override
            public void onSuccess(PaySinleEntity entity) {
                if (entity != null) {
                    paySingle.callbackPaySingle(entity);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    paySingle.showError(msg);
                }
            }
        });
    }

    @Override
    public void pay() {
        if (paySingle.payType().equals("")) {
            paySingle.showError("请选择支付方式");
            return;
        }
        iMyDao.pay(App.getApp().getUid(), paySingle.payType(), paySingle.Id(), new ResponseCallback<OtherPayEntity>() {
            @Override
            public void onSuccess(OtherPayEntity data) {
                paySingle.paySuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    paySingle.showError(msg);
                }
            }
        });
    }


}
