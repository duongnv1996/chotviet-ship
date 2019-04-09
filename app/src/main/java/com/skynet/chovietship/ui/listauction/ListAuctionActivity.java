package com.skynet.chovietship.ui.listauction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.skynet.chovietship.R;
import com.skynet.chovietship.application.AppController;
import com.skynet.chovietship.models.Auction;
import com.skynet.chovietship.ui.base.BaseActivity;
import com.skynet.chovietship.ui.views.ProgressDialogCustom;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListAuctionActivity extends BaseActivity implements XRecyclerView.LoadingListener, ListProductContract.View {
    @BindView(R.id.imgHome)
    ImageView imgHome;
    @BindView(R.id.imgRight)
    ImageView imgRight;
    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    private static int TYPE_LOADMORE = 1;
    private static int TYPE_REFREESH = 0;
    @BindView(R.id.imgNew2)
    ImageView imgNew2;
    private int requestType;
    private int index = 0;
    private List<Auction> listAuction;
    private AdapterAuction adapter;
    ListProductContract.Presenter presenter;
    private ProgressDialogCustom dialogLoading;
    boolean isFirstShow = true;

    @Override
    protected int initLayout() {
        return R.layout.activity_list_auction;
    }

    @Override
    protected void initVariables() {
        dialogLoading = new ProgressDialogCustom(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        rcv.setLayoutManager(layoutManager);
        rcv.setHasFixedSize(true);
        listAuction = new ArrayList<>();
        adapter = new AdapterAuction(listAuction, this, callbackAuction);
        rcv.setAdapter(adapter);
        presenter = new ListProductPresenter(this);
        onRefresh();
    }
    private AdapterAuction.callbackAuction callbackAuction = new AdapterAuction.callbackAuction() {
        @Override
        public void onCallBack(int pos) {

        }

        @Override
        public void onSetPriceAuction(int pos) {
            presenter.setPrice(listAuction.get(pos).getId(), listAuction.get(pos).getLast_price() + listAuction.get(pos).getStep_price());
            AppController.getInstance().getmSetting().put("timeCountdown", 60000l);
            listAuction.get(pos).setLast_price(listAuction.get(pos).getLast_price() + listAuction.get(pos).getStep_price());
            adapter.notifyItemChanged(pos);
        }

        @Override
        public void onCountDownComplete(int pos) {

        }
    };
    @Override
    protected void initViews() {

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

    @OnClick({R.id.imgHome, R.id.imgRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgHome:
                onBackPressed();
                break;
            case R.id.imgRight:
                startActivity(new Intent(ListAuctionActivity.this,AuctionIntro.class));
                break;
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            onRefresh();
        }
    }

    @Override
    public void onRefresh() {
        requestType = TYPE_REFREESH;
        index = 0;
        presenter.getListProduct();

    }

    @Override
    public void onLoadMore() {
        requestType = TYPE_LOADMORE;
        presenter.getListProduct();
    }

    @Override
    public void onSucessGetListProduct(List<Auction> list) {
            this.listAuction.addAll(list);
            adapter.notifyDataSetChanged();

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
