package com.skynet.choviet.ui.home;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.choviet.R;
import com.skynet.choviet.application.AppController;
import com.skynet.choviet.interfaces.ICallback;
import com.skynet.choviet.models.Auction;
import com.skynet.choviet.models.News;
import com.skynet.choviet.utils.DateTimeUtil;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;

public class AdapterAuction extends RecyclerView.Adapter<AdapterAuction.ViewHolder> {
    List<Auction> list;
    Context context;
    callbackAuction iCallback;


    public AdapterAuction(List<Auction> list, Context context, callbackAuction iCallback) {
        this.list = list;
        this.context = context;
        this.iCallback = iCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_auction, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.get(position).getImg() != null && !list.get(position).getImg().isEmpty()) {
            Picasso.with(context).load(list.get(position).getImg()).fit().centerCrop().into(holder.img);
        }
        holder.tvTitle.setText(list.get(position).getProduct_name());
        holder.tvPriceNow.setText(String.format("%,.0fđ", list.get(position).getLast_price()));
        holder.tvPriceStep.setText(String.format("%,.0fđ", list.get(position).getStep_price()));
        long timeEnd = AppController.getInstance().getmSetting().getInt("timeCountdown", 60);
        holder.countdownView.start(timeEnd * 1000);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallback.onCallBack(position);
            }
        });
        holder.btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallback.onSetPriceAuction(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.textView56)
        TextView textView56;
        @BindView(R.id.tvPriceNow)
        TextView tvPriceNow;
        @BindView(R.id.textView58)
        TextView textView58;
        @BindView(R.id.tvPriceStep)
        TextView tvPriceStep;
        @BindView(R.id.btnJoin)
        TextView btnJoin;
        @BindView(R.id.countdownView)
        CountdownView countdownView;
        @BindView(R.id.imageView24)
        ImageView imageView24;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface callbackAuction extends ICallback {
        void onSetPriceAuction(int pos);

    }
}
