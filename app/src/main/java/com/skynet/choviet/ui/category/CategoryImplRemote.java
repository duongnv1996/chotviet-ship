package com.skynet.choviet.ui.category;

import com.skynet.choviet.application.AppController;
import com.skynet.choviet.models.Category;
import com.skynet.choviet.models.Profile;
import com.skynet.choviet.models.ShopResponse;
import com.skynet.choviet.network.api.ApiResponse;
import com.skynet.choviet.network.api.ApiService;
import com.skynet.choviet.network.api.ApiUtil;
import com.skynet.choviet.network.api.CallBackBase;
import com.skynet.choviet.ui.base.Interactor;
import com.skynet.choviet.ui.shop.ShopContract;
import com.skynet.choviet.utils.AppConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CategoryImplRemote extends Interactor implements CategoryContract.Interactor {
    CategoryContract.Listener listener;

    public CategoryImplRemote(CategoryContract.Listener listener) {
        this.listener = listener;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }



    @Override
    public void getCategory() {
        getmService().getListCategory().enqueue(new CallBackBase<ApiResponse<List<Category>>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<List<Category>>> call, Response<ApiResponse<List<Category>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        listener.onSucessGetCategory(response.body().getData());
                    } else {
                        listener.onErrorApi(response.message());
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<List<Category>>> call, Throwable t) {

            }
        });
    }
}
