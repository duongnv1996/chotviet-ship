package com.skynet.choviet.ui.auth.verifyaccount;


import com.skynet.choviet.models.Profile;
import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

public interface VerifyAccountContract {

    interface View extends BaseView {
        void onSuccessSignUp();

        void onSuccessSendCode(String code);

    }

    interface Presenter extends IBasePresenter, VerifyListener {
        void signUp(String phone, String password);

        void sendCode(String phone);

    }

    interface Interactor {
        void signUp(String phone, String password);

        void sendCodeTo(String phone);

    }

    interface VerifyListener extends OnFinishListener {
        void onSuccessSignUp(Profile profile);

        void onSuccessSendCode(String code);
    }

}
