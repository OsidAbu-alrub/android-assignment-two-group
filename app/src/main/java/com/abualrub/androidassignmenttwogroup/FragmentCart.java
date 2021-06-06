package com.abualrub.androidassignmenttwogroup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abualrub.androidassignmenttwogroup.domain.Cart;
import com.abualrub.androidassignmenttwogroup.domain.Item;
import com.abualrub.androidassignmenttwogroup.utils.CartAdapter;
import com.abualrub.androidassignmenttwogroup.utils.HomeAdapter;
import com.abualrub.androidassignmenttwogroup.utils.IConstants;
import com.abualrub.androidassignmenttwogroup.utils.ITags;
import com.google.gson.Gson;

// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public class FragmentCart extends FragmentRoot implements ITags, IConstants {

    private View rootView;
    private RecyclerView rv;
    private SharedPreferences prefs;
    private CartAdapter adapter;
    private Item[] items;
    private TextView textViewCartNoItems;
    private TextView textViewCartPriceWithoutTax;
    private TextView textViewCartTax;
    private TextView textViewCartWithTax;
    private Button buttonCheckout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        init();
        initButtonCheckoutHandleClick();
        return rootView;
    }

    private void init(){
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        textViewCartNoItems = rootView.findViewById(R.id.textViewCartNoItems);
        textViewCartPriceWithoutTax = rootView.findViewById(R.id.textViewCartPriceWithoutTax);
        textViewCartTax = rootView.findViewById(R.id.textViewCartTax);
        textViewCartWithTax = rootView.findViewById(R.id.textViewCartWithTax);
        buttonCheckout = rootView.findViewById(R.id.buttonCheckout);
        String jsonCart = prefs.getString(CART,null);

        if(jsonCart == null){
            emptyState();
            return;
        }
        Gson gson = new Gson();
        Cart cart = gson.fromJson(jsonCart,Cart.class);

        if(cart.getItems() == null){
            emptyState();
            return;
        }

        if(cart.getItems().size() == 0){
            emptyState();
            return;
        }

        items = new Item[cart.getItems().size()];
        int i = 0;
        for(Item item : cart.getItems().values()){
            items[i++] = item;
        }
        initRecyclerView();
        textViewCartTax.setText(cart.calculateTax());
        textViewCartWithTax.setText(cart.calculatePriceWithTax());
        textViewCartPriceWithoutTax.setText(cart.calculatePriceWithoutTax());
    }

    private void initRecyclerView(){
        rv = rootView.findViewById(R.id.recyclerViewCart);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CartAdapter(items,getActivity());
        rv.setAdapter(adapter);
    }

    private void initButtonCheckoutHandleClick(){
        buttonCheckout.setOnClickListener(e ->{
            openFragment(new FragmentCheckout(),false);
        });
    }

    private void emptyState(){
        textViewCartNoItems.setVisibility(View.VISIBLE);
        textViewCartPriceWithoutTax.setVisibility(View.GONE);
        textViewCartTax.setVisibility(View.GONE);
        textViewCartWithTax.setVisibility(View.GONE);
        buttonCheckout.setVisibility(View.GONE);
    }
}
