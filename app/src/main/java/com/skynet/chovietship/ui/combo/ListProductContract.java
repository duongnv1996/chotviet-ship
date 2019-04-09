package com.skynet.chovietship.ui.combo;

import com.skynet.chovietship.models.Cart;
import com.skynet.chovietship.models.Combo;
import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

public interface ListProductContract {
    interface View extends BaseView {
        void onSucessGetListProduct(Combo combo);        void onSucessGetCart(Cart cart);

    }

    interface Presenter extends IBasePresenter ,Listener{
        void getListProduct(int id);
        void toggleFav(int id, boolean toggle);        void getCart();
        void addToCart(int idProduct,int number);

    }

    interface Interactor {
        void getListProduct(int id);
        void toggleFav(int id, boolean toggle);        void getCart();
        void addToCart(int idProduct,int number);

    }

    interface Listener extends OnFinishListener {
        void onSucessGetListProduct(Combo combo);
        void onSucessGetCart(Cart cart);

    }
}
