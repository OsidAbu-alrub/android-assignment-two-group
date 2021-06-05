package com.abualrub.androidassignmenttwogroup.domain;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.abualrub.androidassignmenttwogroup.R;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.myViewHolder> {

  //  Context context;
   private String title[];
    private int image[];
    private double[] price;
    private double[] rate;


    public ListViewAdapter( String[] title, double[] price, double[] rate, int[] image) {

        this.title=title;
        this.price=price;
        this.rate=rate;
        this.image=image;

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        CardView cardView= holder.cardView;
        ImageView imageView=(ImageView) cardView.findViewById(R.id.mainIcon);
        Drawable dr= ContextCompat.getDrawable(cardView.getContext(),image[position]);
        imageView.setImageDrawable(dr);

        TextView itemTitle= (TextView) cardView.findViewById(R.id.mainTitle);
        TextView itemPrice= (TextView) cardView.findViewById(R.id.Salary);
        TextView itemRate= (TextView) cardView.findViewById(R.id.rate);

        itemTitle.setText(title[position]);
        itemPrice.setText(price[position]+"");
        itemRate.setText(rate[position]+"");

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return image.length;
    }


    public class myViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;

        public myViewHolder(CardView cardView) {
            super(cardView);
            this.cardView=cardView;

        }
    }
}

