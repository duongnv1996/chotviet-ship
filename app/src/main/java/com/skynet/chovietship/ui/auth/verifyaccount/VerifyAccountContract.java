package com.skynet.chovietship.ui.auth.verifyaccount;


import com.skynet.chovietship.models.Profile;
import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

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
