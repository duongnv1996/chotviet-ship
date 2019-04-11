package com.skynet.Choviet.ui.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


import com.skynet.chovietship.R;

import androidx.annotation.NonNull;


/**
 * Created by thaopt on 8/28/17.
 */

public class DialogTwoButtonUtil extends Dialog {
    private TextView mContentTextView;
    private ImageView imageView;
    private TextView butonRight, btnLeft, title;
    private int mType = 0;
    private Context mContext;
    private DialogOneButtonClickListener mListener;

    public DialogTwoButtonUtil(@NonNull Context context) {
        super(context);
        this.mContext = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_new_trip);
        getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        initView();
    }

    private void initView() {

    }

    //callback

    public interface DialogOneButtonClickListener {
        void okClick();
    }


    public void setDialogOneButtonClick(DialogOneButtonClickListener listener) {
        this.mListener = listener;
    }
}
