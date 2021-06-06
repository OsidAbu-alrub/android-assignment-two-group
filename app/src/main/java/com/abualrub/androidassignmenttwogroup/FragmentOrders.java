package com.abualrub.androidassignmenttwogroup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abualrub.androidassignmenttwogroup.domain.Order;
import com.abualrub.androidassignmenttwogroup.utils.CartAdapter;
import com.abualrub.androidassignmenttwogroup.utils.ITags;
import com.abualrub.androidassignmenttwogroup.utils.OrderAdapter;
import com.google.gson.Gson;

// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public class FragmentOrders extends FragmentRoot implements ITags {
    private TextView textViewOrdersNoOrders;
    private OrderAdapter adapter;
    private RecyclerView rv;
    private View rootView;
    private SharedPreferences prefs;
    private Order order;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_order, container, false);
        init();
        return rootView;
    }

    private void init(){
        Gson gson = new Gson();
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        textViewOrdersNoOrders = rootView.findViewById(R.id.textViewOrdersNoOrders);
        order = gson.fromJson(prefs.getString(ORDER,null),Order.class);
        if(order == null){
            textViewOrdersNoOrders.setVisibility(View.VISIBLE);
            return;
        }
        initRecyclerView();
    }

    private void initRecyclerView(){
        rv = rootView.findViewById(R.id.recyclerViewOrders);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new OrderAdapter(order,getActivity());
        rv.setAdapter(adapter);
    }
}
