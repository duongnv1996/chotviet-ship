package com.skynet.chovietship.ui.category.listProductbycateogry;

import com.skynet.chovietship.application.AppController;
import com.skynet.chovietship.models.Cart;
import com.skynet.chovietship.models.ProductResponse;
import com.skynet.chovietship.models.Profile;
import com.skynet.chovietship.network.api.ApiResponse;
import com.skynet.chovietship.network.api.ApiService;
import com.skynet.chovietship.network.api.ApiUtil;
import com.skynet.chovietship.network.api.CallBackBase;
import com.skynet.chovietship.ui.base.Interactor;
import com.skynet.chovietship.utils.AppConstant;

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
    public void getListProduct(int index,int idCate) {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            listener.onErrorAuthorization();
            return;
        }
        getmService().getListProductCategory(profile.getId(), index,idCate).enqueue(new CallBackBase<ApiResponse<ProductResponse>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<ProductResponse>> call, Response<ApiResponse<ProductResponse>> response) {
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
            public void onRequestFailure(Call<ApiResponse<ProductResponse>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());
            }
        });
    }

    @Override
    public void getCart() {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            listener.onErrorAuthorization();
            return;
        }
        getmService().getCart(profile.getId()).enqueue(new CallBackBase<ApiResponse<Cart>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<Cart>> call, Response<ApiResponse<Cart>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        AppController.getInstance().setCart(response.body().getData());
                        listener.onSucessGetCart(response.body().getData());
                    } else {
                        listener.onError(response.message());
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<Cart>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());

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
        getmService().toggleFavProduct(profile.getId(), id, toggle ? 1 : 2).enqueue(new CallBackBase<ApiResponse>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse> call, Response<ApiResponse> response) {

            }

            @Override
            public void onRequestFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }
}