package com.skynet.chovietship.ui.Notification;


import com.skynet.chovietship.models.Notification;
import com.skynet.chovietship.network.api.ApiResponse;
import com.skynet.chovietship.network.api.ApiService;
import com.skynet.chovietship.network.api.ApiUtil;
import com.skynet.chovietship.network.api.CallBackBase;
import com.skynet.chovietship.ui.base.Interactor;
import com.skynet.chovietship.utils.AppConstant;

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
        getmService().getNotification(idShop, AppConstant.TYPE_USER).enqueue(new CallBackBase<ApiResponse<List<Notification>>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<List<Notification>>> call, Response<ApiResponse<List<Notification>>> response) {
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
            public void onRequestFailure(Call<ApiResponse<List<Notification>>> call, Throwable t) {
                presenter.onErrorApi(t.getMessage());
            }
        });
    }
}