package com.skynet.choviet.ui.home;


import com.skynet.choviet.models.Auction;
import com.skynet.choviet.models.Banner;
import com.skynet.choviet.models.Category;
import com.skynet.choviet.models.Combo;
import com.skynet.choviet.models.HomeResponse;
import com.skynet.choviet.models.News;
import com.skynet.choviet.models.Product;
import com.skynet.choviet.models.ProductResponse;
import com.skynet.choviet.models.Profile;
import com.skynet.choviet.models.Shop;
import com.skynet.choviet.models.Suggestion;
import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

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

    }

    interface Interactor {
        void doGetInfor(String profileInfor);
        void getListProduct(int id);
        void getHome();

    }

    interface Listener extends OnFinishListener {
        void onSuccessGetInfor(Profile profile);
        void onSucessGetHome(HomeResponse response);
    }
}
