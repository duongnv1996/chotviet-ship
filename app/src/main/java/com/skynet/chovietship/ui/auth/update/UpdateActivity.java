package com.skynet.chovietship.ui.auth.update;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.LogUtils;
import com.skynet.chovietship.R;
import com.skynet.chovietship.application.AppController;
import com.skynet.chovietship.network.api.ApiService;
import com.skynet.chovietship.network.api.ApiUtil;
import com.skynet.chovietship.ui.base.BaseActivity;
import com.skynet.chovietship.ui.profile.ProfileActivity;
import com.skynet.chovietship.ui.profile.UploadContract;
import com.skynet.chovietship.ui.views.ProgressDialogCustom;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import me.iwf.photopicker.PhotoPicker;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class UpdateActivity extends BaseActivity implements UpdateContract.View {


    final int PENDDING = 0;
    final int VERIFIED = 1;
    final int NEED_VERIFY_AGAIN = 2;
    final int DENY = 3;
    final int NOT_UPLOAD = 4;
    UpdateContract.Presenter presenter;
    ProgressDialogCustom dialogLoading;
    @BindView(R.id.img_result_avatar)
    ImageView imgResultAvatar;
    @BindView(R.id.img_next_avatar)
    ImageView imgNextAvatar;
    @BindView(R.id.progress_avatar)
    ProgressBar progressAvatar;
    @BindView(R.id.ll_root_avatar)
    LinearLayout llRootAvatar;
    @BindView(R.id.tv_getting)
    TextView tvGetting;
    @BindView(R.id.ll_root_getting_start)
    LinearLayout llRootGettingStart;
    @BindView(R.id.img_result_bangLai)
    ImageView imgResultBangLai;
    @BindView(R.id.img_next_bangLai)
    ImageView imgNextBangLai;
    @BindView(R.id.progress_bangLai)
    ProgressBar progressBangLai;
    @BindView(R.id.ll_root_bangLai)
    LinearLayout llRootBangLai;

    @BindView(R.id.img_result_dangkyxe)
    ImageView imgResultDangkyxe;
    @BindView(R.id.img_next_dangkyxe)
    ImageView imgNextDangkyxe;
    @BindView(R.id.progress_dangkyxe)
    ProgressBar progressDangkyxe;
    @BindView(R.id.ll_root_dangkyxe)
    LinearLayout llRootDangkyxe;

    @BindView(R.id.img_result_baohiem)
    ImageView imgResultBaohiem;
    @BindView(R.id.img_next_baohiem)
    ImageView imgNextBaohiem;
    @BindView(R.id.progress_baohiem)
    ProgressBar progressBaohiem;
    @BindView(R.id.ll_root_baohiem)
    LinearLayout llRootBaohiem;

    @BindView(R.id.img_result_cmnd)
    ImageView imgResultCmnd;
    @BindView(R.id.img_next_cmnd)
    ImageView imgNextCmnd;
    @BindView(R.id.progress_cmnd)
    ProgressBar progressCmnd;
    @BindView(R.id.ll_root_cmnd)
    LinearLayout llRootCmnd;

    @BindView(R.id.img_result_lylich)
    ImageView imgResultLylich;
    @BindView(R.id.img_next_lylich)
    ImageView imgNextLylich;
    @BindView(R.id.progress_lylich)
    ProgressBar progressLylich;
    @BindView(R.id.ll_root_lylich)
    LinearLayout llRootLylich;
    @BindView(R.id.update_layout)
    LinearLayout updateLayout;
    @BindView(R.id.img_result_baohiemhanghoa)
    ImageView imgResultBaohiemhanghoa;
    @BindView(R.id.img_next_baohiemhanghoa)
    ImageView imgNextBaohiemhanghoa;
    @BindView(R.id.progress_baohiemhanghoa)
    ProgressBar progressBaohiemhanghoa;
    @BindView(R.id.ll_root_baohiemhanghoa)
    LinearLayout llRootBaohiemhanghoa;
    private int requestCode=1;


    @Override
    protected void onResume() {
        super.onResume();


    }


    private void showDialogPermission() {
        MaterialDialog materialDialog = new MaterialDialog.Builder(this).title("Thông báo")
                .content("Bạn cần cấp quyền truy cập ứng dụng để tiếp tục sử dụng dịch vụ")
                .positiveColor(Color.GRAY)
                .positiveText("Đồng ý")
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        ActivityCompat.requestPermissions(UpdateActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1000);
                    }
                }).show();
    }


    @Override
    protected int initLayout() {
        return R.layout.activity_update;
    }

    @Override
    protected void initVariables() {
        dialogLoading = new ProgressDialogCustom(this);
        presenter = new UpdatePresenter(this);
        presenter.getInfor();
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
//        tvGetting.setText(Html.fromHtml(getString(R.string.update_1)));
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }


    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    public void onDestroy() {
        presenter.onDestroyView();
        super.onDestroy();


    }


    private ApiService getmApi() {
        return ApiUtil.createTokenApi();
    }

    private void updateStateItem(int type, int state) {
        switch (state) {
            case PENDDING: {
                llRootGettingStart.setVisibility(View.GONE);
                changeStateToVerify(type);
                break;
            }
            case VERIFIED: {
                changeStateToSuccess(type);
                break;
            }
            case NEED_VERIFY_AGAIN: {
                llRootGettingStart.setVisibility(View.GONE);
                changeStateToVerify(type);
                break;
            }
            case DENY: {
                llRootGettingStart.setVisibility(View.GONE);
                changeStateToDeny(type);
                break;
            }
            case NOT_UPLOAD: {
                llRootGettingStart.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    ArrayList<String> listPermissions = new ArrayList<>();
    View view;

    @OnClick({R.id.ll_root_bangLai, R.id.ll_root_dangkyxe, R.id.ll_root_baohiem, R.id.ll_root_cmnd, R.id.ll_root_baohiemhanghoa, R.id.ll_root_lylich, R.id.ll_root_avatar})
    public void onViewClicked(View view) {
        this.view = view;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            listPermissions.add(Manifest.permission.CAMERA);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            listPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            listPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (listPermissions.size() > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(listPermissions.toArray(new String[listPermissions.size()]), 100);
                return;
            }
        }
        clickView(view);
    }

    private void clickView(View view) {
        switch (view.getId()) {
            case R.id.ll_root_bangLai:
                requestCode = 1;
                EasyImage.openChooserWithDocuments(UpdateActivity.this,"Chọn ảnh ", 0);
//                PhotoPicker.builder().setPhotoCount(1)
//                        .setShowCamera(true)
//                        .setPreviewEnabled(true)
//                        .start(this, 1);
                break;
            case R.id.ll_root_dangkyxe:                requestCode = 2;

                EasyImage.openChooserWithDocuments(UpdateActivity.this,"Chọn ảnh ", 0);
//
//                PhotoPicker.builder().setPhotoCount(1)
//                        .setShowCamera(true)
//                        .setPreviewEnabled(true)
//                        .start(this, 2);
                break;
            case R.id.ll_root_baohiem:                requestCode = 3;

//                PhotoPicker.builder().setPhotoCount(1)
//                        .setShowCamera(true)
//                        .setPreviewEnabled(true)
//                        .start(this, 7);
                EasyImage.openChooserWithDocuments(UpdateActivity.this,"Chọn ảnh ", 0);

                break;
            case R.id.ll_root_baohiemhanghoa:
                requestCode = 7;
                EasyImage.openChooserWithDocuments(UpdateActivity.this,"Chọn ảnh ", 0);

//                PhotoPicker.builder().setPhotoCount(1)
//                        .setShowCamera(true)
//                        .setPreviewEnabled(true)
//                        .start(this, 8);
                break;

            case R.id.ll_root_lylich:                requestCode = 5;

//                PhotoPicker.builder().setPhotoCount(1)ll_root_cmnd
//                        .setShowCamera(true)
//                        .setPreviewEnabled(true)
//                        .start(this, 5);
                EasyImage.openChooserWithDocuments(UpdateActivity.this,"Chọn ảnh ", 0);

                break;
            case R.id.ll_root_avatar:                requestCode = 8;

//                PhotoPicker.builder().setPhotoCount(1)
//                        .setShowCamera(true)
//                        .setPreviewEnabled(true)
//                        .start(this, 4);
                EasyImage.openChooserWithDocuments(UpdateActivity.this,"Chọn ảnh ", 0);

                break;
            case R.id.ll_root_cmnd:                requestCode = 4;

//                PhotoPicker.builder().setPhotoCount(1)
//                        .setShowCamera(true)
//                        .setPreviewEnabled(true)
//                        .start(this, 3);
                EasyImage.openChooserWithDocuments(UpdateActivity.this,"Chọn ảnh ", 0);

                break;


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                showDialogPermission();
            } else {
            }
            return;
        }
        if (grantResults.length == listPermissions.size()) {
            boolean isSuccessful = true;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    isSuccessful = false;
                    break;
                }
            }

            if (isSuccessful && view != null) {
                clickView(view);
            }
        } else {
//            showDialogErrorContent(getString(R.string.permission));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                if (!imageFile.exists()) {
                    Toast.makeText(UpdateActivity.this, "File không tồn tại.", Toast.LENGTH_SHORT).show();
                    return;
                }

                uploadProfile(imageFile, UpdateActivity.this.requestCode);
            }


        });
