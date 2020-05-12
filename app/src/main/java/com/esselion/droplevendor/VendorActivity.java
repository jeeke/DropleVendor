package com.esselion.droplevendor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.esselion.droplevendor.Adapters.VendorAdaptor;
import com.esselion.droplevendor.Models.Order;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class VendorActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    VendorAdaptor vendorAdaptor;
    ArrayList<Order> mPendingOrders, mProgressOrders, mCompletedOrders;
    ProgressDialog dlg;
//    int previousFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);
        mPendingOrders = new ArrayList<>();
        mProgressOrders = new ArrayList<>();
        mCompletedOrders = new ArrayList<>();
        viewPager = findViewById(R.id.inductionpager);
        tabLayout = findViewById(R.id.tabLayout);
        dlg = new ProgressDialog(this);
        dlg.setTitle("Getting orders....");
        dlg.show();
        getOrders();
    }

    public void getOrders() {
//        previousFragment = viewPager.getCurrentItem();
        FirebaseDatabase.getInstance().
                getReference().child("Orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPendingOrders.clear();
                mProgressOrders.clear();
                mCompletedOrders.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Order order = d.getValue(Order.class);
                    try {
                        int status = Integer.parseInt(order.status);
                        switch (status) {
                            case 0:
                                mPendingOrders.add(order);
                                break;
                            case 1:
                                mProgressOrders.add(order);
                                break;
                            case 2:
                                mCompletedOrders.add(order);
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                vendorAdaptor = new VendorAdaptor(getSupportFragmentManager());
                vendorAdaptor.setOrders(mPendingOrders, mProgressOrders, mCompletedOrders);
                viewPager.setAdapter(vendorAdaptor);
                tabLayout.setupWithViewPager(viewPager);
//                viewPager.setCurrentItem(previousFragment);
                dlg.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                dlg.dismiss();
            }
        });
    }
}
