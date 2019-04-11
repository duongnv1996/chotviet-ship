package com.skynet.chovietship.ui.wallet;

import com.skynet.chovietship.models.Wallet;

import java.util.List;

public class WalletPresenter implements WalletContract.Presenter {
    WalletContract.View view;

    public WalletPresenter(WalletContract.View view) {
        this.view = view;
        interactor = new WalletRemoteImpl(this);
    }

    WalletContract.Interactor interactor;

    @Override
    public void getWallet() {
        if (view == null) return;
        view.showProgress();
        interactor.getWallet();
    }



    @Override
    public void onDestroyView() {
        view = null;
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

    @Override
    public void onSuccessGetWallet(Wallet wallet) {
        if(view == null) return;
        view.hiddenProgress();
        view.onSuccessGetWallet(wallet);
    }
}
