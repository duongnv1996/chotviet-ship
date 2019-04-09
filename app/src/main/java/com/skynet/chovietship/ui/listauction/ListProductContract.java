package com.skynet.chovietship.ui.listauction;

import com.skynet.chovietship.models.Auction;
import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

import java.util.List;

public interface ListProductContract {
    interface View extends BaseView {
        void onSucessGetListProduct(List<Auction> list);

    }

    interface Presenter extends IBasePresenter ,Listener{
        void getListProduct();
        void setPrice(int idAuction,double price);
    }

    interface Interactor {
        void getListProduct();
        void setPrice(int idAuction,double price);

    }

    interface Listener extends OnFinishListener {
        void onSucessGetListProduct(List<Auction> list);

    }
}
