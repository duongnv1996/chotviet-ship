package com.skynet.choviet.ui.home;


import com.skynet.choviet.application.AppController;
import com.skynet.choviet.models.Combo;
import com.skynet.choviet.models.HomeResponse;
import com.skynet.choviet.models.ProductResponse;
import com.skynet.choviet.models.Profile;
import com.skynet.choviet.utils.AppConstant;

import java.util.List;

public class HomePresenterI implements HomeContract.PresenterI {
    HomeContract.View view;
    HomeContract.Interactor interactor;

    public HomePresenterI(HomeContract.View view) {
        this.view = view;
        interactor = new HomeRemoteImpl(this);
    }

    @Override
    public void getInfor() {

        String profileStr = AppController.getInstance().getmSetting().getString(AppConstant.KEY_PROFILE, "");
        if (profileStr.isEmpty()) {
            view.onError("not found");
        } else {
            view.showProgress();
            interactor.doGetInfor(profileStr);
        }

    }

    @Override
    public void getHome() {
        if (view == null) return;
        view.showProgress();
        interactor.getHome();
    }

    @Override
    public void getListProduct(int id) {
        if (view == null) return;
        interactor.getListProduct(id);
    }

    @Override
    public void onSuccessGetInfor(Profile profile) {
        if (view == null) return;
        view.hiddenProgress();
        AppController.getInstance().setmProfileUser(profile);
        view.onSuccessGetInfor();
    }


    @Override
    public void onSucessGetHome(HomeResponse response) {
        if (view == null) return;
        view.hiddenProgress();
        if (response.getBanners() != null && !response.getBanners().isEmpty())
            view.onSucessGetBanner(response.getBanners());

        if (response.getAuction() != null && !response.getAuction().isEmpty())
            view.onSucessGetAuction(response.getAuction());

        if (response.getHot_product() != null && !response.getHot_product().isEmpty())
            view.onSucessGetRecommend(response.getHot_product());

        if (response.getList_shop() != null && !response.getList_shop().isEmpty())
            view.onSucessGetShops(response.getList_shop());

        if (response.getSuggest() != null && !response.getSuggest().isEmpty())
            view.onSucessGetSuggestion(response.getSuggest());


    }


    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onErrorApi(String message) {
        if (view == null) return;
        view.hiddenProgress();
        view.onError("not found");

    }

    @Override
    public void onError(String message) {
        if (view == null) return;
        view.hiddenProgress();
        view.onError("not found");

    }

    @Override
    public void onErrorAuthorization() {
        if (view == null) return;
        view.hiddenProgress();
        view.onError("not found");

    }


}
