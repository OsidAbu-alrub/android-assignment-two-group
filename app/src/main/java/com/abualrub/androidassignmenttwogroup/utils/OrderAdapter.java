package com.abualrub.androidassignmenttwogroup.utils;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.abualrub.androidassignmenttwogroup.FragmentDetails;
import com.abualrub.androidassignmenttwogroup.FragmentOrderDetails;
import com.abualrub.androidassignmenttwogroup.FragmentRoot;
import com.abualrub.androidassignmenttwogroup.R;
import com.abualrub.androidassignmenttwogroup.domain.Cart;
import com.abualrub.androidassignmenttwogroup.domain.Item;
import com.abualrub.androidassignmenttwogroup.domain.Order;
import com.google.gson.Gson;

import java.util.ArrayList;

// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> implements ITags {

    private ArrayList<Cart> carts;
    private Order order;
    private FragmentActivity fragmentActivity;

    public OrderAdapter(Order order, FragmentActivity fragmentActivity) {
        this.carts = new ArrayList<>();
        this.carts.addAll(order.getCarts());
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.order_cardview,
                        parent,
                        false);
        return new OrderAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView textViewOrderWithoutTax = cardView.findViewById(R.id.textViewCardViewOrderPriceWithoutTax);
        TextView textViewOrderTax = cardView.findViewById(R.id.textViewCardViewOrderTax);
        TextView  textViewOrderWithTax = cardView.findViewById(R.id.textViewCardViewOrderPriceWithTax);
        TextView textViewCardViewOrderDate = cardView.findViewById(R.id.textViewCardViewOrderDate);
        textViewOrderWithoutTax.setText(carts.get(position).calculatePriceWithoutTax());
        textViewOrderTax.setText(carts.get(position).calculateTax());
        textViewOrderWithTax.setText(carts.get(position).calculatePriceWithTax());
        textViewCardViewOrderDate.setText("Order Date: "+carts.get(position).getDate());
        cardView.setOnClickListener(e -> {
            Gson gson = new Gson();
            String jsonCart = gson.toJson(carts.get(position));
            FragmentOrderDetails fragmentOrderDetails = new FragmentOrderDetails();
            Bundle bundle = new Bundle();
            bundle.putString(CART, jsonCart);
            fragmentOrderDetails.setArguments(bundle);
            new FragmentRoot().openFragment(fragmentActivity, fragmentOrderDetails, false);
        });

    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}