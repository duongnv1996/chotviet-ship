package com.skynet.chovietship.ui.home;


import com.skynet.chovietship.models.Auction;
import com.skynet.chovietship.models.Banner;
import com.skynet.chovietship.models.HomeResponse;
import com.skynet.chovietship.models.Product;
import com.skynet.chovietship.models.Profile;
import com.skynet.chovietship.models.Shop;
import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView {
        void onSuccessGetInfor();
        void onSucessGetBanner(List<Banner> list);
        void onSucessGetRecommend(List<Product> list);
        void onSucessGetSuggestion(List<Product> list);
        void onSucessGetShops(List<Shop> list);
        void onSucessGetAuction(List<Auction> list);
    }

    interface PresenterI extends IBasePresenter, Listener {
        void getInfor();
        void getHome();
        void getListProduct(int id);
        void bookASeatAuction(int idAuction, double price);
    }

    interface Interactor {
        void doGetInfor(String profileInfor);
        void getListProduct(int id);
        void getHome();
        void bookASeatAuction(int idAuction, double price);

    }

    interface Listener extends OnFinishListener {
        void onSuccessGetInfor(Profile profile);
        void onSucessGetHome(HomeResponse response);
    }
}
