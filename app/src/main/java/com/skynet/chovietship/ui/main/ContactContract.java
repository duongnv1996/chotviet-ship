package com.skynet.chovietship.ui.main;

import com.skynet.chovietship.models.History;
import com.skynet.chovietship.models.Notification;
import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

import java.util.List;

public interface ContactContract {
    interface View extends BaseView {
        void onSucessGetNotification(List<Notification> list);

    }

    interface Presenter extends IBasePresenter ,Listener{
        void updateToken();
        void updateLatlng(double lat,double lng);
        void getNoti();

    }

    interface Interactor {
        void updateToken();
        void updateLatlng(double lat,double lng);
        void getNoti();

    }

    interface Listener extends OnFinishListener {
        void onSucessGetNotification(List<Notification> list);
    }
}
