package com.skynet.chovietship.ui.market;

import com.skynet.chovietship.models.Market;
import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

import java.util.List;

public interface ListProductContract {
    interface View extends BaseView {
        void onSucessGetListProduct(List<Market> list);


    }

    interface Presenter extends IBasePresenter, Listener {
        void getListProduct(double lat, double lng);
    }

    interface Interactor {
        void getListProduct(double lat, double lng);
    }

    interface Listener extends OnFinishListener {
        void onSucessGetListProduct(List<Market> list );


    }
}
