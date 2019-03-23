package com.skynet.choviet.ui.scanqr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.KeyboardUtils;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.skynet.choviet.R;
import com.skynet.choviet.ui.base.BaseActivity;
import com.skynet.choviet.ui.detailshop.DetailShopActivity;
import com.skynet.choviet.utils.AppConstant;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScannerQr extends BaseActivity implements ScanContract.View {

    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    private CodeScanner mCodeScanner;
    private ScanContract.Presenter presenter;

    @Override
    protected int initLayout() {
        return R.layout.qrscan;
    }

    @Override
    protected void initVariables() {
        presenter = new ScanPresenter(this);
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(ScannerQr.this, result.getText(), Toast.LENGTH_SHORT).show();
                        if (result == null || result.getText().isEmpty()) return;
                        Intent i = new Intent(ScannerQr.this, DetailShopActivity.class);
                        i.putExtra(AppConstant.MSG, result.getText().replace(".jpg",""));
                        presenter.getShop(result.getText().replace(".jpg",""));
                        startActivity(i);
                        finish();
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
                KeyboardUtils.hideSoftInput(ScannerQr.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hiddenProgress() {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSubmit)
    public void onViewClicked() {
        presenter.getShop(edtPhone.getText().toString());
        Intent i = new Intent(ScannerQr.this, DetailShopActivity.class);
        i.putExtra(AppConstant.MSG,edtPhone.getText().toString().replace(".jpg",""));
        startActivity(i);
        finish();
    }
}