//        if (resultCode == RESULT_OK && data != null) {
//            File newFile = new File((String) data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS).get(0));
//            if (newFile != null && newFile.exists()) {
//                uploadProfile(newFile, this.requestCode);
//            }
//        }

    }

    private void uploadProfile(File newFile, final int type) {
        presenter.update(newFile, type);
    }

    private void changeStateToLoading(int type) {
        switch (type) {
            case 1: {
                progressBangLai.setVisibility(View.VISIBLE);
                imgNextBangLai.setVisibility(View.GONE);
                llRootBangLai.setClickable(false);

                break;
            }
            case 2: {
                progressDangkyxe.setVisibility(View.VISIBLE);
                imgNextDangkyxe.setVisibility(View.GONE);
                llRootDangkyxe.setClickable(false);

                break;
            }
            case 3: {
                progressBaohiem.setVisibility(View.VISIBLE);
                imgNextBaohiem.setVisibility(View.GONE);
                llRootBaohiem.setClickable(false);

                break;
            }
            case 4: {
                progressCmnd.setVisibility(View.VISIBLE);
                imgNextCmnd.setVisibility(View.GONE);
                llRootCmnd.setClickable(false);

                break;
            }
            case 5: {

                break;
            }
        }
    }

    private void changeStateToVerify(int type) {
        switch (type) {
            case 1: {
                progressBangLai.setVisibility(View.GONE);
                imgNextBangLai.setVisibility(View.GONE);
                imgResultBangLai.setImageResource(R.drawable.ic_wait);
                imgResultBangLai.setVisibility(View.VISIBLE);
                llRootBangLai.setClickable(true);

                break;
            }
            case 2: {
                progressDangkyxe.setVisibility(View.GONE);
                imgNextDangkyxe.setVisibility(View.GONE);
                imgResultDangkyxe.setImageResource(R.drawable.ic_wait);
                imgResultDangkyxe.setVisibility(View.VISIBLE);
                llRootDangkyxe.setClickable(true);

                break;
            }
            case 3: {
                progressBaohiem.setVisibility(View.GONE);
                imgNextBaohiem.setVisibility(View.GONE);
                imgResultBaohiem.setImageResource(R.drawable.ic_wait);
                imgResultBaohiem.setVisibility(View.VISIBLE);
                llRootBaohiem.setClickable(true);

                break;
            }
            case 4: {
                progressCmnd.setVisibility(View.GONE);
                imgNextCmnd.setVisibility(View.GONE);
                imgResultCmnd.setImageResource(R.drawable.ic_wait);
                imgResultCmnd.setVisibility(View.VISIBLE);
                llRootCmnd.setClickable(true);

                break;
            }
            case 5: {

                break;
            }
        }
    }

    private void changeStateToDeny(int type) {
        switch (type) {
            case 1: {
                progressBangLai.setVisibility(View.GONE);
                imgNextBangLai.setVisibility(View.GONE);
                imgResultBangLai.setImageResource(R.drawable.ic_error);
                imgResultBangLai.setVisibility(View.VISIBLE);
                llRootBangLai.setClickable(true);

                break;
            }
            case 2: {
                progressDangkyxe.setVisibility(View.GONE);
                imgNextDangkyxe.setVisibility(View.GONE);
                imgResultDangkyxe.setImageResource(R.drawable.ic_error);
                imgResultDangkyxe.setVisibility(View.VISIBLE);
                llRootBangLai.setClickable(true);

                break;
            }
            case 3: {

                progressCmnd.setVisibility(View.GONE);
                imgNextCmnd.setVisibility(View.GONE);
                imgResultCmnd.setImageResource(R.drawable.ic_error);
                imgResultCmnd.setVisibility(View.VISIBLE);
                llRootBangLai.setClickable(true);
                break;
            }
            case 4: {
                progressAvatar.setVisibility(View.GONE);
                imgNextAvatar.setVisibility(View.GONE);
                imgResultAvatar.setImageResource(R.drawable.ic_error);
                imgResultAvatar.setVisibility(View.VISIBLE);
                llRootBangLai.setClickable(true);

                break;
            }
            case 5: {
                progressLylich.setVisibility(View.GONE);
                imgNextLylich.setVisibility(View.GONE);
                imgResultLylich.setImageResource(R.drawable.ic_error);
                imgResultLylich.setVisibility(View.VISIBLE);
                llRootBangLai.setClickable(true);

                break;
            }

            case 7: {
                progressBaohiem.setVisibility(View.GONE);
                imgNextBaohiem.setVisibility(View.GONE);
                imgResultBaohiem.setImageResource(R.drawable.ic_error);
                imgResultBaohiem.setVisibility(View.VISIBLE);
                llRootBangLai.setClickable(true);
                break;
            }
//            case 8: {
//                progressBaohiemhanghoa.setVisibility(View.GONE);
//                imgNextBaohiemhanghoa.setVisibility(View.GONE);
//                imgResultBaohiemhanghoa.setImageResource(R.drawable.ic_error);
//                imgResultBaohiemhanghoa.setVisibility(View.VISIBLE);
//                llRootBangLai.setClickable(true);
//                break;
//            }
        }
    }

    private void changeStateToSuccess(int type) {
        switch (type) {
            case 1: {
                progressBangLai.setVisibility(View.GONE);
                imgNextBangLai.setVisibility(View.GONE);
                imgResultBangLai.setImageResource(R.drawable.ic_success);
                imgResultBangLai.setVisibility(View.VISIBLE);
                llRootBangLai.setClickable(false);
                break;
            }
            case 2: {
                progressDangkyxe.setVisibility(View.GONE);
                imgNextDangkyxe.setVisibility(View.GONE);
                imgResultDangkyxe.setImageResource(R.drawable.ic_success);
                imgResultDangkyxe.setVisibility(View.VISIBLE);
                llRootDangkyxe.setClickable(false);

                break;
            }
            case 3: {

                progressCmnd.setVisibility(View.GONE);
                imgNextCmnd.setVisibility(View.GONE);
                imgResultCmnd.setImageResource(R.drawable.ic_success);
                imgResultCmnd.setVisibility(View.VISIBLE);
                llRootBangLai.setClickable(false);
                break;
            }
            case 4: {
                progressAvatar.setVisibility(View.GONE);
                imgNextAvatar.setVisibility(View.GONE);
                imgResultAvatar.setImageResource(R.drawable.ic_success);
                imgResultAvatar.setVisibility(View.VISIBLE);
                llRootBangLai.setClickable(false);

                break;
            }
            case 5: {
                progressLylich.setVisibility(View.GONE);
                imgNextLylich.setVisibility(View.GONE);
                imgResultLylich.setImageResource(R.drawable.ic_success);
                imgResultLylich.setVisibility(View.VISIBLE);
                llRootBangLai.setClickable(false);

                break;
            }

            case 7: {
                progressBaohiem.setVisibility(View.GONE);
                imgNextBaohiem.setVisibility(View.GONE);
                imgResultBaohiem.setImageResource(R.drawable.ic_success);
                imgResultBaohiem.setVisibility(View.VISIBLE);
                llRootBangLai.setClickable(false);
                break;
            }
//            case 8: {
//                progressBaohiemhanghoa.setVisibility(View.GONE);
//                imgNextBaohiemhanghoa.setVisibility(View.GONE);
//                imgResultBaohiemhanghoa.setImageResource(R.drawable.ic_success);
//                imgResultBaohiemhanghoa.setVisibility(View.VISIBLE);
//                llRootBangLai.setClickable(false);
//                break;
//            }
        }
    }

    @Override
    public void getInforSucess() {
        boolean flagGo = true;
        if (AppController.getInstance().getmProfileUser().getAvatar() == null || !AppController.getInstance().getmProfileUser().getAvatar().isEmpty()) {
            changeStateToSuccess(1);

        } else {
            flagGo = false;
        }
        if (AppController.getInstance().getmProfileUser().getCmnd2() == null || !AppController.getInstance().getmProfileUser().getCmnd2().isEmpty()) {
            changeStateToSuccess(7);

        } else {
            flagGo = false;
        }
        if (AppController.getInstance().getmProfileUser().getCmnd1() == null || !AppController.getInstance().getmProfileUser().getCmnd1().isEmpty()) {
            changeStateToSuccess(2);
        } else {
            flagGo = false;
        }
//        if (AppController.getInstance().getmProfileUser().getGplx1() == null || !AppController.getInstance().getmProfileUser().getGplx1().isEmpty()) {
//            changeStateToSuccess(5);
//        } else {
//            flagGo = false;
//        }
//        if (AppController.getInstance().getmProfileUser().getGplx2() == null || !AppController.getInstance().getmProfileUser().getGplx2().isEmpty()) {
//            changeStateToSuccess(6);
//        } else {
//            flagGo = false;
//        }
        if (AppController.getInstance().getmProfileUser().getDkx() == null || !AppController.getInstance().getmProfileUser().getDkx().isEmpty()) {
            changeStateToSuccess(4);
        } else {
            flagGo = false;
        }
//        if (AppController.getInstance().getmProfileUser().getImg4() == null || !AppController.getInstance().getmProfileUser().getImg4().isEmpty()) {
//            changeStateToSuccess(8);
//        } else {
//            flagGo = false;
//        }
//        if (AppController.getInstance().getmProfileUser().getShk() == null || !AppController.getInstance().getmProfileUser().getShk().isEmpty()) {
//            changeStateToSuccess(4);
//        } else {
//            flagGo = false;
//        }

        if (flagGo) {
            startActivity(new Intent(UpdateActivity.this, StatusAccountActivity.class));
            finish();
        }

    }

    @Override
    public void uploadSucess(int type) {
        changeStateToSuccess(type);
        presenter.getInfor();
    }

    @Override
    public void uploadfail(int type) {
        changeStateToDeny(type);
    }

    @Override
    public Context getMyContext() {
        return this;
    }


    @Override
    public void showProgress() {
        dialogLoading.showDialog();
    }

    @Override
    public void hiddenProgress() {
        dialogLoading.hideDialog();

    }

    @Override
    public void onErrorApi(String message) {
        LogUtils.e(message);
    }

    @Override
    public void onError(String message) {
        LogUtils.e(message);

    }

    @Override
    public void onErrorAuthorization() {
        showDialogExpired();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
