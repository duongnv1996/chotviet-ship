package com.skynet.chovietship.ui.auth.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.jaeger.library.StatusBarUtil;
import com.skynet.chovietship.R;
import com.skynet.chovietship.application.AppController;
import com.skynet.chovietship.models.Profile;
import com.skynet.chovietship.ui.auth.forgotPassword.ForgotPwActivity;
import com.skynet.chovietship.ui.auth.signup.ActivitySignUp;
import com.skynet.chovietship.ui.auth.update.StatusAccountActivity;
import com.skynet.chovietship.ui.auth.update.UpdateActivity;
import com.skynet.chovietship.ui.base.BaseActivity;
import com.skynet.chovietship.ui.main.MainActivity;
import com.skynet.chovietship.ui.privacy.PrivacyActivity;
import com.skynet.chovietship.ui.privacy.TermActivity;
import com.skynet.chovietship.ui.views.ProgressDialogCustom;
import com.skynet.chovietship.utils.AppConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {
    private static final int RC_SIGN_IN = 1000;
    @BindView(R.id.username_txt)
    EditText edtEmailPhone;
    @BindView(R.id.pass_txt)
    EditText edtPassword;


    private ProgressDialogCustom dialogCustom;
    private LoginContract.PresenterI presenter;

    @Override
    protected int initLayout() {
        StatusBarUtil.setTransparent(this);
        return R.layout.activity_login;
    }

    @Override
    protected void initVariables() {
        dialogCustom = new ProgressDialogCustom(this);
        presenter = new LoginPresenterI(this);

    }

    @Override
    protected void initViews() {
//        StatusBarUtil.setTransparent(this);
        ButterKnife.bind(this);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }

    @OnClick({R.id.login_btn, R.id.forget_txt, R.id.btnSignUpFB2, R.id.btnSignInGG2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                presenter.login(edtEmailPhone.getText().toString(), edtPassword.getText().toString(), AppConstant.TYPE_USER);
                break;
            case R.id.forget_txt:
                  startActivity(new Intent(LoginActivity.this, ForgotPwActivity.class));
                break;
            case R.id.btnSignUpFB2:
                startActivity(new Intent(LoginActivity.this, PrivacyActivity.class));
                break;
            case R.id.btnSignInGG2:
                startActivity(new Intent(LoginActivity.this, TermActivity.class));
                break;
        }
    }

    @Override
    public void onSuccessLogin(Profile profile) {
        setResult(RESULT_OK);
//        if (profile.getAvatar().isEmpty() || profile.getCmnd1().isEmpty() || profile.getCmnd2().isEmpty()
//                || profile.getDkx().isEmpty()) {
//            startActivity(new Intent(LoginActivity.this, UpdateActivity.class));
//            finish();
//            return;
//        }
//        if (profile.getActive() != 1) {
//            startActivity(new Intent(LoginActivity.this, StatusAccountActivity.class));
//            finish();
//            return;
//        }
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        i.putExtra("isFromLogin", true);
        startActivity(i);
        finish();
    }

    @Override
    public void onSuccesLoginFacebook(Profile profile) {

    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroyView();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        dialogCustom.showDialog();
    }

    @Override
    public void hiddenProgress() {
        dialogCustom.hideDialog();
    }

    @Override
    public void onErrorApi(String message) {
        LogUtils.e(message);
//      showDialogError(getString(R.string.error_unk),getString(R.string.back),R.drawable.bg_button_red_dialog,R.drawable.ic_error);
//        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);
//        showDialogError(message,getString(R.string.back),R.drawable.bg_button_red_dialog,R.drawable.ic_error);
//        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        showToast(message, AppConstant.NEGATIVE);
    }

    @Override
    public void onErrorAuthorization() {

    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @OnClick(R.id.imgBackToolbar)
    public void onViewClicked() {
        onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tvSignup)
    public void onViewSignUpClicked() {
        startActivity(new Intent(LoginActivity.this,ActivitySignUp.class));
    }
}
