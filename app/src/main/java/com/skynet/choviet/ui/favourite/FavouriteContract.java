package com.skynet.choviet.ui.favourite;

import com.skynet.choviet.models.FavouriteItem;
import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

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
