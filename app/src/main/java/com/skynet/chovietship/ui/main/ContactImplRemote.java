package com.skynet.chovietship.ui.main;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.skynet.chovietship.application.AppController;
import com.skynet.chovietship.models.Notification;
import com.skynet.chovietship.models.Profile;
import com.skynet.chovietship.network.api.ApiResponse;
import com.skynet.chovietship.network.api.ApiService;
import com.skynet.chovietship.network.api.ApiUtil;
import com.skynet.chovietship.network.api.CallBackBase;
import com.skynet.chovietship.ui.base.Interactor;
import com.skynet.chovietship.utils.AppConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ContactImplRemote extends Interactor implements ContactContract.Interactor {
    ContactContract.Listener listener;

    public ContactImplRemote(ContactContract.Listener listener) {
        this.listener = listener;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }



    @Override
    public void updateToken() {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            listener.onErrorAuthorization();
            return;
        }
        LogUtils.e("update token firebase ",AppController.getInstance().getmSetting().getString(AppConstant.KEY_TOKEN_FCM));
        getmService().updateFCM(profile.getU_id(), AppController.getInstance().getmSetting().getString(AppConstant.KEY_TOKEN_FCM),AppConstant.TYPE_USER).enqueue(new CallBackBase<ApiResponse>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse> call, Response<ApiResponse> response) {

            }

            @Override
            public void onRequestFailure(Call<ApiResponse> call, Throwable t) {
                listener.onErrorApi(t.getMessage());
            }
        });
    }

    @Override
    public void updateLatlng(double lat, double lng) {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            listener.onErrorAuthorization();
            return;
        }

        getmService().updateLatlng(profile.getU_id(), PhoneUtils.getIMEI(),AppConstant.TYPE_USER,lat,lng).enqueue(new CallBackBase<ApiResponse>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse> call, Response<ApiResponse> response) {

            }

            @Override
            public void onRequestFailure(Call<ApiResponse> call, Throwable t) {
                listener.onErrorApi(t.getMessage());
            }
        });
    }

    @Override
    public void getNoti() {
        getmService().getNotificationDaily().enqueue(new CallBackBase<ApiResponse<List<Notification>>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<List<Notification>>> call, Response<ApiResponse<List<Notification>>> response) {
                if(response.isSuccessful() && response.body().getData()!=null){
                    listener.onSucessGetNotification(response.body().getData());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<List<Notification>>> call, Throwable t) {

            }
        });
    }


}
