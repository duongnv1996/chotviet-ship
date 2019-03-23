package com.skynet.choviet.ui.auth.signup;


import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;

public interface SignUpContract {
    interface View extends BaseView {
        void signUpSuccess(String code);

    }

    interface Presenter extends IBasePresenter {
        void signUp(String phone    );

        void signUpSuccess(String code);



    }

    interface Interactor {
        void doSignUp(String phone);

    }
}
