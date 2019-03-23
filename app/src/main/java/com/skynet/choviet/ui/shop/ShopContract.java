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
        void onSuccessGetListFriendShop(List<Shop> list);
        void onSuccessGetListNearbyShop(List<Shop> list);
    }

    interface Presenter extends IBasePresenter ,Listener{
        void getListShop(int type);
        void getListFriend();
        void getListShopNearby(double lat,double lng);
    }

    interface Interactor {
        void getListShop(int type);
        void getListFriend(int type);
        void getListShopNearby(double lat,double lng);

    }

    interface Listener extends OnFinishListener {
        void onSuccessGetListShop(List<Shop> response);
    }
}
