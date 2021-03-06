package com.skynet.chovietship.ui.detailhistory;

import com.skynet.chovietship.models.History;
import com.skynet.chovietship.network.api.ApiResponse;
import com.skynet.chovietship.network.api.ApiService;
import com.skynet.chovietship.network.api.ApiUtil;
import com.skynet.chovietship.network.api.CallBackBase;
import com.skynet.chovietship.ui.base.Interactor;
import com.skynet.chovietship.utils.AppConstant;

import retrofit2.Call;
import retrofit2.Response;

public class HistoryImplRemote extends Interactor implements HistoryContract.Interactor {
    HistoryContract.Listener listener;

    public HistoryImplRemote(HistoryContract.Listener listener) {
        this.listener = listener;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }


    @Override
    public void getHistory(int id) {
        getmService().getHistory(id).enqueue(new CallBackBase<ApiResponse<History>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<History>> call, Response<ApiResponse<History>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        listener.onSucessGetCart(response.body().getData());
                    } else {
                        listener.onError(response.message());
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<History>> call, Throwable t) {
                listener.onErrorApi(t.getMessage());

            }
        });
    }

    @Override
    public void cancle(int id, int active) {
        getmService().cancelBooking(id, active).enqueue(new CallBackBase<ApiResponse>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        getHistory(id);
                        listener.onSucessCancel();
                    } else {
                        listener.onError(response.message());
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse> call, Throwable t) {
                listener.onErrorApi(t.getMessage());

            }
        });
    }
}
