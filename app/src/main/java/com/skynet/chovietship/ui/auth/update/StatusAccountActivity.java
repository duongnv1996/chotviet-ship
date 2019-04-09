package com.skynet.chovietship.ui.auth.update;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.chovietship.R;
import com.skynet.chovietship.application.AppController;
import com.skynet.chovietship.models.Profile;
import com.skynet.chovietship.ui.base.BaseActivity;
import com.skynet.chovietship.ui.main.MainActivity;
import com.skynet.chovietship.ui.splash.SplashActivity;
import com.skynet.chovietship.utils.AppConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatusAccountActivity extends BaseActivity {
    @BindView(R.id.imageView9)
    ImageView imageView9;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.imageView25)
    ImageView imageView25;
    @BindView(R.id.textView62)
    TextView textView62;
    @BindView(R.id.textView63)
    TextView textView63;
    @BindView(R.id.textView64)
    TextView textView64;
    @BindView(R.id.hotline)
    TextView hotline;

    @Override
    protected int initLayout() {
        return R.layout.activity_status_account;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        Profile profile = AppController.getInstance().getmProfileUser();

        if (profile == null) {
            textView7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StatusAccountActivity.this, SplashActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    AppController.getInstance().setmProfileUser(null);
                    AppController.getInstance().getmSetting().remove(AppConstant.KEY_PROFILE);
                    AppController.getInstance().getmSetting().remove(AppConstant.KEY_TOKEN);
                    AppController.getInstance().setmProfileUser(null);
                    startActivity(intent);
                    finishAffinity();
                }
            });
        } else {
            textView62.setText("Hi! " + profile.getName());
            if (profile.getActive() == 1) {
                startActivity(new Intent(StatusAccountActivity.this, MainActivity.class));
                finish();
                return;
            }
        }


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

    @OnClick(R.id.hotline)
    public void onViewClicked() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + AppConstant.PHONE_CENTER));
        startActivity(intent);
    }
}
