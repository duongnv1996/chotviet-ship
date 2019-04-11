package com.skynet.chovietship.ui.history;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.chovietship.R;
import com.skynet.chovietship.interfaces.ICallback;
import com.skynet.chovietship.models.History;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {
    List<History> list;
    Context context;
    ICallback iCallback;
    SparseBooleanArray cacheAlreadyBook;
    SparseBooleanArray cacheWaiting;
    SparseBooleanArray cacheDone;
    SparseBooleanArray cacheCancle;


    public AdapterProduct(List<History> list, Context context, ICallback iCallback) {
        this.list = list;
        this.context = context;
        this.iCallback = iCallback;
        this.cacheAlreadyBook = new SparseBooleanArray();
        this.cacheWaiting = new SparseBooleanArray();
        this.cacheDone = new SparseBooleanArray();
        this.cacheCancle = new SparseBooleanArray();
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getActive()) {
                case 1: {
                    list.get(i).setActiveString("Đã nhận");
                    cacheAlreadyBook.put(i, true);
                    break;
                }
                case 2: {
                    list.get(i).setActiveString("Đang chờ giao");
                    cacheWaiting.put(i, true);

                    break;
                }
                case 3: {
                    list.get(i).setActiveString("Đã giao");
                    cacheDone.put(i, true);
                    break;
                }
                case 4: {
                    list.get(i).setActiveString("Đã huỷ");
                    cacheCancle.put(i, true);
                    break;
                }
                case 5: {
                    list.get(i).setActiveString("Hoàn thành");
                    cacheDone.put(i, true);
                    break;
                }
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.get(position).getUser().getAvatar() != null && !list.get(position).getUser().getAvatar().isEmpty()) {
            Picasso.with(context).load(list.get(position).getUser().getAvatar()).into(holder.imgAvt);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallback.onCallBack(position);
            }
        });
        holder.tvDate.setText(list.get(position).getDate_booking());
        holder.tvName.setText(list.get(position).getName());
        holder.tvTimeReceive.setText(list.get(position).getTime_receive());
        holder.tvTimeShip.setText(list.get(position).getTime_finish());
        holder.tvID.setText("Đơn hàng #" + list.get(position).getId());
        holder.tvState.setText(list.get(position).getActiveString());
        if (cacheAlreadyBook.get(position)) {
            holder.tvState.setTextColor(ContextCompat.getColor(context, R.color.orage));
            holder.tvState.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getDrawable(R.drawable.ic_busy), null, null, null);
        } else if (cacheWaiting.get(position)) {
            holder.tvState.setTextColor(ContextCompat.getColor(context, R.color.orage));
            holder.tvState.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getDrawable(R.drawable.ic_busy), null, null, null);
        } else if (cacheDone.get(position)) {
            holder.tvState.setTextColor(ContextCompat.getColor(context, R.color.green));
            holder.tvState.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getDrawable(R.drawable.ic_checked), null, null, null);
        } else if (cacheCancle.get(position)) {
            holder.tvState.setTextColor(ContextCompat.getColor(context, R.color.red));
            holder.tvState.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getDrawable(R.drawable.ic_cancel), null, null, null);
        } else {
            holder.tvState.setTextColor(ContextCompat.getColor(context, R.color.orage));
            holder.tvState.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getDrawable(R.drawable.ic_busy), null, null, null);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clearCache() {
        for (int i = 0; i < list.size(); i++) {
            switch (list.get(i).getActive()) {
                case 1: {
                    list.get(i).setActiveString("Đã đặt");
                    cacheAlreadyBook.put(i, true);
                    break;
                }
                case 2: {
                    list.get(i).setActiveString("Đang chờ giao");
                    cacheWaiting.put(i, true);

                    break;
                }
                case 3: {
                    list.get(i).setActiveString("Đã giao");
                    cacheDone.put(i, true);
                    break;
                }
                case 4: {
                    list.get(i).setActiveString("Đã huỷ");
                    cacheCancle.put(i, true);
                    break;
                }
                case 5: {
                    list.get(i).setActiveString("Đã nhận");
                    cacheDone.put(i, true);
                    break;
                }
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView65)
        TextView textView65;
        @BindView(R.id.tvTimeReceive)
        TextView tvTimeReceive;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.textView69)
        TextView tvTimeShip;
        @BindView(R.id.tvTimeShip)
        TextView textView69;
        @BindView(R.id.imageView26)
        ImageView imageView26;
        @BindView(R.id.tvID)
        TextView tvID;
        @BindView(R.id.view10)
        View view10;
        @BindView(R.id.imgAvt)
        CircleImageView imgAvt;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvState)
        TextView tvState;
        @BindView(R.id.textView73)
        TextView textView73;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
