package com.skynet.choviet.ui.listauction;

import com.skynet.choviet.application.AppController;
import com.skynet.choviet.models.Auction;
import com.skynet.choviet.models.Cart;
import com.skynet.choviet.models.ProductResponse;
import com.skynet.choviet.models.Profile;
import com.skynet.choviet.network.api.ApiResponse;
import com.skynet.choviet.network.api.ApiService;
import com.skynet.choviet.network.api.ApiUtil;
import com.skynet.choviet.network.api.CallBackBase;
import com.skynet.choviet.ui.base.Interactor;
import com.skynet.choviet.utils.AppConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ListProductImplRemote extends Interactor implements ListProductContract.Interactor {
    ListProductContract.Listener listener;

    public ListProductImplRemote(ListProductContract.Listener listener) {
        this.listener = listener;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }

    @Override
    public void getListProduct() {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            listener.onErrorAuthorization();
            return;
        }
        getmService().getListAuction(profile.getId()).enqueue(new CallBackBase<ApiResponse<List<Auction>>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<List<Auction>>> call, Response<ApiResponse<List<Auction>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        listener.onSucessGetListProduct(response.body().getData());
                    } else {
                        listener.onError(response.body().getMessage());
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<List<Auction>>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());
            }
        });
    }

    @Override
    public void setPrice(int idAuction, double price) {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            listener.onErrorAuthorization();
            return;
        }
        getmService().setPriceAuction(profile.getId(), idAuction, price).enqueue(new CallBackBase<ApiResponse>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse> call, Response<ApiResponse> response) {

            }

            @Override
            public void onRequestFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }


}