package com.skynet.choviet.ui.market;

import com.skynet.choviet.models.Cart;
import com.skynet.choviet.models.Market;
import com.skynet.choviet.models.Product;
import com.skynet.choviet.models.ProductResponse;
import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

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
