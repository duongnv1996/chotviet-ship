package com.skynet.chovietship.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.skynet.chovietship.R;
import com.skynet.chovietship.application.AppController;
import com.skynet.chovietship.interfaces.ICallback;
import com.skynet.chovietship.models.Auction;
import com.skynet.chovietship.models.Banner;
import com.skynet.chovietship.models.Product;
import com.skynet.chovietship.models.Profile;
import com.skynet.chovietship.models.Shop;
import com.skynet.chovietship.ui.base.BaseFragment;
import com.skynet.chovietship.ui.cart.CartActivity;
import com.skynet.chovietship.ui.detailProduct.ActivityDetailProduct;
import com.skynet.chovietship.ui.detailshop.DetailShopActivity;
import com.skynet.chovietship.ui.listProduct.ListProductActivity;
import com.skynet.chovietship.ui.listauction.ListAuctionActivity;
import com.skynet.chovietship.ui.main.MainActivity;
import com.skynet.chovietship.ui.scanqr.ScannerQr;
import com.skynet.chovietship.ui.search.ActivitySearch;
import com.skynet.chovietship.utils.AppConstant;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.lightsky.infiniteindicator.OnPageClickListener;
import cn.lightsky.infiniteindicator.Page;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomeFragment extends BaseFragment implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener, XRecyclerView.LoadingListener, OnPageClickListener, AdapterMoreProduct.ICallBackListProduct {

    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    Unbinder unbinder;

    HomeContract.PresenterI presenter;
    @BindView(R.id.viewpager)
    LoopingViewPager indicator;

    @BindView(R.id.rcvCategory)
    RecyclerView rcvAuctions;

    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.textView17)
    TextView textView17;
    @BindView(R.id.tvShowMore)
    TextView tvShowMore;
    @BindView(R.id.rcvRecommend)
    RecyclerView rcvRecommend;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.rcvMore)
    XRecyclerView rcvMore;
    List<Banner> listBanner;
    @BindView(R.id.scrollUp)
    FloatingActionButton scrollUp;
    @BindView(R.id.nestscroll)
    NestedScrollView nestscroll;
    List<Product> listRecommend;
    List<Product> listSuggestion;
    List<Shop> listShop;
    @BindView(R.id.rcvHot)
    RecyclerView rcvShop;

    private int requestType;
    private List<Product> list;
    private static int TYPE_LOADMORE = 1;
    private static int TYPE_REFREESH = 0;

    private ICallback callBackRecommend = new ICallback() {
        @Override
        public void onCallBack(int pos) {
            Intent i = new Intent(getActivity(), ActivityDetailProduct.class);
            i.putExtra(AppConstant.MSG, listRecommend.get(pos).getId());
            startActivity(i);
        }
    };
    private AdapterHotShop.ICallBackListShop callBackShop = new AdapterHotShop.ICallBackListShop() {
        @Override
        public void onClickShop(int pos, Shop shop) {
            Intent i = new Intent(getActivity(), DetailShopActivity.class);
            i.putExtra(AppConstant.MSG, listShop.get(pos).getId());
            startActivity(i);
        }
    };
    private ICallback callbackSuggesstion = new ICallback() {
        @Override
        public void onCallBack(int pos) {
            Intent i = new Intent(getActivity(), ActivityDetailProduct.class);
            i.putExtra(AppConstant.MSG, listSuggestion.get(pos).getId());
            startActivity(i);
        }
    };
    private AdapterAuction.callbackAuction callbackAuction = new AdapterAuction.callbackAuction() {
        @Override
        public void onCallBack(int pos) {

        }

        @Override
        public void onSetPriceAuction(int pos) {
            presenter.bookASeatAuction(listAuction.get(pos).getId(), listAuction.get(pos).getLast_price() + listAuction.get(pos).getStep_price());
            AppController.getInstance().getmSetting().put("timeCountdown", 60000l);
            listAuction.get(pos).setLast_price(listAuction.get(pos).getLast_price() + listAuction.get(pos).getStep_price());
            rcvAuctions.getAdapter().notifyItemChanged(pos);
        }
    };
    private int index = 0;
    private List<Auction> listAuction;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View view) {
        ButterKnife.bind(this, view);
        swipe.setOnRefreshListener(this);
        rcvMore.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvMore.setHasFixedSize(true);
        rcvMore.setPullRefreshEnabled(false);
        rcvMore.setLoadingMoreEnabled(false);
        rcvMore.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rcvMore.setRefreshProgressStyle(ProgressStyle.BallPulse);
        rcvMore.setLimitNumberToCallLoadMore(10);
        rcvMore.setLoadingListener(this);
        rcvAuctions.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rcvRecommend.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rcvRecommend.setHasFixedSize(true);
        rcvAuctions.setHasFixedSize(true);
        rcvShop.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rcvShop.setHasFixedSize(true);
//        rcvMore.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (isAtBottom(rcvMore)) {
//                    scrollUp.setVisibility(View.GONE);
//                } else if (dy < 0 && scrollUp.getVisibility() == View.GONE) {
//                    scrollUp.setVisibility(View.VISIBLE);
//                }
//            }
//        });
    }

    public static boolean isAtBottom(RecyclerView recyclerView) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return isAtBottomBeforeIceCream(recyclerView);
        } else {
            return !ViewCompat.canScrollVertically(recyclerView, 1);
        }
    }

    private static boolean isAtBottomBeforeIceCream(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int count = recyclerView.getAdapter().getItemCount();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int pos = linearLayoutManager.findLastVisibleItemPosition();
            int lastChildBottom = linearLayoutManager.findViewByPosition(pos).getBottom();
            if (lastChildBottom == recyclerView.getHeight() - recyclerView.getPaddingBottom() && pos == count - 1)
                return true;
        }
        return false;
    }

    @Override
    protected void initVariables() {
        presenter = new HomePresenterI(this);
        listBanner = new ArrayList<>();
        listShop = new ArrayList<>();
        listSuggestion = new ArrayList<>();
        listAuction = new ArrayList<>();
        // bindData();
        onRefresh();
    }


    private void bindData() {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile != null) {

        } else {
            showDialogExpiredToken();
            return;
        }
    }

    private void setupChart(Profile profile) {

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

    @Override
    public void doAction() {

    }

    @Override
    public void onResume() {
        super.onResume();
        indicator.resumeAutoScroll();
    }

    @Override
    public void onPause() {
        super.onPause();
        indicator.pauseAutoScroll();
    }

    @Override
    public void onRefresh() {
//        presenter.getInfor();
        presenter.getHome();
        requestType = TYPE_REFREESH;
        index = 0;
        presenter.getListProduct(index);
    }

    @Override
    public void onLoadMore() {
        requestType = TYPE_LOADMORE;
        presenter.getListProduct(index);
    }

    @Override
    public void onSuccessGetInfor() {
        bindData();
    }

    @Override
    public void onSucessGetBanner(List<Banner> list) {
        listBanner.clear();
        listBanner.addAll(list);
        BannerAdapter adapter = new BannerAdapter(getContext(), listBanner, true);
        indicator.setAdapter(adapter);
//        IndicatorConfiguration configuration = new IndicatorConfiguration.Builder()
//                .imageLoader(new PicassoLoader())
//                .isStopWhileTouch(true)
//                .onPageClickListener(this)
//                .direction(LEFT)
//                .viewBinder(new ViewCard())
//                .position(IndicatorConfiguration.IndicatorPosition.Center_Bottom)
//                .build();
//        indicator.init(configuration);
//        indicator.notifyDataChange(listBanner);

    }


    @Override
    public void onSucessGetRecommend(List<Product> list) {
        this.listRecommend = list;
        rcvRecommend.setAdapter(new AdapterRecommend(listRecommend, getContext(), callBackRecommend));

    }

    @Override
    public void onSucessGetSuggestion(List<Product> list) {
        listSuggestion.addAll(list);
        rcvMore.setAdapter(new AdapterSuggession(list, getContext(), callbackSuggesstion));

    }

    @Override
    public void onSucessGetShops(List<Shop> list) {
        listShop.clear();
        listShop.addAll(list);
        rcvShop.setAdapter(new AdapterHotShop(listShop, getContext(), callBackShop));
    }

    @Override
    public void onSucessGetAuction(List<Auction> list) {
        listAuction = list;
        rcvAuctions.setAdapter(new AdapterAuction(listAuction, getContext(), callbackAuction));
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


    @OnClick(R.id.tvShowMore)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), ListProductActivity.class));
    }

    @OnClick(R.id.tvShowMore3)
    public void onViewShowAuctionClicked() {
        startActivity(new Intent(getActivity(), ListAuctionActivity.class));
    }

    @OnClick(R.id.tvShowMore4)
    public void onViewShopClicked() {
        getCallBackTitle().tranToTab(1);
    }

    @OnClick(R.id.tvShowMore5)
    public void onViewMoreClicked() {
        startActivity(new Intent(getActivity(), ListProductActivity.class));

    }

    @Override
    public void onPageClick(int position, Page page) {

    }

    @Override
    public void onClickProduct(int pos, Product shop) {
        Intent i = new Intent(getActivity(), ActivityDetailProduct.class);
        i.putExtra(AppConstant.MSG, shop.getId());
        startActivity(i);
    }

    @OnClick({R.id.imageView5, R.id.imgCart2, R.id.imageView21, R.id.editText7})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView5:
                ((MainActivity) getActivity()).openMenu();
                break;
            case R.id.imgCart2:
                startActivity(new Intent(getActivity(), CartActivity.class));
                break;
            case R.id.imageView21:
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
                //  startActivity(new Intent(getActivity(), ScannerQr.class));
                break;
            case R.id.editText7:
                Intent i = new Intent(getActivity(), ActivitySearch.class);
                Bundle b = new Bundle();
                b.putInt("type", ActivitySearch.TYPE_PRODUCT);
                b.putParcelableArrayList(AppConstant.MSG, (ArrayList<? extends Parcelable>) listSuggestion);
                i.putExtra(AppConstant.BUNDLE, b);
                startActivity(i);
                break;
        }
    }

    @OnClick(R.id.scrollUp)
    public void onViewScrollClicked() {
        nestscroll.scrollTo(0, 0);
    }
}