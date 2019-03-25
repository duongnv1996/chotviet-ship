package com.skynet.choviet.ui.listauction;

import com.skynet.choviet.models.Auction;
import com.skynet.choviet.models.Cart;
import com.skynet.choviet.models.ProductResponse;
import com.skynet.choviet.ui.base.Presenter;

import java.util.List;

public class ListProductPresenter extends Presenter<ListProductContract.View> implements ListProductContract.Presenter {
    ListProductContract.Interactor interactor;

    public ListProductPresenter(ListProductContract.View view) {
        super(view);
        interactor = new ListProductImplRemote(this);
    }



    @Override
    public void onDestroyView() {
        view = null;
    }

    @Override
    public void onSucessGetListProduct(List<Auction> response) {
        if (isAvaliableView()) {
            view.hiddenProgress();
            if (response != null) {
                view.onSucessGetListProduct(response);
            }
        }
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
    public void getListProduct() {
        if (isAvaliableView()) {
            view.showProgress();
            interactor.getListProduct();
        }
    }

    @Override
    public void setPrice(int idAuction, double price) {
        interactor.setPrice(idAuction, price);
    }
}
