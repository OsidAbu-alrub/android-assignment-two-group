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
import com.abualrub.androidassignmenttwogroup.FragmentRoot;
import com.abualrub.androidassignmenttwogroup.R;
import com.abualrub.androidassignmenttwogroup.domain.Item;
import com.google.gson.Gson;

import java.util.ArrayList;

// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> implements ITags {

    private ArrayList<Item> items;
    private ArrayList<Item> itemsCopy;
    private FragmentActivity fragmentActivity;

    public CheckoutAdapter(Item[] items, FragmentActivity fragmentActivity) {
        this.itemsCopy = new ArrayList<>();
        this.items = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            itemsCopy.add(items[i]);
            this.items.add(items[i]);
        }
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public CheckoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.home_cardview,
                        parent,
                        false);
        return new CheckoutAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.imageViewHome);
        Drawable dr = ContextCompat.getDrawable(cardView.getContext(), items.get(position).getIcon());
        imageView.setImageDrawable(dr);

        TextView textViewTitle = cardView.findViewById(R.id.textViewHomeTitle);
        TextView textViewRating = cardView.findViewById(R.id.textViewHomeRating);
        TextView textViewPrice = cardView.findViewById(R.id.textViewHomePrice);
        textViewTitle.setText(items.get(position).getTitle());
        textViewRating.setText(items.get(position).getRating() + "");
        textViewPrice.setText("$" + items.get(position).getPrice() + "");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}

