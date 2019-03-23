package com.skynet.choviet.ui.auth.signup;


import com.skynet.choviet.network.api.ApiResponse;
import com.skynet.choviet.network.api.ApiService;
import com.skynet.choviet.network.api.ApiUtil;
import com.skynet.choviet.network.api.CallBackBase;
import com.skynet.choviet.ui.base.Interactor;
import com.skynet.choviet.utils.AppConstant;

import retrofit2.Call;
import retrofit2.Response;

public class SignUpRemoteImpl extends Interactor implements SignUpContract.Interactor {
    SignUpContract.Presenter presenter;

    public SignUpRemoteImpl(SignUpContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void doSignUp( String phone) {
        getmService().sendCode( phone,AppConstant.TYPE_USER).enqueue(new CallBackBase<ApiResponse<String>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
                        presenter.signUpSuccess(response.body().getData());
                    } else {
                        presenter.onError(response.body().getMessage());
                    }
                } else {
                    presenter.onError(response.message());
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<String>> call, Throwable t) {
                presenter.onErrorApi(t.getMessage());
            }
        });
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }
}
