package com.esselion.droplevendor.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esselion.droplevendor.Models.Order;
import com.esselion.droplevendor.R;

import java.util.ArrayList;

public class VendorListAdaptor extends RecyclerView.Adapter<VendorListAdaptor.ViewHolder> {

    public interface VendorListListener {
        void onDoneClick(Order order);
    }

    private VendorListListener mListener;
    private ArrayList<Order> mOrders;

    public VendorListAdaptor(VendorListListener listener, ArrayList<Order> orders) {
        this.mListener = listener;
        mOrders = orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.vendorcard, parent, false);
        return new ViewHolder(listItem);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.setItem(mOrders.get(position));
        holder.clothesdetail.setOnClickListener(v -> {
            if (holder.deatilofclothes.getVisibility() == View.GONE) {
                VendorClothesAdaptor mAdapter = new VendorClothesAdaptor(mOrders.get(position).garments);//response.body().getData());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(holder.itemView.getContext());
                holder.recyclerView.setLayoutManager(mLayoutManager);
                holder.recyclerView.setAdapter(mAdapter);
                holder.deatilofclothes.setVisibility(View.VISIBLE);
            } else {
                holder.deatilofclothes.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView clothesdetail,name,phone,count,price,hostel;
        ConstraintLayout deatilofclothes;
        RecyclerView recyclerView;

        ViewHolder(View itemView) {
            super(itemView);
            this.clothesdetail = itemView.findViewById(R.id.clothesdetail);
            this.deatilofclothes = itemView.findViewById(R.id.detailofclothes);
            this.recyclerView = itemView.findViewById(R.id.clothesrv);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            count = itemView.findViewById(R.id.count);
            price = itemView.findViewById(R.id.price);
            hostel = itemView.findViewById(R.id.hostel);
            itemView.findViewById(R.id.done).setOnClickListener(this);
        }

        void setItem(Order order){
            name.setText(order.address.name);
            phone.setText(order.address.phone);
            hostel.setText(order.address.hostel + ", Room No :" + order.address.room);
            count.setText(order.count+"");
            price.setText(order.cost);
        }

        @Override
        public void onClick(View v) {
            mListener.onDoneClick(mOrders.get(getAdapterPosition()));
        }
    }


}

