package com.skynet.chovietship.ui.location;

import com.google.android.gms.maps.model.LatLng;
import com.skynet.chovietship.models.MyPlace;
import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

public interface LocationContract {
    interface View extends BaseView {
        void onSuccessGetMyAddress(MyPlace response);
    }

    interface Presenter extends IBasePresenter, Listener {
        void getMyAddress(LatLng latLng);
    }

    interface Interactor {
        void getMyAddress(LatLng latLng);

    }

    interface Listener extends OnFinishListener {
        void onSuccessGetMyAddress(MyPlace response);
    }
}
