package com.skynet.chovietship.ui.category.listProductbylocation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.skynet.chovietship.R;
import com.skynet.chovietship.application.AppController;
import com.skynet.chovietship.models.Cart;
import com.skynet.chovietship.models.MyPlace;
import com.skynet.chovietship.models.Place;
import com.skynet.chovietship.models.Product;
import com.skynet.chovietship.models.Shop;
import com.skynet.chovietship.ui.auth.updateProfile.SearchMapAdressActivity;
import com.skynet.chovietship.ui.base.BaseActivity;
import com.skynet.chovietship.ui.detailProduct.ActivityDetailProduct;
import com.skynet.chovietship.ui.detailshop.DetailShopActivity;
import com.skynet.chovietship.ui.profile.SpacesItemDecoration;
import com.skynet.chovietship.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListProductActivity extends BaseActivity implements AdapterProduct.CallBackProduct, XRecyclerView.LoadingListener, ListProductContract.View, AdapterNearbyShop.ICallBackListShop {


    @BindView(R.id.imgHome)
    ImageView imgHome;
    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.imgRight)
    ImageView imgRight;
    @BindView(R.id.edtSearch)
    EditText edtSearch;
    @BindView(R.id.include4)
    ConstraintLayout include4;
    @BindView(R.id.rcv)
    XRecyclerView rcv;
    @BindView(R.id.tvShowMore2)
    TextView tvShowMore2;
    @BindView(R.id.textView48)
    TextView textView48;
    @BindView(R.id.rcvShop)
    RecyclerView rcvShop;
    @BindView(R.id.rcvFriendShop)
    RecyclerView rcvFriendShop;
    @BindView(R.id.textView51)
    TextView textView51;
    private int requestType;
    private int index = 0;
    private List<Product> list;
    private List<Shop> listFriendShop;
    private List<Shop> listShop;
    private AdapterProduct adapter;
    ListProductContract.Presenter presenter;
    boolean isFirstShow = true;
    private MyPlace myPlace;
    String name;

    private static int TYPE_LOADMORE = 1;
    private static int TYPE_REFREESH = 0;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar2;
    private int market;

    @Override
    protected int initLayout() {
        return R.layout.activity_list_product_by_category;
    }

    @Override
    protected void initVariables() {
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setAutoMeasureEnabled(true);
        rcv.setLayoutManager(layoutManager);
        rcv.setHasFixedSize(true);
        list = new ArrayList<>();
        adapter = new AdapterProduct(list, this, this);
        rcv.setPullRefreshEnabled(false);
        rcv.addItemDecoration(new SpacesItemDecoration(4));
        rcv.setLoadingMoreEnabled(true);
        rcv.setAdapter(adapter);
        rcv.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rcv.setRefreshProgressStyle(ProgressStyle.BallPulse);
        rcv.setLimitNumberToCallLoadMore(10);
        rcv.setLoadingListener(this);
        presenter = new ListProductPresenter(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestType = TYPE_REFREESH;
                index = 0;
                double lat = 0, lng = 0;
                if (myPlace != null) {
                    lat = myPlace.getLat();
                    lng = myPlace.getLng();
                }
                presenter.getListProduct(index, getIntent().getIntExtra(AppConstant.MSG, 1), lat, lng,market);
            }
        }, 1000);
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        myPlace = new Gson().fromJson(AppController.getInstance().getmSetting().getString("myplace"), MyPlace.class);
        name = getIntent().getStringExtra("name");
        market = getIntent().getIntExtra("market",1);
        if (myPlace != null) {
            tvTitleToolbar.setText(name + " > " + myPlace.getAddress());
        } else {
            tvTitleToolbar.setText(name);
        }

        rcvShop.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rcvFriendShop.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rcvShop.setHasFixedSize(true);
        rcvFriendShop.setHasFixedSize(true);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    @OnClick({R.id.imgHome, R.id.imgRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgHome:
                onBackPressed();
                break;
            case R.id.imgRight:
                startActivityForResult(new Intent(ListProductActivity.this, SearchMapAdressActivity.class), 1000);
                break;
        }
    }

    @Override
    public void toggleFav(int pos, Product product, boolean toggle) {
        presenter.toggleFav(product.getId(), toggle);
    }

    @Override
    public void onCallBack(int pos) {
        Intent i = new Intent(ListProductActivity.this, ActivityDetailProduct.class);
        i.putExtra(AppConstant.MSG, list.get(pos).getId());
        startActivityForResult(i, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Bundle b = data.getBundleExtra(AppConstant.BUNDLE);
            if (b != null) {
                Place place = b.getParcelable(AppConstant.MSG);
                if (place != null) {
                    MyPlace myPlace = new MyPlace();
                    myPlace.setName(place.getAddress());
                    myPlace.setAddress(place.getAddress());
                    myPlace.setDescription(place.getAddress());
                    myPlace.setLat(place.getLatLng().latitude);
                    myPlace.setLng(place.getLatLng().longitude);
                    tvTitleToolbar.setText(name + " > " + myPlace.getAddress());
                    this.myPlace = myPlace;
                    AppController.getInstance().getmSetting().put("myplace",new Gson().toJson(myPlace));
                    AppController.getInstance().getmSetting().put("latlng", new Gson().toJson(place.getLatLng()));

                    onRefresh();
                }
            }
        }
    }

    @Override
    public void onRefresh() {
        requestType = TYPE_REFREESH;
        index = 0;
        double lat = 0, lng = 0;
        if (myPlace != null) {
            lat = myPlace.getLat();
            lng = myPlace.getLng();
        }
        presenter.getListProduct(index, getIntent().getIntExtra(AppConstant.MSG, 1), lat, lng,market);
        list.clear();
        if(listShop!=null)
        listShop.clear();
        if(listFriendShop!=null)
            listFriendShop.clear();
        if( rcvFriendShop.getAdapter()!=null)
            rcvFriendShop.getAdapter().notifyDataSetChanged();
        if(  rcvShop.getAdapter()!=null)

            rcvShop.getAdapter().notifyDataSetChanged();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        requestType = TYPE_LOADMORE;
        double lat = 0, lng = 0;
        if (myPlace != null) {
            lat = myPlace.getLat();
            lng = myPlace.getLng();
        }
        presenter.getListProduct(index, getIntent().getIntExtra(AppConstant.MSG, 1), lat, lng,market);
    }

    @Override
    public void onSucessGetListProduct(List<Product> list, int index) {
        if (requestType == TYPE_REFREESH) {
            this.list.clear();
        }
        if (!list.isEmpty()) {
            this.list.addAll(list);
            adapter.clearCache();
            adapter.notifyDataSetChanged();
        } else {
            rcv.setNoMore(true);
        }
        this.index = index;
        rcv.loadMoreComplete();
        rcv.refreshComplete();
    }

    @Override
    public void onSucessGetCart(Cart cart) {
    }

    @Override
    public void onSucessGetListShop(List<Shop> list) {
        this.listShop = list;
        rcvShop.setAdapter(new AdapterNearbyShop(listShop, this, this));
    }

    @Override
    public void onSucessGetListFriendShop(List<Shop> list) {
        this.listFriendShop = list;
        rcvFriendShop.setAdapter(new AdapterNearbyShop(listFriendShop, this, this));

    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgress() {
        if (isFirstShow) {
            progressBar2.setVisibility(View.VISIBLE);
            isFirstShow = false;
        }
    }

    @Override
    public void hiddenProgress() {
        progressBar2.setVisibility(View.GONE);

    }

    @Override
    public void onErrorApi(String message) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onErrorAuthorization() {

    }

    @Override
    public void onClickShop(int pos, Shop shop) {
        Intent i = new Intent(ListProductActivity.this, DetailShopActivity.class);
        i.putExtra(AppConstant.MSG, shop.getId());
        startActivity(i);
    }
}
