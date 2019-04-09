package com.skynet.chovietship.ui.auth.update;

import android.net.Uri;


import com.skynet.chovietship.application.AppController;
import com.skynet.chovietship.models.Profile;
import com.skynet.chovietship.network.api.ApiResponse;
import com.skynet.chovietship.network.api.ApiService;
import com.skynet.chovietship.network.api.ApiUtil;
import com.skynet.chovietship.network.api.CallBackBase;
import com.skynet.chovietship.ui.base.Interactor;
import com.skynet.chovietship.utils.AppConstant;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class UpdateRemoteImpl extends Interactor implements UpdateContract.Interactor {
    UpdateContract.UpdateListener listener;

    public UpdateRemoteImpl(UpdateContract.UpdateListener listener) {
        this.listener = listener;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }

    @Override
    public void getInfor() {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            listener.onErrorAuthorization();
            return;
        }
        getmService().getProfile(profile.getId(), AppConstant.TYPE_USER).enqueue(new CallBackBase<ApiResponse<Profile>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<Profile>> call, Response<ApiResponse<Profile>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS && response.body().getData() != null) {
                        AppController.getInstance().setmProfileUser(response.body().getData());
                        listener.getInforSucess();
                    } else {
                        listener.onErrorAuthorization();

                    }
                } else {
                    listener.onErrorAuthorization();
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<Profile>> call, Throwable t) {
                listener.onErrorAuthorization();


            }
        });
    }

    @Override
    public void update(File file, int type) {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            listener.onErrorAuthorization();
            return;
        }
        int typeAPI = 1;
        if (type == 1) {
            typeAPI = 3;
        }
        if (type == 2) typeAPI = 1;
        if (type == 3) typeAPI = 2;
        if (type == 8) typeAPI = 4;

        RequestBody idRequest = ApiUtil.createPartFromString(profile.getId());
        RequestBody typeRequest = ApiUtil.createPartFromString(String.valueOf(typeAPI));
        Map<String, RequestBody> map = new HashMap<>();
        map.put("shiper_id", idRequest);
        map.put("type", typeRequest);
        getmService().uploadProfile(ApiUtil.prepareFilePart("img", Uri.fromFile(file)), map).enqueue(new CallBackBase<ApiResponse>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        listener.uploadSucess(type);
                    } else {
                        listener.onError(response.body().getMessage());
                        listener.uploadfail(type);
                    }
                } else {
                    listener.onError(response.message());
                    listener.uploadfail(type);

                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse> call, Throwable t) {
                listener.onErrorApi(t.getMessage());
                listener.uploadfail(type);

            }
        });
    }
}
