package com.skynet.choviet.ui.search.searchListProduct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.skynet.choviet.R;
import com.skynet.choviet.models.Cart;
import com.skynet.choviet.models.Product;
import com.skynet.choviet.ui.base.BaseActivity;
import com.skynet.choviet.ui.cart.CartActivity;
import com.skynet.choviet.ui.detailProduct.ActivityDetailProduct;
import com.skynet.choviet.ui.views.ProgressDialogCustom;
import com.skynet.choviet.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchProductActivity extends BaseActivity implements AdapterProduct.CallBackProduct, XRecyclerView.LoadingListener, ListProductContract.View {

    private static int TYPE_LOADMORE = 1;
    private static int TYPE_REFREESH = 0;

    @BindView(R.id.imageView13)
    ImageView imageView13;
    @BindView(R.id.editText6)
    EditText editText6;
    @BindView(R.id.constraintLayout9)
    ConstraintLayout constraintLayout9;
    @BindView(R.id.rcv)
    XRecyclerView rcv;
    private int requestType;
    private int index = 0;
    private List<Product> list;
    private AdapterProduct adapter;
    ListProductContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;
    boolean isFirstShow = true;
    private Timer timer;

    @Override
    protected int initLayout() {
        return R.layout.activity_search_product;
    }

    @Override
    protected void initVariables() {
        dialogLoading = new ProgressDialogCustom(this);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setAutoMeasureEnabled(true);
        rcv.setLayoutManager(layoutManager);
        rcv.setHasFixedSize(true);
        list = new ArrayList<>();
        adapter = new AdapterProduct(list, this, this);
        rcv.setPullRefreshEnabled(true);
        rcv.setLoadingMoreEnabled(true);
        rcv.setAdapter(adapter);
        rcv.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rcv.setRefreshProgressStyle(ProgressStyle.BallPulse);
        rcv.setLimitNumberToCallLoadMore(10);
        rcv.setLoadingListener(this);
        presenter = new ListProductPresenter(this);
        onRefresh();
    }

    @Override
    protected void initViews() {
        editText6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // do your actual work here
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                list.clear();
                                adapter.notifyDataSetChanged();
                               onRefresh();
                            }
                        });
                    }
                }, 600);
            }
        });
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView13})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView13:
                onBackPressed();
                break;

        }
    }

    @Override
    public void toggleFav(int pos, Product product, boolean toggle) {
        presenter.toggleFav(product.getId(), toggle);
    }

    @Override
    public void onCallBack(int pos) {
        Intent i = new Intent(SearchProductActivity.this, ActivityDetailProduct.class);
        i.putExtra(AppConstant.MSG, list.get(pos).getId());
        startActivityForResult(i, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            onRefresh();
        }
    }

    @Override
    public void onRefresh() {
        requestType = TYPE_REFREESH;
        index = 0;
        presenter.getListProduct(index, editText6.getText().toString());
//        presenter.getCart();
    }

    @Override
    public void onLoadMore() {
        requestType = TYPE_LOADMORE;
        presenter.getListProduct(index, editText6.getText().toString());
    }

    @Override
    public void onSucessGetListProduct(List<Product> list, int index) {
        if (requestType == TYPE_REFREESH) {
            this.list.clear();
        }
        if (!list.isEmpty()) {
            this.list.addAll(list);
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
//        imgNew2.setVisibility(cart.getListProduct() != null && !cart.getListProduct().isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgress() {
        if (isFirstShow) {
            dialogLoading.showDialog();
            isFirstShow = false;
        }
    }

    @Override
    public void hiddenProgress() {
        dialogLoading.hideDialog();

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
}
