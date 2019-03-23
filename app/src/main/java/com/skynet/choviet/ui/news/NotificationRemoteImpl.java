package com.skynet.choviet.ui.news;


import com.skynet.choviet.models.Promotion;
import com.skynet.choviet.network.api.ApiResponse;
import com.skynet.choviet.network.api.ApiService;
import com.skynet.choviet.network.api.ApiUtil;
import com.skynet.choviet.network.api.CallBackBase;
import com.skynet.choviet.ui.base.Interactor;
import com.skynet.choviet.utils.AppConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class NotificationRemoteImpl extends Interactor implements NotificationContract.Interactor {
    NotificationContract.Presenter presenter;

    public NotificationRemoteImpl(NotificationContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }



    @Override
    public void doGetAllService(String idShop) {
        getmService().getListNotification(idShop, 2).enqueue(new CallBackBase<ApiResponse<List<Promotion>>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<List<Promotion>>> call, Response<ApiResponse<List<Promotion>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS && response.body().getData() != null) {

                        presenter.onSuccessGetServices(response.body().getData());
                    } else {
                        presenter.onError(response.body().getMessage());
                    }
                } else {
                    presenter.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<List<Promotion>>> call, Throwable t) {
                presenter.onErrorApi(t.getMessage());
            }
        });
    }
}
