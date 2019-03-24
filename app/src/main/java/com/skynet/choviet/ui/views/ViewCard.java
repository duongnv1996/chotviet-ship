package com.skynet.choviet.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skynet.choviet.R;

import cn.lightsky.infiniteindicator.ImageLoader;
import cn.lightsky.infiniteindicator.OnPageClickListener;
import cn.lightsky.infiniteindicator.Page;
import cn.lightsky.infiniteindicator.recycle.ViewBinder;

public class ViewCard implements ViewBinder {
    @Override
    public View bindView(Context context, int position, Page page, ImageLoader imageLoader, OnPageClickListener onPageClickListener, View convertView, ViewGroup container) {
        return LayoutInflater.from(context).inflate(R.layout.card_home_banner, container, false);
    }
}
