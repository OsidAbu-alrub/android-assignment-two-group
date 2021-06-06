package com.abualrub.androidassignmenttwogroup;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.abualrub.androidassignmenttwogroup.domain.Cart;
import com.abualrub.androidassignmenttwogroup.domain.Item;
import com.abualrub.androidassignmenttwogroup.utils.ITags;
import com.google.gson.Gson;


// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public class FragmentDetails extends FragmentRoot implements ITags {

    private View rootView;
    private Button buttonAddToCart;
    private Button buttonRemoveFromCart;
    private TextView textViewDetailsTitle;
    private TextView textViewDetailsRating;
    private TextView textViewDetailsPrice;
    private ImageView imageViewDetails;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Item item;
    private Cart cart;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_details, container, false);
        init();
        fillFragmentViews();
        initAddToCartButton();
        initRemoveFromCartButton();
        return rootView;
    }

    private void init(){
        Gson gson = new Gson();
        Bundle bundle = this.getArguments();
        item = gson.fromJson(bundle.getString(ITEM, null),Item.class);
        buttonAddToCart = rootView.findViewById(R.id.buttonAddToCart);
        buttonRemoveFromCart = rootView.findViewById(R.id.buttonRemoveFromCart);
        textViewDetailsTitle = rootView.findViewById(R.id.textViewDetailsTitle);
        textViewDetailsRating = rootView.findViewById(R.id.textViewDetailsRating);
        textViewDetailsPrice = rootView.findViewById(R.id.textViewDetailsPrice);
        imageViewDetails = rootView.findViewById(R.id.imageViewDetails);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor =  prefs.edit();

        boolean isLoggedIn = prefs.getBoolean(IS_LOGGED_IN,false);
        if(!isLoggedIn){
            buttonAddToCart.setEnabled(false);
            buttonRemoveFromCart.setEnabled(false);
            Toast.makeText(getActivity(), "Must Login To Add To Cart", Toast.LENGTH_LONG).show();
            return;
        }

        String jsonCart = prefs.getString(CART,null);
        if(jsonCart == null){
            cart = new Cart();
            buttonRemoveFromCart.setEnabled(false);
            return;
        }
        cart = gson.fromJson(jsonCart,Cart.class);
        if(cart.getItems().containsKey(item.getIcon())){
            buttonAddToCart.setEnabled(false);
        }
        else{
            buttonRemoveFromCart.setEnabled(false);
        }
    }

    private void fillFragmentViews(){
        textViewDetailsTitle.setText(item.getTitle());
        textViewDetailsPrice.setText("$"+item.getPrice());
        textViewDetailsRating.setText(item.getRating()+"");
        Drawable dr = ContextCompat.getDrawable(getContext(),item.getIcon());
        imageViewDetails.setImageDrawable(dr);
    }
    private void initAddToCartButton(){
        buttonAddToCart.setOnClickListener(e ->{
            cart.getItems().put(item.getIcon(),item);
            Gson gson = new Gson();
            String jsonCart = gson.toJson(cart);
            editor.putString(CART,jsonCart);
            editor.commit();
            Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();
            removeCurrentFragment();
        });
    }

    private void initRemoveFromCartButton(){
        buttonRemoveFromCart.setOnClickListener(e ->{
            cart.getItems().remove(item.getIcon());
            Gson gson = new Gson();
            String jsonCart = gson.toJson(cart);
            editor.putString(CART,jsonCart);
            editor.commit();
            Toast.makeText(getActivity(), "Removed Successfully", Toast.LENGTH_SHORT).show();
            removeCurrentFragment();
        });
    }
}
