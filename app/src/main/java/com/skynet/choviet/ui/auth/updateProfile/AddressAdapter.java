package com.skynet.choviet.ui.auth.updateProfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.skynet.choviet.R;
import com.skynet.choviet.models.Address;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AddressAdapter extends ArrayAdapter<Address> {
    List<Address> list;
    Context context;

    public AddressAdapter(@NonNull Context context, int resource, @NonNull List<Address> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);

        TextView offTypeTv = (TextView) view.findViewById(R.id.tv);
        Address address = list.get(position);
        offTypeTv.setText(address.getName());
//        if (address.getType() == 1) {
//            callBackAddressAdapter.selectRegion(address);
//        } else if (address.getType() == 2) {
//            callBackAddressAdapter.selectCity(address);
//        } else {
//            callBackAddressAdapter.selectRegion(address);
//        }
        return view;
    }

    interface CallBackAddressAdapter {
        void selectRegion(Address address);

        void selectCity(Address address);

        void selectDistric(Address address);
    }
}
