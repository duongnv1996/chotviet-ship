package com.skynet.choviet.ui.search.searchListProduct;

import com.skynet.choviet.models.Cart;
import com.skynet.choviet.models.Product;
import com.skynet.choviet.models.ProductResponse;
import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

import java.util.List;

public interface ListProductContract {
    interface View extends BaseView {
        void onSucessGetListProduct(List<Product> list, int index);        void onSucessGetCart(Cart cart);

    }

    interface Presenter extends IBasePresenter ,Listener{
        void getListProduct(int id, String key);
        void toggleFav(int id, boolean toggle);        void getCart();

    }

    interface Interactor {
        void getListProduct(int id, String idCate);
        void toggleFav(int id, boolean toggle);        void getCart();

    }

    interface Listener extends OnFinishListener {
        void onSucessGetListProduct(ProductResponse response);
        void onSucessGetCart(Cart cart);

    }
}
