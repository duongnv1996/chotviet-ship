package com.skynet.choviet.ui.DetailNews;


import com.skynet.choviet.application.AppController;
import com.skynet.choviet.models.Promotion;
import com.skynet.choviet.network.api.ApiResponse;
import com.skynet.choviet.network.api.ApiService;
import com.skynet.choviet.network.api.ApiUtil;
import com.skynet.choviet.network.api.CallBackBase;
import com.skynet.choviet.ui.base.Interactor;
import com.skynet.choviet.utils.AppConstant;

import retrofit2.Call;
import retrofit2.Response;

public class DetailNotificationRemoteImpl extends Interactor implements DetailNotificationContract.Interactor {
    DetailNotificationContract.OnFinishDetailNotificationListener listener;

    public DetailNotificationRemoteImpl(DetailNotificationContract.OnFinishDetailNotificationListener listener) {
        this.listener = listener;
    }

    @Override
    public void doGetDetail(String id) {
        if(AppController.getInstance().getmProfileUser() == null ){
            listener.onErrorAuthorization();
            return;
        }

        getmService().getDetailNotification(id,2,AppController.getInstance().getmProfileUser().getId()).enqueue(new CallBackBase<ApiResponse<Promotion>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<Promotion>> call, Response<ApiResponse<Promotion>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS && response.body().getData() != null) {

                        listener.onSuccessGetDetail(response.body().getData());
                    } else {
                        listener.onError(response.body().getMessage());
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<Promotion>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());

            }
        });
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }
}
