package com.lidegou.lideshangmeng.mobile.fragment.Search;

import com.lidegou.lideshangmeng.mobile.data.entity.Search;
import com.lidegou.lideshangmeng.mobile.ui.base.BasePresenter;
import com.lidegou.lideshangmeng.mobile.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/26.
 */

public interface SearchAllContract {

    interface View extends BaseView {
        void callbackSearchHotkeywordSuccess(Search.HotKeyWord hotKeyWord);

    }

    interface Presenter extends BasePresenter {
        void Hotkeyword();
    }

}
