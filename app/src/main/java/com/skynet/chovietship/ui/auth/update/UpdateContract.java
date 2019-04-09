package com.skynet.chovietship.ui.auth.update;


import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

import java.io.File;

public interface UpdateContract {
    interface View extends BaseView {
        void getInforSucess();
        void uploadSucess(int type);
        void uploadfail(int type);
    }
    interface Presenter extends IBasePresenter,UpdateListener{
        void getInfor();
        void update(File file, int type);
    }
    interface Interactor {
        void getInfor();
        void update(File file, int type);

    }
    interface UpdateListener extends OnFinishListener {
        void getInforSucess();
        void uploadSucess(int type);
        void uploadfail(int type);
    }
}
