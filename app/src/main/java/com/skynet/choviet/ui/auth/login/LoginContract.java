package com.skynet.choviet.ui.auth.login;


import com.skynet.choviet.models.Profile;
import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

public interface LoginContract  {
    interface View extends BaseView {
        void onSuccessLogin(Profile profile);
        void onSuccesLoginFacebook(Profile profile);

    }

    interface PresenterI extends IBasePresenter,OnFinishListener {
        void login(String username, String password, int type);
        void onSuccessLogin(Profile profile);
    }

    interface Interactor {
        void doLogin(String username, String password, int type);
        void doLoginGGG(String idGG, String email, String name);

    }
}
