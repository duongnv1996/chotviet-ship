package com.skynet.choviet.ui.auth.updateProfile;

import android.net.Uri;

import com.skynet.choviet.application.AppController;
import com.skynet.choviet.models.Address;
import com.skynet.choviet.models.Profile;
import com.skynet.choviet.network.api.ApiUtil;
import com.skynet.choviet.utils.AppConstant;

import java.io.File;
import java.util.List;

public class ProfilePresenter implements ProfileContract.Presenter {
    ProfileContract.View view;
    ProfileContract.Interactor interactor;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
        interactor = new ProfileRemoteImpl(this);
    }



    @Override
    public void getInfor() {
        view.showProgress();
        String profileStr = AppController.getInstance().getmSetting().getString(AppConstant.KEY_PROFILE, "");
        if (profileStr.isEmpty()) {
            view.onError("not found");
        } else {
            view.showProgress();
            interactor.doGetInfor(profileStr);
        }
    }


    @Override
    public void uploadAvatar(File file) {
        view.showProgress();
        interactor.doUpdateAvatar(file, ApiUtil.prepareFilePart("img", Uri.fromFile(file)));
    }



    @Override
    public void update(String name,String email, String phone, int idCity, int idDist) {
        if(view == null) return;
        if(name.isEmpty() || email.isEmpty() || phone.isEmpty()){
            onError("Vui lòng điền đầy đủ thông tin");
            return;
        }
        view.showProgress();
        interactor.update(name, email, phone,idCity,idDist);
    }

    @Override
    public void getCity() {
        interactor.getCity();
    }

    @Override
    public void getDistrict(int city) {
            interactor.getDistrict(city);
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSucessGetCity(List<Address> listCity) {
        if(view == null) return;
        view.hiddenProgress();
        view.onSucessGetCity(listCity);
    }

    @Override
    public void onSucessGetDistrcits(List<Address> listDistricts) {
        if(view == null) return;
        view.hiddenProgress();
        view.onSucessGetDistrcits(listDistricts);
    }

    @Override
    public void onSuccessUpdate() {
        if (view == null) return;
        view.hiddenProgress();
        view.onSuccessUpdate();
    }

    @Override
    public void getInforSuccess(Profile profile) {
        if (view == null) return;
        view.hiddenProgress();
        AppController.getInstance().setmProfileUser(profile);
        view.onSuccessGetInfor();
    }

    @Override
    public void notFoundInfor() {
        if (view == null) return;
        view.hiddenProgress();
        view.onErrorAuthorization();
    }

    @Override
    public void onSuccessUpdatedAvatar() {
        if (view == null) return;
        view.hiddenProgress();
        view.onSuccessUpdatedAvatar();
    }

    @Override
    public void onErrorApi(String message) {
        if (view == null) return;
        view.hiddenProgress();
        view.onErrorApi(message);
    }

    @Override
    public void onError(String message) {
        if (view == null) return;
        view.hiddenProgress();
        view.onError(message);

    }

    @Override
    public void onErrorAuthorization() {
        if (view == null) return;
        view.hiddenProgress();
        view.onErrorAuthorization();
    }
}
