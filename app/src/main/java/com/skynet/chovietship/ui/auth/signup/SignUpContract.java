package com.skynet.chovietship.ui.auth.signup;


import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;

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
