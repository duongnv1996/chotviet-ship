package com.skynet.choviet.ui.shop;


import com.skynet.choviet.models.Category;
import com.skynet.choviet.models.Shop;
import com.skynet.choviet.models.ShopResponse;
import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

import java.util.List;

public interface ShopContract {
    interface View extends BaseView {
        void onSuccessGetListShop(List<Shop> list);
        void onSuccessGetListHotShop(List<Shop> list);
        void onSucessGetCategory(List<Category> categories);

    }

    interface Presenter extends IBasePresenter,Listener{
        void getListShop(int type);
        void getCategory();
    }

    interface Interactor {
        void getListShop(int type);
        void getCategory();

    }

    interface Listener extends OnFinishListener {
        void onSuccessGetListShop(ShopResponse response);
        void onSucessGetCategory(List<Category> categories);
    }
}
