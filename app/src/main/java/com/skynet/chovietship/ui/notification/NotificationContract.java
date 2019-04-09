package com.skynet.chovietship.ui.Notification;


import com.skynet.chovietship.models.Notification;
import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

import java.util.List;

public interface NotificationContract {
    interface View extends BaseView {
        void onSuccessGetServices(List<Notification> listGroupServices);
    }

    interface Presenter extends IBasePresenter, OnHomeRequestFinish {
        void getAllService(String idShop);
    }

    interface Interactor {
        void doGetAllService(String idShop);
    }

    interface OnHomeRequestFinish extends OnFinishListener {
        void onSuccessGetServices(List<Notification> listGroupServices);
    }
}
