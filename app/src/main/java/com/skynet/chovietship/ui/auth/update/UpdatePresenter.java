package com.skynet.chovietship.ui.auth.update;


import java.io.File;

public class UpdatePresenter implements UpdateContract.Presenter {
    UpdateContract.View view;
    UpdateContract.Interactor interactor;

    public UpdatePresenter(UpdateContract.View view) {
        this.view = view;
        interactor = new UpdateRemoteImpl(this);
    }

    @Override
    public void getInfor() {
        view.showProgress();
        interactor.getInfor();
    }

    @Override
    public void update(File file, int type) {
        view.showProgress();
        interactor.update(file, type);
    }

    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void getInforSucess() {
        if (view == null) return;
        view.hiddenProgress();
        view.getInforSucess();
    }

    @Override
    public void uploadSucess(int type) {
        if (view == null) return;
        view.hiddenProgress();
        view.uploadSucess(type);
    }

    @Override
    public void uploadfail(int type) {
        if (view == null) return;
        view.hiddenProgress();
        view.uploadfail(type);
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
