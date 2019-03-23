package com.skynet.choviet.ui.combo;

import com.skynet.choviet.models.Cart;
import com.skynet.choviet.models.Combo;
import com.skynet.choviet.models.Product;
import com.skynet.choviet.models.ProductResponse;
import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

import java.util.List;

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
