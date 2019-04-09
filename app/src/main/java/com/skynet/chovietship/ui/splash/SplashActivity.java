package com.skynet.chovietship.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.jaeger.library.StatusBarUtil;
import com.skynet.chovietship.R;
import com.skynet.chovietship.application.AppController;
import com.skynet.chovietship.models.Profile;
import com.skynet.chovietship.ui.auth.AuthActivity;
import com.skynet.chovietship.ui.auth.update.StatusAccountActivity;
import com.skynet.chovietship.ui.auth.update.UpdateActivity;
import com.skynet.chovietship.ui.main.MainActivity;
import com.skynet.chovietship.ui.views.ProgressDialogCustom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity implements SlideContract.View {


    @BindView(R.id.layoutSplash1)
    ConstraintLayout layoutSplash1;
    @BindView(R.id.layoutSplash2)
    ConstraintLayout layoutSplash2;
    private ProgressDialogCustom dialogCustom;
    private SlideContract.PresenterI presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        StatusBarUtil.setTransparent(this);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        if (AppController.getInstance().getmSetting().getBoolean("isFirstTime", true)) {
            layoutSplash1.setVisibility(View.VISIBLE);
            layoutSplash2.setVisibility(View.GONE);
            AppController.getInstance().getmSetting().put("isFirstTime", false);
        } else {
            layoutSplash1.setVisibility(View.GONE);
            layoutSplash2.setVisibility(View.VISIBLE);
        }
        dialogCustom = new ProgressDialogCustom(this);
        presenter = new SlidePresenterI(this);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                if (!AppController.getInstance().getmSetting().getBoolean("isShown", false)) {
//////                    startActivity(new Intent(SplashActivity.this, ShowcaseActivity.class));
////                    return;
////                } else {
////                }
//            }
//        }, 500);
//        Profile profile =  new Profile();
////        profile.setId("3ed572a6-0278-11e9-b7ce-005056b2404b");  //son
////        profile.setId("b307b18c-09af-11e9-b7ce-005056b2404b");  //tram
//        profile.setId("663ccc4b-0278-11e9-b7ce-005056b2404b");  //tam
//        AppController.getInstance().setmProfileUser(profile);
        presenter.getInfor();

//        getDialogProgress().hideDialog();
//        MainApplication.getInstance().setDay(day);


//        Spannable wordtoSpan = new SpannableString("VINENGLISH");
//        wordtoSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#ecac1d")), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tvName.setText(wordtoSpan);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            tvName.setText(Html.fromHtml(getString(R.string.splash_name), Html.FROM_HTML_MODE_LEGACY));
//        } else {
//            tvName.setText(Html.fromHtml(getString(R.string.splash_name)));
//        }
    }


    @Override
    public void onSuccessGetInfor() {
        Profile profile = AppController.getInstance().getmProfileUser();
//        if (profile.getAvatar().isEmpty() || profile.getCmnd1().isEmpty() || profile.getCmnd2().isEmpty()
//                || profile.getDkx().isEmpty()) {
//            startActivity(new Intent(SplashActivity.this, UpdateActivity.class));
//            finish();
//            return;
//        }
//        if (profile.getActive() != 1) {
//            startActivity(new Intent(SplashActivity.this, StatusAccountActivity.class));
//            finish();
//            return;
//
//        }
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void showProgress() {
        dialogCustom.showDialog();
    }

    @Override
    public void hiddenProgress() {
        dialogCustom.hideDialog();
    }

    @OnClick(R.id.button2)
    public void onClickMain() {
        startActivity(new Intent(SplashActivity.this, AuthActivity.class));
        finish();
    }

    @Override
    public void onErrorApi(String message) {
        LogUtils.e(message);
        startActivity(new Intent(SplashActivity.this, AuthActivity.class));
        finish();
    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);
        startActivity(new Intent(SplashActivity.this, AuthActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroyView();
        super.onDestroy();
    }

    @Override
    public void onErrorAuthorization() {
        startActivity(new Intent(SplashActivity.this, AuthActivity.class));
        finish();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
