package com.skynet.chovietship.ui.detailNotification;


import com.skynet.chovietship.models.Notification;
import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

public interface DetailNotificationContract  {
    interface View extends BaseView {
        void onSuccessGetDetail(Notification notification);

    }

    interface Presenter extends IBasePresenter,OnFinishDetailNotificationListener{
        void getDetail(String id);
    }

    interface Interactor {
        void doGetDetail(String id);
    }

    interface OnFinishDetailNotificationListener extends OnFinishListener {
        void onSuccessGetDetail(Notification notification);
    }
}
