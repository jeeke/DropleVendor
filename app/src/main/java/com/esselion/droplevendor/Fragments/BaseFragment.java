package com.esselion.droplevendor.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esselion.droplevendor.Adapters.VendorListAdaptor;
import com.esselion.droplevendor.Models.Order;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BaseFragment extends Fragment implements VendorListAdaptor.VendorListListener {

    RecyclerView recyclerView;

    public BaseFragment() {
        // Required empty public constructor
    }

    public void myapivendor(Context context, ArrayList<Order> orders) {
        VendorListAdaptor mAdapter = new VendorListAdaptor(this,orders);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    ProgressDialog dlg;
    @Override
    public void onDoneClick(Order order) {
        dlg = new ProgressDialog(getContext());
        dlg.setTitle("Please wait....");
        dlg.show();
        placeOrder(order);
    }

    private void placeOrder(Order order) {
        String prevOrderRef = "PrevOrders/" + order.uid + "/" + order.id;
        String orderRef = "Orders/" + order.id;
        order.status = Integer.parseInt(order.status) + 1 + "";
        Map<String,Object> map = new HashMap<>();
        map.put(prevOrderRef, order);
        map.put(orderRef, order);

        FirebaseDatabase.getInstance().getReference().updateChildren(map)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        dlg.dismiss();
                    }else {
                        Toast.makeText(getContext(), "Couldn't be updated", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
