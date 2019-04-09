package com.skynet.chovietship.ui.detailshop;

import com.skynet.chovietship.application.AppController;
import com.skynet.chovietship.models.Profile;
import com.skynet.chovietship.models.ShopDetail;
import com.skynet.chovietship.network.api.ApiResponse;
import com.skynet.chovietship.network.api.ApiService;
import com.skynet.chovietship.network.api.ApiUtil;
import com.skynet.chovietship.network.api.CallBackBase;
import com.skynet.chovietship.ui.base.Interactor;
import com.skynet.chovietship.utils.AppConstant;

import retrofit2.Call;
import retrofit2.Response;

public class DetailShopImplRemote extends Interactor implements DetailShopContract.Interactor {
    DetailShopContract.Listener listener;

    public DetailShopImplRemote(DetailShopContract.Listener listener) {
        this.listener = listener;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }


    @Override
    public void getShop(String id) {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            listener.onErrorAuthorization();
            return;
        }
        getmService().getDetailShop(profile.getId(), id).enqueue(new CallBackBase<ApiResponse<ShopDetail>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<ShopDetail>> call, Response<ApiResponse<ShopDetail>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        listener.onSucessGetShop(response.body().getData());
                    } else {
                        listener.onErrorApi(response.message());
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<ShopDetail>> call, Throwable t) {
                listener.onError("Đã có lỗi xảy ra");

            }
        });
    }

    @Override
    public void toggleFav(int id, boolean toggle) {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            listener.onErrorAuthorization();
            return;
        }
        getmService().toggleFavShop(profile.getId(), id, toggle ? 1 : 2).enqueue(new CallBackBase<ApiResponse>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse> call, Response<ApiResponse> response) {

            }

            @Override
            public void onRequestFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }
}
