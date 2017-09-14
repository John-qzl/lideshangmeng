package com.lidegou.lideshangmeng.mobile.fragment.My.merchantsin;

import com.lidegou.lideshangmeng.mobile.App;
import com.lidegou.lideshangmeng.mobile.data.ResponseCallback;
import com.lidegou.lideshangmeng.mobile.data.dao.IMyDao;
import com.lidegou.lideshangmeng.mobile.data.dao.impl.IMyDaoImpl;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsIn;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsInFiveEntity;
import com.lidegou.lideshangmeng.mobile.data.entity.MerchantsInFourEntity;

/**
 * Created by Administrator on 2016/8/26.
 */

public class MerchantsInPresenter implements MerchantsInContract.Presenter {

    private MerchantsInContract.ViewOne viewOne;
    private MerchantsInContract.ViewTwo viewTwo;
    private MerchantsInContract.ViewThree viewThree;
    private MerchantsInContract.ViewFour viewFour;
    private MerchantsInContract.ViewFive viewFive;
    private IMyDao iMyDao;

    public MerchantsInPresenter(MerchantsInContract.ViewOne viewOne) {
        this.viewOne = viewOne;
        this.iMyDao = new IMyDaoImpl();
    }

    public MerchantsInPresenter(MerchantsInContract.ViewTwo viewTwo) {
        this.viewTwo = viewTwo;
        this.iMyDao = new IMyDaoImpl();
    }

    public MerchantsInPresenter(MerchantsInContract.ViewThree viewThree) {
        this.viewThree = viewThree;
        this.iMyDao = new IMyDaoImpl();
    }

    public MerchantsInPresenter(MerchantsInContract.ViewFour viewFour) {
        this.viewFour = viewFour;
        this.iMyDao = new IMyDaoImpl();
    }

    public MerchantsInPresenter(MerchantsInContract.ViewFive viewFive) {
        this.viewFive = viewFive;
        this.iMyDao = new IMyDaoImpl();
    }

    @Override
    public void start() {
    }


    @Override
    public void MerchantsInOne() {
        iMyDao.MerchantsInOne(viewOne.getApp().getUid(), new ResponseCallback<MerchantsIn.MerchantsInProtocol>() {
            @Override
            public void onSuccess(MerchantsIn.MerchantsInProtocol data) {
                if (data != null) {
                    viewOne.callbackMerchantsInOneSuccess(data);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    viewOne.showError(msg);
                }
            }
        });
    }

    @Override
    public void MerchantsInOnedone() {
        iMyDao.MerchantsInOnedone(viewOne.getApp().getUid(), new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                viewOne.callbackMerchantsInOnedoneSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    viewOne.showError(msg);
                }
            }
        });
    }

    @Override
    public void MerchantsInTwo() {
        iMyDao.MerchantsInTwo(viewTwo.getApp().getUid(), new ResponseCallback<MerchantsIn.MerchantsInContact>() {
            @Override
            public void onSuccess(MerchantsIn.MerchantsInContact data) {
                if (data != null) {
                    viewTwo.callbackMerchantsInTwoSuccess(data);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    viewTwo.showError(msg);
                }
            }
        });
    }

    @Override
    public void MerchantsInTwodone(String contactXinbie, String contactPhone, String contactName) {
        if (contactPhone.equals("") || contactPhone == null) {
            viewTwo.showError("手机号不能为空");
            return;
        }
        if (contactName.equals("") || contactName == null) {
            viewTwo.showError("姓名不能为空");
            return;
        }
        iMyDao.MerchantsInTwodone(viewTwo.getApp().getUid(), contactXinbie, contactPhone, contactName, new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                viewTwo.callbackMerchantsInTwodoneSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    viewTwo.showError(msg);
                }
            }
        });
    }

    @Override
    public void MerchantsInThree() {
        iMyDao.MerchantsInThree(viewThree.getApp().getUid(), new ResponseCallback<MerchantsIn.MerchantsInCompany>() {
            @Override
            public void onSuccess(MerchantsIn.MerchantsInCompany data) {
                viewThree.callbackMerchantsInThreeSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    viewThree.showError(msg);
                }
            }
        });
    }

    @Override
    public void MerchantsInThreedone(String companyName, String business_license_id, String legal_person, String busines_scope, String license_fileImg, String company_adress, String company_contactTel, String province, String city, String district, String longitude, String latitude) {
        if (companyName.equals("") || companyName == null) {
            viewThree.showError("公司名称不能为空");
            return;
        }
        if (business_license_id.equals("") || business_license_id == null) {
            viewThree.showError("营业执照注册号不能为空");
            return;
        }
        if (legal_person.equals("") || legal_person == null) {
            viewThree.showError("法定代表人姓名不能为空");
            return;
        }
        if (busines_scope.equals("") || busines_scope == null) {
            viewThree.showError("经营范围不能为空");
            return;
        }
        if (license_fileImg.equals("") || license_fileImg == null) {
            viewThree.showError("营业执照副本电子版不能为空");
            return;
        }
        if (company_adress.equals("") || company_adress == null) {
            viewThree.showError("店面详细地址不能为空");
            return;
        }
        if (company_contactTel.equals("") || company_contactTel == null) {
            viewThree.showError("店内电话不能为空");
            return;
        }
        iMyDao.MerchantsInThreedone(viewThree.getApp().getUid(), companyName, business_license_id, legal_person, busines_scope, license_fileImg, company_adress, company_contactTel, province, city, district, longitude, latitude, new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                viewThree.callbackMerchantsInThreedoneSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    viewThree.showError(msg);
                }
            }
        });
    }

    @Override
    public void MerchantsInFour() {
        iMyDao.MerchantsInFour(viewFour.getApp().getUid(), new ResponseCallback<MerchantsInFourEntity>() {
            @Override
            public void onSuccess(MerchantsInFourEntity data) {
                viewFour.callbackMerchantsInFourSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    viewFour.showError(msg);
                }
            }
        });
    }

    @Override
    public void MerchantsInFourdone(String shop_class_keyWords, String rz_shopName, String shop_categoryMain, String cat_id) {
        if (shop_class_keyWords.equals("")) {
            viewFour.showError("请输入项目描述关键词");
            return;
        }
        if (rz_shopName.equals("")) {
            viewFour.showError("请输入商铺名称");
            return;
        }
        if (shop_categoryMain == null) {
            viewFour.showError("请选择主营类目");
            return;
        }
        iMyDao.MerchantsInFourdone(App.getApp().getUid(), shop_class_keyWords, rz_shopName, shop_categoryMain, cat_id, new ResponseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                viewFour.callbackMerchantsInFourdoneSuccess(data);
            }

            @Override
            public void onFailure(int code, String msg) {

            }
        });
    }

    @Override
    public void MerchantsInFive() {
        iMyDao.MerchantsInFive(viewFive.getApp().getUid(), new ResponseCallback<MerchantsInFiveEntity>() {
            @Override
            public void onSuccess(MerchantsInFiveEntity entity) {
                viewFive.callbackMerchantsInFiveSuccess(entity);
            }

            @Override
            public void onFailure(int code, String msg) {
                if (code > 0) {
                    viewFive.showError(msg);
                }
            }
        });
    }
}
