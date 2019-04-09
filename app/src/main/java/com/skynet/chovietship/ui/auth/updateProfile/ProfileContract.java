package com.skynet.chovietship.ui.auth.updateProfile;




import com.skynet.chovietship.models.Address;
import com.skynet.chovietship.models.Profile;
import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;

public interface ProfileContract {
    interface View extends BaseView {
        void onSuccessGetInfor();
        void onSuccessUpdatedAvatar();
        void onSuccessUpdate();
        void onSucessGetCity(List<Address> listCity);
        void onSucessGetDistrcits(List<Address> listDistricts);

    }
    interface Presenter extends IBasePresenter,OnFinishProfileListener{
        void getInfor();
        void uploadAvatar(File file);
        void update(String name, String email, String password,String inviteCode);
        void getCity();
        void getDistrict(int city);

    }

    interface Interactor {
        void doGetInfor(String profileInfor);
        void doUpdateAvatar(File file, MultipartBody.Part part);
        void update(String name, String email, String password,String inviteCode);
        void getCity();
        void getDistrict(int city);

    }

    interface OnFinishProfileListener extends OnFinishListener {
        void getInforSuccess(Profile profile);
        void notFoundInfor();
        void onSuccessUpdate();
        void onSuccessUpdatedAvatar();
        void onSucessGetCity(List<Address> listCity);
        void onSucessGetDistrcits(List<Address> listDistricts);
    }
}
