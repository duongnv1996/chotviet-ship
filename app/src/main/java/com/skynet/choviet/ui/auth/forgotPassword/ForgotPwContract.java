package com.skynet.choviet.ui.auth.forgotPassword;


import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;

public interface ForgotPwContract {
    interface View extends BaseView {
        void signUpSuccess();
    }

    interface Presenter extends IBasePresenter{
        void signUp(String email, int type);
        void signUpSuccess();
    }

    interface Interactor {
        void doSignUp(String email, int type);
    }
}
