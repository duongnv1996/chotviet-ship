package com.skynet.choviet.ui.scanqr;

import com.skynet.choviet.models.ShopDetail;
import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

public interface ScanContract {
    interface View extends BaseView {
    }

    interface Presenter extends IBasePresenter ,Listener{
        void getShop(String id);
    }

    interface Interactor {
        void getShop(String id);
    }

    interface Listener extends OnFinishListener {
    }
}
