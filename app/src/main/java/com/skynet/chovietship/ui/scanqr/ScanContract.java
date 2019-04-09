package com.skynet.chovietship.ui.scanqr;

import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

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
