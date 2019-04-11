package com.skynet.chovietship.ui.wallet;


import com.skynet.chovietship.application.AppController;
import com.skynet.chovietship.models.Profile;
import com.skynet.chovietship.models.Wallet;
import com.skynet.chovietship.network.api.ApiResponse;
import com.skynet.chovietship.network.api.ApiService;
import com.skynet.chovietship.network.api.ApiUtil;
import com.skynet.chovietship.network.api.CallBackBase;
import com.skynet.chovietship.ui.base.Interactor;
import com.skynet.chovietship.utils.AppConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class WalletRemoteImpl extends Interactor implements WalletContract.Interactor{
    WalletContract.WalletListener listener;

    public WalletRemoteImpl(WalletContract.WalletListener listener) {
        this.listener = listener;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }

    @Override
    public void getWallet() {
        Profile profile = AppController.getInstance().getmProfileUser();
        if(profile == null) {
          listener.onErrorAuthorization();
          return;
        }
        getmService().getWallet(profile.getId()).enqueue(new CallBackBase<ApiResponse<Wallet>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<Wallet>> call, Response<ApiResponse<Wallet>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        listener.onSuccessGetWallet(response.body().getData());
                    } else {
                        listener.onError(response.body().getMessage());
                    }
                } else {
                    listener.onErrorApi(response.message());

                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<Wallet>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());

            }
        });
    }

}
