package com.skynet.choviet.ui.category;

import com.skynet.choviet.models.Category;
import com.skynet.choviet.ui.base.BaseView;
import com.skynet.choviet.ui.base.IBasePresenter;
import com.skynet.choviet.ui.base.OnFinishListener;

import java.util.List;

public interface CategoryContract {
    interface View extends BaseView {
        void onSuccessGetListCategory(List<Category> list);
    }

    interface Presenter extends IBasePresenter ,Listener{
        void getCategory();
    }

    interface Interactor {
        void getCategory();

    }

    interface Listener extends OnFinishListener {
        void onSucessGetCategory(List<Category> categories);
    }
}
