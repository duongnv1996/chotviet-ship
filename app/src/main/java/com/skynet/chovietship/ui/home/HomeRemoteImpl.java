package com.skynet.chovietship.ui.home;

import com.google.gson.Gson;
import com.skynet.chovietship.application.AppController;
import com.skynet.chovietship.models.HomeResponse;
import com.skynet.chovietship.models.Profile;
import com.skynet.chovietship.network.api.ApiResponse;
import com.skynet.chovietship.network.api.ApiService;
import com.skynet.chovietship.network.api.ApiUtil;
import com.skynet.chovietship.network.api.CallBackBase;
import com.skynet.chovietship.ui.base.Interactor;
import com.skynet.chovietship.utils.AppConstant;

import retrofit2.Call;
import retrofit2.Response;

public class HomeRemoteImpl extends Interactor implements HomeContract.Interactor {
    HomeContract.Listener presenter;

    public HomeRemoteImpl(HomeContract.Listener presenter) {
        this.presenter = presenter;
    }

    @Override
    public ApiService createService() {
        return ApiUtil.createNotTokenApi();
    }

    @Override
    public void doGetInfor(String idUser) {
        Profile profile = new Gson().fromJson(idUser, Profile.class);
        if (profile == null) {
            presenter.onErrorAuthorization();
            return;
        }
        getmService().getProfile(profile.getId(), AppConstant.TYPE_USER).enqueue(new CallBackBase<ApiResponse<Profile>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<Profile>> call, final Response<ApiResponse<Profile>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS && response.body().getData() != null) {
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                presenter.onSuccessGetInfor(response.body().getData());
//                                AppController.getInstance().setListBanner(response.body().getData().getBanners());
                            }
                        }, 2000);
                    } else {
                        presenter.onErrorAuthorization();
                    }
                } else {
                    presenter.onErrorAuthorization();
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<Profile>> call, Throwable t) {
                presenter.onErrorAuthorization();

            }
        });
    }

    @Override
    public void getListProduct(int id) {
//        Profile profile = AppController.getInstance().getmProfileUser();
//        if (profile == null) {
//            presenter.onErrorAuthorization();
//            return;
//        }
//        getmService().getListCombo(profile.getId()).enqueue(new CallBackBase<ApiResponse<List<Combo>>>() {
//            @Override
//            public void onRequestSuccess(Call<ApiResponse<List<Combo>>> call, Response<ApiResponse<List<Combo>>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS) {
//                        presenter.onSucessGetListProduct(response.body().getData());
//                    } else {
//                        presenter.onError(response.body().getMessage());
//                    }
//                } else {
//                    presenter.onError(response.message());
//                }
//            }
//
//            @Override
//            public void onRequestFailure(Call<ApiResponse<List<Combo>>> call, Throwable t) {
//                presenter.onErrorApi(t.getMessage());
//            }
//        });
    }

    @Override
    public void getHome() {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            presenter.onErrorAuthorization();
            return;
        }
        getmService().getHome(profile.getId()).enqueue(new CallBackBase<ApiResponse<HomeResponse>>() {
            @Override
            public void onRequestSuccess(Call<ApiResponse<HomeResponse>> call, final Response<ApiResponse<HomeResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getCode() == AppConstant.CODE_API_SUCCESS && response.body().getData() != null) {
                        presenter.onSucessGetHome(response.body().getData());
                    } else {
                        presenter.onErrorAuthorization();
                    }
                } else {
                    presenter.onErrorAuthorization();
                }
            }

            @Override
            public void onRequestFailure(Call<ApiResponse<HomeResponse>> call, Throwable t) {
                presenter.onErrorAuthorization();

            }
        });
    }

    @Override
    public void bookASeatAuction(int idAuction, double price) {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile == null) {
            presenter.onErrorAuthorization();
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
