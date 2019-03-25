package com.skynet.choviet.ui.listauction;

import com.skynet.choviet.models.Auction;
import com.skynet.choviet.models.Cart;
import com.skynet.choviet.models.Product;
import com.skynet.choviet.models.ProductResponse;
import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

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
