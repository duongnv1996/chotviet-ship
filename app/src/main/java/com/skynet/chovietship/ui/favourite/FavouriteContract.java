package com.skynet.chovietship.ui.favourite;

import com.skynet.chovietship.models.FavouriteItem;
import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

public interface FavouriteContract {
    interface View extends BaseView {
        void onSucessGetList(FavouriteItem list);
    }
    interface Presenter extends IBasePresenter, Listener {
        void getList();
        void toggleFav(int idPost, boolean isFav);
    }
    interface Interactor {
        void getList();
        void toggleFav(int idPost, int isFav);
    }
    interface Listener extends OnFinishListener {
        void onSucessGetList(FavouriteItem list);
    }
}
