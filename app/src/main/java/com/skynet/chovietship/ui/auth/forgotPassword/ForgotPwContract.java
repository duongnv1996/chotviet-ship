package com.skynet.chovietship.ui.auth.forgotPassword;


import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;

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
