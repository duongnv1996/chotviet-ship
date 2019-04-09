package com.skynet.chovietship.ui.shop;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.tabs.TabLayout;
import com.skynet.chovietship.R;
import com.skynet.chovietship.models.Category;
import com.skynet.chovietship.models.Shop;
import com.skynet.chovietship.ui.base.BaseFragment;
import com.skynet.chovietship.ui.detailshop.DetailShopActivity;
import com.skynet.chovietship.ui.scanqr.ScannerQr;
import com.skynet.chovietship.utils.AppConstant;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ListShopFragment extends BaseFragment implements ShopContract.View, SwipeRefreshLayout.OnRefreshListener, AdapterHotShop.ICallBackListShop {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.textView17)
    TextView textView17;
    @BindView(R.id.rcvHot)
    RecyclerView rcvHot;
    @BindView(R.id.tvShowMore)
    TextView tvShowMore;
    @BindView(R.id.rcvMore)
    RecyclerView rcvMore;
    @BindView(R.id.button3)
    TextView button3;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private ShopContract.Presenter presenter;
    private List<Shop> listShop;
    private List<Shop> listHotShop;
    private List<Category> listCategories;

    public static ListShopFragment newInstance() {
        Bundle args = new Bundle();
        ListShopFragment fragment = new ListShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void doAction() {

    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        swipe.setOnRefreshListener(this);
        rcvHot.setLayoutManager(new LinearLayoutManager(getMyContext(), RecyclerView.HORIZONTAL, false));
        rcvMore.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    protected void initVariables() {
        presenter = new ShopPresenter(this);
        presenter.getCategory();
    }

    @Override
    public void onSuccessGetListShop(List<Shop> list) {
        this.listShop = list;
        rcvMore.setAdapter(new AdapterHotShop(list, getContext(), this));
    }

    @Override
    public void onSuccessGetListHotShop(List<Shop> list) {
        this.listHotShop = list;
        rcvHot.setAdapter(new AdapterHotShop(list, getContext(), this));
    }

    @Override
    public void onSucessGetCategory(List<Category> categories) {
        this.listCategories = categories;
        for (int i = 0; i < listCategories.size(); i++) {
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.getTabAt(i).setText(listCategories.get(i).getName());
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (listHotShop != null) {
                    listHotShop.clear();
                    rcvHot.getAdapter().notifyDataSetChanged();
                }
                if (listShop != null) {
                    listShop.clear();
                    rcvMore.getAdapter().notifyDataSetChanged();
                }
                presenter.getListShop(listCategories.get(tabLayout.getSelectedTabPosition()).getId());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.getTabAt(0).select();
        presenter.getListShop(listCategories.get(tabLayout.getSelectedTabPosition()).getId());

    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    public void showProgress() {
        swipe.setRefreshing(true);
    }

    @Override
    public void hiddenProgress() {
        swipe.setRefreshing(false);

    }

    @Override
    public void onErrorApi(String message) {
        LogUtils.e(message);
    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);
        showToast(message, AppConstant.NEGATIVE);
    }

    @Override
    public void onErrorAuthorization() {
        showDialogExpiredToken();
    }

    @OnClick(R.id.button3)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), NearbyActivity.class));
    }

    @Override
    public void onRefresh() {

        presenter.getListShop(listCategories.get(tabLayout.getSelectedTabPosition()).getId());

    }

    @Override
    public void onClickShop(int pos, Shop shop) {
        Intent i = new Intent(getActivity(), DetailShopActivity.class);
        i.putExtra(AppConstant.MSG, shop.getId());
        startActivity(i);
    }

    @OnClick(R.id.btnQr)
    public void onViewQRClicked() {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    startActivity(new Intent(getActivity(), ScannerQr.class));
                } else {

                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });

    }
}
