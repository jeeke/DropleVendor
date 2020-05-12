package com.esselion.droplevendor.Adapters;


import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.esselion.droplevendor.Models.Garment;
import com.esselion.droplevendor.R;

import java.util.ArrayList;

public class VendorClothesAdaptor extends RecyclerView.Adapter<VendorClothesAdaptor.ViewHolder>{

    private ArrayList<Garment> mGarments;
    public VendorClothesAdaptor(ArrayList<Garment> garments) {
        mGarments = garments;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.clothesdetail, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mGarments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView clothesdetail;
//        public ConstraintLayout deatilofclothes;
        public ViewHolder(View itemView) {
            super(itemView);
//            this.clothesdetail = (TextView) itemView.findViewById(R.id.clothesdetail);
//            this.deatilofclothes = itemView.findViewById(R.id.detailofclothes);
        }
    }


}

