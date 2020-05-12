package com.esselion.droplevendor.Adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.esselion.droplevendor.Fragments.BaseFragment;
import com.esselion.droplevendor.Fragments.VendorFragmentComp;
import com.esselion.droplevendor.Fragments.VendorFragmentPending;
import com.esselion.droplevendor.Fragments.VendorFragmentProgress;
import com.esselion.droplevendor.Models.Order;

import java.util.ArrayList;

public class VendorAdaptor extends FragmentPagerAdapter {

    private String[] titles = {"Pending", "In Progress", "Completed"};

    public VendorAdaptor(FragmentManager fm) {
        super(fm);
    }

    public static ArrayList<Order> pending, progress, completed;

    public void setOrders(ArrayList<Order> pending, ArrayList<Order> progress, ArrayList<Order> completed) {
        VendorAdaptor.pending = pending;
        VendorAdaptor.progress = progress;
        VendorAdaptor.completed = completed;
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment;
        if (position == 0) {
            fragment = new VendorFragmentPending();
        } else if (position == 1) {
            fragment = new VendorFragmentProgress();
        } else {
            fragment = new VendorFragmentComp();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}