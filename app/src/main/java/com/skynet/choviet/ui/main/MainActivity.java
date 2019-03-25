package com.skynet.choviet.ui.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.skynet.choviet.R;
import com.skynet.choviet.application.AppController;
import com.skynet.choviet.interfaces.FragmentCallBackTitle;
import com.skynet.choviet.models.Notification;
import com.skynet.choviet.models.Profile;
import com.skynet.choviet.ui.auth.updateProfile.ActivityProfileUpdate;
import com.skynet.choviet.ui.base.BaseActivity;
import com.skynet.choviet.ui.cart.CartActivity;
import com.skynet.choviet.ui.contact.ContactUsActivity;
import com.skynet.choviet.ui.history.ListHistoryActivity;
import com.skynet.choviet.ui.listchat.ListChatActivity;
import com.skynet.choviet.ui.news.NotificationActivity;
import com.skynet.choviet.ui.profile.ProfileActivity;
import com.skynet.choviet.ui.scanqr.ScannerQr;
import com.skynet.choviet.ui.search.searchListProduct.SearchProductActivity;
import com.skynet.choviet.ui.views.ViewpagerNotSwipe;
import com.skynet.choviet.utils.AlarmUtils;
import com.skynet.choviet.utils.AppConstant;
import com.squareup.picasso.Picasso;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import q.rorbin.badgeview.Badge;

public class MainActivity extends BaseActivity implements OptionBottomSheet.MoreOptionCallback, ContactContract.View, FragmentCallBackTitle {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.viewpager)
    ViewpagerNotSwipe viewpager;
    @BindView(R.id.bnve)
    RadioGroup radGroup;

    @BindView(R.id.layoutRootViewpager)
    FrameLayout layoutRootViewpager;
    @BindView(R.id.imgAvatarProfile)
    CircleImageView imgAvatarProfile;
    @BindView(R.id.tvNameProfile)
    TextView tvNameProfile;
    @BindView(R.id.nav_home)
    LinearLayout navHome;
    @BindView(R.id.nav_fav)
    LinearLayout navFav;
    @BindView(R.id.nav_cart)
    LinearLayout navCart;
    @BindView(R.id.nav_history)
    LinearLayout navHistory;
    @BindView(R.id.nav_message)
    LinearLayout navMessage;
    @BindView(R.id.nav_news)
    LinearLayout navNews;
    @BindView(R.id.nav_help)
    LinearLayout navHelp;
    @BindView(R.id.nav_customer)
    LinearLayout navCustomer;
    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.imgRight)
    ImageView imgRight;
    @BindView(R.id.viewCart)
    View viewCart;
    @BindView(R.id.viewHistory)
    View viewHistory;
    @BindView(R.id.viewChat)
    View viewChat;
    @BindView(R.id.viewNews)
    View viewNews;
    private AdapterMainViewpager adapter;
    private boolean doubleBackToExitPressedOnce;
    private Badge badge;
    private ContactContract.Presenter presenter;
    private OptionBottomSheet optionBottomSheet;
    private OptionBottomSheet bottomAddFriendRequest;
    private String userIdRequestFriend;
    private OptionBottomSheet.MoreOptionCallback addFriendCallback = new OptionBottomSheet.MoreOptionCallback() {
        @Override
        public void onMoreOptionCallback() {
            if (userIdRequestFriend != null) {
            }
        }
    };
    private FusedLocationProviderClient mFusedLocationClient;
    private LatLng myLatlng;

    @Override
    protected int initLayout() {
        StatusBarUtil.setTransparent(this);
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables() {
//        showDialogExpired();
        bottomAddFriendRequest = new OptionBottomSheet(this, addFriendCallback);

        if (getIntent() != null && getIntent().getExtras() != null) {
            String data = getIntent().getExtras().getString(AppConstant.NOTIFICATION_SOCKET);
            if (data != null) {
//
            }
        }
        presenter = new ContactPresenter(this);
        optionBottomSheet = new OptionBottomSheet(this, this);
        presenter.updateToken();
        presenter.getNoti();
        radGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                imgRight.setImageResource(R.drawable.ic_qrcode);
                switch (checkedId) {
                    case R.id.btmNewest: {
                        viewpager.setCurrentItem(0);
                        findViewById(R.id.include5).setVisibility(View.GONE);
                        setTilte("Sản phẩm mới");
                        break;
                    }
                    case R.id.btmShop: {
                        findViewById(R.id.include5).setVisibility(View.VISIBLE);
                        viewpager.setCurrentItem(1);
                        imgRight.setImageResource(R.drawable.ic_search_toolbar);
                        setTilte("Chuỗi cửa hàng");
                        break;
                    }
                    case R.id.btmCategory: {
                        findViewById(R.id.include5).setVisibility(View.VISIBLE);
                        viewpager.setCurrentItem(2);
                        setTilte("Ngành hàng");
                        imgRight.setImageResource(R.drawable.ic_search_toolbar);
//                        startActivity(new Intent(MainActivity.this, C.class));
                        break;
                    }
                    case R.id.btmFav: {
                        findViewById(R.id.include5).setVisibility(View.VISIBLE);
                        viewpager.setCurrentItem(3);
                        setTilte("Yêu thích");
//                        startActivity(new Intent(MainActivity.this, SearchProductActivity.class));
                        break;
                    }
                }
            }
        });
        bindUserData();
        radGroup.check(R.id.btmNewest);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
            }
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 1001);
            }
            return;
        }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            myLatlng = new LatLng(location.getLatitude(), location.getLongitude());
                            AppController.getInstance().getmSetting().put("latlng", new Gson().toJson(myLatlng));
                            updateLatlng();

                        }
                    }
                });
    }

    private void updateLatlng() {
        presenter.updateLatlng(myLatlng.latitude, myLatlng.longitude);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1001 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                myLatlng = new LatLng(location.getLatitude(), location.getLongitude());
                                AppController.getInstance().getmSetting().put("latlng", new Gson().toJson(myLatlng));
                                updateLatlng();
                            }
                        }
                    });
        }
    }

    @Override
    public void onSocketConnected() {
        super.onSocketConnected();

    }


    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, null, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        ;
        viewpager.setAdapter(new AdapterMainViewpager(getSupportFragmentManager()));
        viewpager.setPagingEnabled(false);
