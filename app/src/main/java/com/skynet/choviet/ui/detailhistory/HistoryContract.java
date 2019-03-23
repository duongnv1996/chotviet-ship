package com.skynet.choviet.ui.detailhistory;

import com.skynet.choviet.models.History;
import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

public interface HistoryContract {
    interface View extends BaseView {
        void onSucessGetCart(History history);
        void onSucessCancel();

    }
    interface Presenter extends IBasePresenter,Listener{
        void getHistory(int id);
        void cancle(int id);
    }
    interface Interactor{
        void getHistory(int id);
        void cancle(int id);

    }
    interface Listener extends OnFinishListener{
        void onSucessGetCart(History history);
        void onSucessCancel();
    }
}
