package com.skynet.choviet.ui.enterpin;

import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

public interface PurchaseContract {
    interface View extends BaseView {
        void onSucessBooking();
        void onSucessSendPin(String code);

    }

    interface Presenter extends IBasePresenter ,Listener{
        void book();
        void sendPin();
    }

    interface Interactor {
        void book();
        void sendPin();

    }

    interface Listener extends OnFinishListener {
        void onSucessBooking();
        void onSucessSendPin(String code);

    }
}
