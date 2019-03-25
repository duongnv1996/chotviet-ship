package com.skynet.choviet.ui.category.listProductbylocation;

import com.skynet.choviet.models.Cart;
import com.skynet.choviet.models.Nearby;
import com.skynet.choviet.models.Product;
import com.skynet.choviet.models.Shop;
import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

import java.util.List;

public interface ListProductContract {
    interface View extends BaseView {
        void onSucessGetListProduct(List<Product> listProduct, int index);
        void onSucessGetCart(Cart cart);
        void onSucessGetListShop(List<Shop> list);
        void onSucessGetListFriendShop(List<Shop> list);
    }

    interface Presenter extends IBasePresenter ,Listener{
        void getListProduct(int id,int idcate,double lat,double lng,int idMarket);
        void toggleFav(int id, boolean toggle);        void getCart();

    }

    interface Interactor {
        void getListProduct(int id,int idCate,double lat,double lng,int idMarket);
        void toggleFav(int id, boolean toggle);        void getCart();

    }

    interface Listener extends OnFinishListener {
        void onSucessGetListProduct(Nearby response);        void onSucessGetCart(Cart cart);

    }
}
