package com.skynet.chovietship.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skynet.chovietship.R;
import com.skynet.chovietship.interfaces.ICallback;
import com.skynet.chovietship.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterSuggession extends RecyclerView.Adapter<AdapterSuggession.ViewHolder> {
    List<Product> list;
    Context context;
    ICallback iCallback;


    public AdapterSuggession(List<Product> list, Context context, ICallback iCallback) {
        this.list = list;
        this.context = context;
        this.iCallback = iCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_suggession_home, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tvName.setText(list.get(i).getName());
        viewHolder.tvContent.setText(list.get(i).getContent());
        viewHolder.tvStar.setText(list.get(i).getStar() + "");
        Picasso.with(context).load(list.get(i).getImg()).fit().centerCrop().into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iCallback.onCallBack(i);
            }
        });
        viewHolder.textView54.setText(String.format("%,.0fđ", list.get(i).getPrice()));
        viewHolder.textView55.setText(String.format("%d/%s", list.get(i).getMin_buy(), list.get(i).getName_unit()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.cardView)
        CardView cardView;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.imageView19)
        ImageView imageView19;
        @BindView(R.id.tvStar)
        TextView tvStar;
        @BindView(R.id.textView54)
        TextView textView54;
        @BindView(R.id.textView55)
        TextView textView55;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
