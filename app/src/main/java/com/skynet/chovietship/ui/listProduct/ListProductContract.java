package com.skynet.chovietship.ui.listProduct;

import com.skynet.chovietship.models.Cart;
import com.skynet.chovietship.models.Product;
import com.skynet.chovietship.models.ProductResponse;
import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

import java.util.List;

public interface ListProductContract {
    interface View extends BaseView {
        void onSucessGetListProduct(List<Product> list,int index);        void onSucessGetCart(Cart cart);

    }

    interface Presenter extends IBasePresenter ,Listener{
        void getListProduct(int id);
        void toggleFav(int id, boolean toggle);        void getCart();

    }

    interface Interactor {
        void getListProduct(int id);
        void toggleFav(int id, boolean toggle);        void getCart();

    }

    interface Listener extends OnFinishListener {
        void onSucessGetListProduct(ProductResponse response);
        void onSucessGetCart(Cart cart);

    }
}
