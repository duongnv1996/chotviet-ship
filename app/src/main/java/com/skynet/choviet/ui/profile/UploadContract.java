package com.skynet.choviet.ui.profile;

import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

import java.io.File;

import okhttp3.MultipartBody;

public interface UploadContract {

    interface Presenter extends IBasePresenter,Listener {
        void getInfor();
        void upload(File file, int type);
    }

    interface Interactor {

        void getInfor();
        void upload(File file, MultipartBody.Part part, int type);
    }

    interface View extends BaseView{

        void onSuccessGetInfor();
        void onSucessUploadAvat();

    }
    interface Listener extends OnFinishListener {

        void onSuccessGetInfor();
        void onSucessUploadAvat();
    }

}
