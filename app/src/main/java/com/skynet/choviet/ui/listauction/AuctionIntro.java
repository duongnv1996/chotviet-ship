package com.skynet.choviet.ui.listauction;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.choviet.R;
import com.skynet.choviet.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuctionIntro extends BaseActivity {
    @BindView(R.id.imgBtn_back_toolbar)
    ImageView imgBtnBackToolbar;
    @BindView(R.id.tvTitle_toolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int initLayout() {
        return R.layout.activity_auction_intro;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        tvTitleToolbar.setText("Đấu giá live");
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

    @OnClick(R.id.imgBtn_back_toolbar)
    public void onViewClicked() {
        onBackPressed();
    }
}
