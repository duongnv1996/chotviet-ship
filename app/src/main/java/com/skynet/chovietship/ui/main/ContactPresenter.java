package com.skynet.chovietship.ui.main;

import com.skynet.chovietship.models.Notification;
import com.skynet.chovietship.ui.base.Presenter;

import java.util.List;


public class ContactPresenter extends Presenter<ContactContract.View> implements ContactContract.Presenter {
    ContactContract.Interactor interactor;

    public ContactPresenter(ContactContract.View view) {
        super(view);
        interactor = new ContactImplRemote(this);
    }


    @Override
    public void updateToken() {
        interactor.updateToken();
    }

    @Override
    public void updateLatlng(double lat, double lng) {
        interactor.updateLatlng(lat,lng);
    }

    @Override
    public void getNoti() {
        interactor.getNoti();

    }


    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onErrorApi(String message) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onErrorApi(message);
        }
    }

    @Override
    public void onError(String message) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onError(message);
        }
    }

    @Override
    public void onErrorAuthorization() {
        if (isAvaliableView()) {
            view.hiddenProgress();
            view.onErrorAuthorization();
        }
    }


    @Override
    public void onSucessGetNotification(List<Notification> list) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            if(list!=null && !list.isEmpty())
            view.onSucessGetNotification(list);
        }
    }
}
