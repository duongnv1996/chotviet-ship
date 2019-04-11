package com.skynet.chovietship.ui.wallet;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.tabs.TabLayout;
import com.skynet.chovietship.R;
import com.skynet.chovietship.application.AppController;
import com.skynet.chovietship.interfaces.ICallback;
import com.skynet.chovietship.models.Wallet;
import com.skynet.chovietship.ui.base.BaseFragment;
import com.skynet.chovietship.ui.views.DialogNewTrip;
import com.skynet.chovietship.ui.views.ProgressDialogCustom;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentWallet extends BaseFragment implements WalletContract.View {


    WalletContract.Presenter presenter;
    ProgressDialogCustom dialog;


    Unbinder unbinder;
    Wallet wallet;
    int numberTrip, numberCancelTrip;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.tvPriceReceive)
    TextView tvPriceReceive;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.tvNumberTrip)
    TextView tvNumberTrip;
    @BindView(R.id.tvNumberKM)
    TextView tvNumberKM;
    @BindView(R.id.textView17)
    TextView textView17;
    @BindView(R.id.textView21)
    TextView textView21;
    @BindView(R.id.view7)
    View view7;
    @BindView(R.id.textView24)
    TextView textView24;
    @BindView(R.id.tvWallet1)
    TextView tvWallet1;
    @BindView(R.id.BtnInCom)
    Button BtnInCom;
    @BindView(R.id.view9)
    View view9;
    @BindView(R.id.textView26)
    TextView textView26;
    @BindView(R.id.tvWallet2)
    TextView tvWallet2;
    @BindView(R.id.btnOutCom)
    Button btnOutCom;

    @Override
    protected int initLayout() {
        return R.layout.fragment_wallet;
    }

    public static FragmentWallet newInstance() {

        Bundle args = new Bundle();

        FragmentWallet fragment = new FragmentWallet();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);

    }

    @Override
    protected void initVariables() {
        dialog = new ProgressDialogCustom(getMyContext());
        presenter = new WalletPresenter(this);
        presenter.getWallet();
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0: {
                        tvPriceReceive.setText(String.format("%,.0f VNĐ", wallet.getToday().getTotal_money()));
                        tvNumberKM.setText(String.format("%,.0f KM",wallet.getToday().getDistance()));
                        tvNumberTrip.setText(String.format("%d", wallet.getToday().getTotal_booking()));
                        tvWallet2.setText(String.format("%,.0f VNĐ", wallet.getToday().getAccount()));
                        break;
                    }
                    case 1: {
                        tvPriceReceive.setText(String.format("%,.0f VNĐ", wallet.getWeek().getTotal_money()));
                        tvNumberKM.setText(String.format("%,.0f KM",wallet.getWeek().getDistance()));
                        tvNumberTrip.setText(String.format("%d", wallet.getWeek().getTotal_booking()));
                        tvWallet2.setText(String.format("%,.0f VNĐ", wallet.getWeek().getAccount()));
                        break;
                    }

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onSuccessGetWallet(Wallet wallet) {
        this.wallet = wallet;



        tablayout.getTabAt(0).select();
        tvPriceReceive.setText(String.format("%,.0f VNĐ", wallet.getToday().getTotal_money()));
        tvNumberKM.setText(String.format("%,.0f KM",wallet.getToday().getDistance()));
        tvNumberTrip.setText(String.format("%d", wallet.getToday().getTotal_booking()));
        tvWallet2.setText(String.format("%,.0f VNĐ", wallet.getToday().getAccount()));
    }



    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    public void showProgress() {
        dialog.showDialog();
    }

    @Override
    public void hiddenProgress() {
        dialog.hideDialog();

    }

    @Override
    public void onErrorApi(String message) {
        LogUtils.e(message);
    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);
    }

    @Override
    public void onErrorAuthorization() {
        showDialogExpiredToken();
    }

    @Override
    public void doAction() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
//
//    @OnClick({R.id.BtnInCom, R.id.btnOutCom})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.BtnInCom:
//                break;
//            case R.id.btnOutCom:
//                break;
//        }
//    }
}
