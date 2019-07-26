package com.skynet.chovietship.ui.views;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.skynet.chovietship.R;
import com.skynet.chovietship.models.History;
import com.squareup.picasso.Picasso;

import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogNewTrip extends AlertDialog {
    private History history;
    private Context context;
    @BindView(R.id.imageView12)
    ImageView imageView12;
    @BindView(R.id.imageView13)
    ImageView imageView13;
    @BindView(R.id.imageView14)
    ImageView imageView14;
    @BindView(R.id.btnSubmit3)
    Button btnSubmit3;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.driver_infor_avatar)
    ImageView driverInforAvatar;
    @BindView(R.id.circleCountdown)
    CircularProgressBar circleCountdown;
    @BindView(R.id.driver_info_time_remain)
    TextView driverInfoTimeRemain;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.driver_info_tvNameDriver)
    TextView driverInfoTvNameDriver;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.driver_info_tvTimeStart)
    TextView driverInfoTvTimeStart;
    @BindView(R.id.driver_info_tvTimeEnd)
    TextView driverInfoTvTimeEnd;
    @BindView(R.id.driver_info_tvAddressStart)
    TextView driverInfoTvAddressStart;
    @BindView(R.id.driver_info_tvAddressEnd)
    TextView driverInfoTvAddressEnd;
    @BindView(R.id.driver_info_tvDistanceEstiamte)
    TextView driverInfoTvDistanceEstiamte;
    @BindView(R.id.textView16)
    TextView textView16;
    @BindView(R.id.driver_info_tvTimeEstimate)
    TextView driverInfoTvTimeEstimate;
    @BindView(R.id.textView18)
    TextView textView18;
    @BindView(R.id.textView19)
    TextView textView19;
    @BindView(R.id.textView20)
    TextView textView20;
    @BindView(R.id.tvTypeCar)
    TextView tvTypeCar;
    @BindView(R.id.tvKm)
    TextView tvKm;
    @BindView(R.id.layoutDriverInfo)
    ConstraintLayout layoutDriverInfo;
    private ListenerDialogTrip listenerDialogTrip;
    CountDownTimer countDownTimer;

    public DialogNewTrip(Context context, ListenerDialogTrip listenerDialogTrip, History history) {
        super(context);
        this.listenerDialogTrip = listenerDialogTrip;
        this.history = history;
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.dialog_new_trip, null, false);
        setView(view);
        ButterKnife.bind(this,view);
        getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        bindView();
    }

    private void bindView() {
        if (history.getUser() != null) {
            if (history.getUser().getAvatar() != null && !history.getUser().getAvatar().isEmpty()) {
                Picasso.with(context).load(history.getUser().getAvatar()).fit().centerCrop().into(driverInforAvatar);
            }
            driverInfoTvNameDriver.setText(history.getUser().getName());
        }
        driverInfoTvAddressStart.setText(history.getStart());
        driverInfoTvAddressEnd.setText(history.getEnd());
        textView20.setText(String.format("%,.0fđ", history.getPrice_ship()));
        countDownTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                driverInfoTimeRemain.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                dismiss();
                listenerDialogTrip.onCanceled(history);
            }
        };
        countDownTimer.cancel();
    }

    @OnClick({R.id.btnSubmit3, R.id.btnCancel})
    public void onViewClicked(View view) {
        countDownTimer.cancel();
        switch (view.getId()) {
            case R.id.btnSubmit3:
                dismiss();
                listenerDialogTrip.onReceived(history);
                break;
            case R.id.btnCancel:
                dismiss();
                listenerDialogTrip.onCanceled(history);
                break;
        }
    }

    public interface ListenerDialogTrip {
        void onCanceled(History booking);

        void onReceived(History booking);
    }
}
