package com.skynet.choviet.ui.location;

import com.google.android.gms.maps.model.LatLng;
import com.skynet.choviet.models.Category;
import com.skynet.choviet.models.MyPlace;
import com.skynet.choviet.models.Shop;
import com.skynet.choviet.models.ShopResponse;
import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

import java.util.List;

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
