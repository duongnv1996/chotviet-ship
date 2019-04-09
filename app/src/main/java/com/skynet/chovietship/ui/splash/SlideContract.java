package com.skynet.chovietship.ui.splash;


import com.skynet.chovietship.models.Profile;
import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

public interface SlideContract  {
    interface View extends BaseView {
        void onSuccessGetInfor();

    }
    interface PresenterI extends IBasePresenter,OnFinishListener {
       void getInfor();
       void getInforSuccess(Profile profile);
       void notFoundInfor();
    }

    interface Interactor {
        void doGetInfor(String profileInfor);
    }
}
