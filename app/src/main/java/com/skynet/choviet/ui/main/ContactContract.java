package com.skynet.choviet.ui.main;

import android.content.ContentResolver;

import com.skynet.choviet.models.Notification;
import com.skynet.choviet.models.Profile;
import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

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
