package com.skynet.chovietship.ui.main;

import android.util.SparseArray;
import android.view.ViewGroup;

import com.skynet.chovietship.ui.category.ListCategoryFragment;
import com.skynet.chovietship.ui.favourite.FavouriteFragment;
import com.skynet.chovietship.ui.home.HomeFragment;
import com.skynet.chovietship.ui.shop.ListShopFragment;
import com.skynet.chovietship.ui.wallet.FragmentWallet;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class AdapterMainViewpager extends FragmentStatePagerAdapter {
    SparseArray<Fragment> registeredFragments = new SparseArray<>();

    public AdapterMainViewpager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    @Override
    public Fragment getItem(int i) {

                return FragmentWallet.newInstance();


    }
}
