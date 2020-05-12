package com.esselion.droplevendor.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esselion.droplevendor.Adapters.VendorAdaptor;
import com.esselion.droplevendor.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class VendorFragmentComp extends BaseFragment{
    FloatingActionButton btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.fragment_vendor, container, false);
        recyclerView = rootView.findViewById(R.id.vendorrv);
        myapivendor(rootView.getContext(), VendorAdaptor.completed);
        return rootView;
    }



}