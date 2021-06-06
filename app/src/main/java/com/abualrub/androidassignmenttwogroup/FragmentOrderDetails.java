package com.abualrub.androidassignmenttwogroup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abualrub.androidassignmenttwogroup.domain.Cart;
import com.abualrub.androidassignmenttwogroup.domain.Item;
import com.abualrub.androidassignmenttwogroup.domain.Order;
import com.abualrub.androidassignmenttwogroup.utils.HomeAdapter;
import com.abualrub.androidassignmenttwogroup.utils.IConstants;
import com.abualrub.androidassignmenttwogroup.utils.ITags;
import com.abualrub.androidassignmenttwogroup.utils.OrderAdapter;
import com.google.gson.Gson;


// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public class FragmentOrderDetails extends FragmentRoot implements ITags, IConstants {
    private RecyclerView rv;
    private View rootView;
    private SharedPreferences prefs;
    private HomeAdapter adapter;
    private Cart cart;
    private Item[] items;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_order_details, container, false);
        init();
        return rootView;
    }

    private void init(){
        Gson gson = new Gson();
        Bundle bundle = this.getArguments();
        cart = gson.fromJson(bundle.getString(CART, null), Cart.class);
        if(cart == null) return;
        items = new Item[cart.getItems().size()];
        int i = 0;
        for(Item item : cart.getItems().values()){
            items[i++] = item;
        }
        initRecyclerView();
    }

    private void initRecyclerView(){
        rv = rootView.findViewById(R.id.recyclerViewOrderDetails);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeAdapter(items,getActivity());
        rv.setAdapter(adapter);
    }

}
