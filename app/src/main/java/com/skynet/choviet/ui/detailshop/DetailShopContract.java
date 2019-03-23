package com.skynet.choviet.ui.detailshop;

import com.skynet.choviet.models.ShopDetail;
import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

public interface DetailShopContract {
    interface View extends BaseView {
        void onSucessGetShop(ShopDetail shopDetail);
    }

    interface Presenter extends IBasePresenter ,Listener{
        void getShop(String id);
        void toggleFav(int id,boolean toggle);
    }

    interface Interactor {
        void getShop(String id);
        void toggleFav(int id,boolean toggle);
    }

    interface Listener extends OnFinishListener {
        void onSucessGetShop(ShopDetail shopDetail);
    }
}