//        viewpager.setCurrentItem(1);
    }


    @Override
    protected void onResume() {
        super.onResume();
        int count = AppController.getInstance().getmProfileUser().getNoty();
//        if (count > 0)
//            addBadgeAt(2, count);
//        else if (badge != null)
//            badge.hide(true);
        if (count > 0) {
//            ((RadioButton) findViewById(R.id.btmCategory)).setCompoundDrawablesRelativeWithIntrinsicBounds(null,
//                    getDrawable(R.drawable.seletor_has_noti), null, null);
            viewNews.setVisibility(View.VISIBLE);
        } else {
//            ((RadioButton) findViewById(R.id.btmCategory)).setCompoundDrawablesRelativeWithIntrinsicBounds(null,
//                    getDrawable(R.drawable.seletor_category), null, null);
            viewNews.setVisibility(View.GONE);
        }

        if (AppController.getInstance().getmProfileUser().getMessage() > 0) {
            viewChat.setVisibility(View.VISIBLE);
        } else {
            viewChat.setVisibility(View.GONE);
        }
        if (AppController.getInstance().getmProfileUser().getNumber() > 0) {
            viewHistory.setVisibility(View.VISIBLE);
        } else {
            viewHistory.setVisibility(View.GONE);
        }
        if (AppController.getInstance().getmProfileUser().getIs_cart() > 0) {
            viewCart.setVisibility(View.VISIBLE);
        } else {
            viewCart.setVisibility(View.GONE);
        }
        if ((AppController.getInstance().getmProfileUser().getName().isEmpty() || AppController.getInstance().getmProfileUser().getEmail().isEmpty()) && !AppController.getInstance().getmSetting().getBoolean("show")) {
            startActivityForResult(new Intent(MainActivity.this, ActivityProfileUpdate.class), 1001);
        }
    }

    private void addBadgeAt(int position, int number) {
        // add badge
//        if (badge == null)
//            badge = new QBadgeView(this)
//                    .setBadgeNumber(number)
//                    .setGravityOffset(12, 2, true)
//                    .bindTarget(bnve.getBottomNavigationItemView(position))
//                    .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
//                        @Override
//                        public void onDragStateChanged(int dragState, Badge badge, View targetView) {
////                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
////                            Toast.makeText(BadgeViewActivity.this, R.string.tips_badge_removed, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        else
//            badge.setBadgeNumber(number);
    }

    @Override
    protected int initViewSBAnchor() {
        return 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

        ButterKnife.bind(this);
    }


    private void bindUserData() {
        Profile profile = AppController.getInstance().getmProfileUser();
        if (profile != null) {
            tvNameProfile.setText(profile.getName());
            if (profile.getAvatar() != null && !profile.getAvatar().isEmpty()) {
                Picasso.with(this).load(profile.getAvatar()).fit().centerCrop().into(imgAvatarProfile);
            }

        } else {
            return;
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Nhấn BACK 2 lần để thoát", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);

        }
    }

    @Override
    public void onMoreOptionCallback() {
//        if (bnve.getCurrentItem() == 1)

    }


    @Override
    public Context getMyContext() {
        return null;
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
    public void setTilte(String title) {
        tvTitleToolbar.setText(title);
    }

    @Override
    public void tranToTab(int tabPosition) {
        switch (tabPosition) {
            case 0: {
                viewpager.setCurrentItem(0);
                findViewById(R.id.include5).setVisibility(View.GONE);
                setTilte("Sản phẩm mới");
                break;
            }
            case 1: {
                findViewById(R.id.include5).setVisibility(View.VISIBLE);
                viewpager.setCurrentItem(1);
                imgRight.setImageResource(R.drawable.ic_search_toolbar);
                setTilte("Chuỗi cửa hàng");
                break;
            }
            case 2: {
                findViewById(R.id.include5).setVisibility(View.VISIBLE);
                viewpager.setCurrentItem(2);
                setTilte("Ngành hàng");
                imgRight.setImageResource(R.drawable.ic_search_toolbar);
//                        startActivity(new Intent(MainActivity.this, C.class));
                break;
            }
            case 3: {
                findViewById(R.id.include5).setVisibility(View.VISIBLE);
                viewpager.setCurrentItem(3);
                setTilte("Yêu thích");
//                        startActivity(new Intent(MainActivity.this, SearchProductActivity.class));
                break;
            }
        }
    }

    @OnClick({R.id.nav_home, R.id.nav_fav, R.id.nav_cart, R.id.nav_history, R.id.nav_message, R.id.nav_news, R.id.nav_help, R.id.nav_setting, R.id.nav_intro, R.id.nav_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.nav_home:
                radGroup.check(R.id.btmNewest);
                break;
            case R.id.nav_fav:
                radGroup.check(R.id.btmFav);
                break;
            case R.id.nav_cart:
                startActivity(new Intent(MainActivity.this, CartActivity.class));
                break;
            case R.id.nav_history:
                startActivity(new Intent(MainActivity.this, ListHistoryActivity.class));

                break;
            case R.id.nav_message:
                startActivity(new Intent(MainActivity.this, ListChatActivity.class));
                break;
            case R.id.nav_news:
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));

                break;
            case R.id.nav_help:
                startActivity(new Intent(MainActivity.this, ContactUsActivity.class));

                break;
            case R.id.nav_setting:
                startActivityForResult(new Intent(MainActivity.this, ProfileActivity.class), 1000);

                break;
            case R.id.nav_intro:
                startActivity(new Intent(MainActivity.this, ContactUsActivity.class));

                break;
            case R.id.nav_share:
//                Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                shareIntent.setType("text/plain");
//                shareIntent.putExtra(Intent.EXTRA_TEXT, "http://Choviet.vn/");
//                startActivity(Intent.createChooser(shareIntent, "Share link using"));
                break;
        }
        drawerLayout.closeDrawer(Gravity.LEFT);

    }

    public void openMenu() {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    @OnClick({R.id.imgHome, R.id.imgRight, R.id.imageView9})
    public void onViewToolbarClicked(View view) {
        switch (view.getId()) {
            case R.id.imgHome:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.imgRight:
                if (viewpager.getCurrentItem() == 3) {
                    RxPermissions rxPermissions = new RxPermissions(this);
                    rxPermissions.request(Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Boolean aBoolean) {
                            if (aBoolean) {
                                startActivity(new Intent(MainActivity.this, ScannerQr.class));
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
                } else {
                    startActivityForResult(new Intent(MainActivity.this, SearchProductActivity.class), 1000);
                }
                break;
            case R.id.imageView9:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
        }
    }

    @OnClick({R.id.imgAvatarProfile, R.id.tvNameProfile})
    public void onViewProfileClicked(View view) {
        startActivityForResult(new Intent(MainActivity.this, ProfileActivity.class), 1000);
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            bindUserData();
        }
    }

    @Override
    public void onSucessGetNotification(List<Notification> list) {
        for (Notification no : list) {
            AlarmUtils.create(this, no.getTime_noty(), no.getTitle(), no.getName());
        }
    }
}
