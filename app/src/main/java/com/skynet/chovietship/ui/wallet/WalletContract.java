package com.skynet.chovietship.ui.wallet;


import com.skynet.chovietship.models.Wallet;
import com.skynet.chovietship.ui.base.BaseView;
import com.skynet.chovietship.ui.base.IBasePresenter;
import com.skynet.chovietship.ui.base.OnFinishListener;

import java.util.List;

public interface WalletContract {
    interface View extends BaseView {
        void onSuccessGetWallet(Wallet wallet);
    }
    interface Presenter extends IBasePresenter,WalletListener {
       void getWallet();
    }
    interface Interactor {
        void getWallet();
    }
    interface WalletListener extends OnFinishListener {
        void onSuccessGetWallet(Wallet wallet);
    }
}
