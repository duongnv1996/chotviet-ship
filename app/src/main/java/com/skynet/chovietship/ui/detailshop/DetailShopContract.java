package com.skynet.chovietship.ui.detailshop;

import com.skynet.chovietship.models.ShopDetail;
import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

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
