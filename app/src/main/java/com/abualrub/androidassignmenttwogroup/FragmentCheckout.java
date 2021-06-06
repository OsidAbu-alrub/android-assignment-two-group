package com.abualrub.androidassignmenttwogroup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abualrub.androidassignmenttwogroup.domain.Cart;
import com.abualrub.androidassignmenttwogroup.domain.Item;
import com.abualrub.androidassignmenttwogroup.domain.Order;
import com.abualrub.androidassignmenttwogroup.domain.Payment;
import com.abualrub.androidassignmenttwogroup.domain.User;
import com.abualrub.androidassignmenttwogroup.utils.CartAdapter;
import com.abualrub.androidassignmenttwogroup.utils.CheckoutAdapter;
import com.abualrub.androidassignmenttwogroup.utils.ITags;
import com.abualrub.androidassignmenttwogroup.utils.Validator;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public class FragmentCheckout extends FragmentRoot implements ITags {
    private EditText editTextUserName;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private EditText editTextPhoneNumber;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextCardExpireDateYear,editTextCardExpireDateMonth,editTextCardHolderName,
            editTextCardCVV,editTextCardNumber;
    private Button buttonPlaceOrder;
    private TextView textViewPlaceOrderWithTax;
    private TextView textViewPlaceOrderPriceWithoutTax;
    private TextView textViewPlaceOrderTax;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private User user;
    private View rootView;
    private Item[] items;
    private RecyclerView rv;
    private CheckoutAdapter adapter;
    private Cart cart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_checkout, container, false);
        init();
        getUserData();
        initRecyclerView();
        buttonPlaceOrderHandleClick();
        return rootView;
    }

    private void init() {
        editTextUserName = rootView.findViewById(R.id.editTextUsername);
        editTextPassword = rootView.findViewById(R.id.editTextPassword);
        editTextPhoneNumber = rootView.findViewById(R.id.editTextPhoneNumber);
        editTextFirstName = rootView.findViewById(R.id.editTextFirstName);
        editTextLastName = rootView.findViewById(R.id.editTextLastName);
        editTextEmail = rootView.findViewById(R.id.editTextEmail);
        editTextCardExpireDateYear = rootView.findViewById(R.id.editTextCardExpireDateYear);
        editTextCardExpireDateMonth = rootView.findViewById(R.id.editTextCardExpireDateMonth);
        editTextCardHolderName = rootView.findViewById(R.id.editTextCardHolderName);
        editTextCardCVV = rootView.findViewById(R.id.editTextCardCVV);
        editTextCardNumber = rootView.findViewById(R.id.editTextCardNumber);
        buttonPlaceOrder = rootView.findViewById(R.id.buttonPlaceOrder);
        textViewPlaceOrderWithTax = rootView.findViewById(R.id.textViewPlaceOrderWithTax);
        textViewPlaceOrderPriceWithoutTax = rootView.findViewById(R.id.textViewPlaceOrderPriceWithoutTax);
        textViewPlaceOrderTax = rootView.findViewById(R.id.textViewPlaceOrderTax);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = prefs.edit();
    }

    private void getUserData(){
        Gson gson = new Gson();
        user = gson.fromJson(prefs.getString(USER,null),User.class);
        cart = gson.fromJson(prefs.getString(CART,null),Cart.class);
        textViewPlaceOrderWithTax.setText(cart.calculatePriceWithTax());
        textViewPlaceOrderPriceWithoutTax.setText(cart.calculatePriceWithoutTax());
        textViewPlaceOrderTax.setText(cart.calculateTax());
        editTextUserName.setText(user.getUsername());
        editTextEmail.setText(user.getEmail());
        editTextFirstName.setText(user.getFirstName());
        editTextLastName.setText(user.getLastName());
        editTextPassword.setText(user.getPassword());
        editTextPhoneNumber.setText(user.getPhoneNumber());

        items = new Item[cart.getItems().size()];
        int i = 0;
        for(Item item : cart.getItems().values()){
            items[i++] = item;
        }

        if(user.getPayment() == null) return;
        editTextCardExpireDateYear.setText(user.getPayment().getExpireYear());
        editTextCardExpireDateMonth.setText(user.getPayment().getExpireMonth());
        editTextCardHolderName.setText(user.getPayment().getCardHolderName());
        editTextCardCVV.setText(user.getPayment().getCvv());
        editTextCardNumber.setText(user.getPayment().getCardNumber());
    }

    public void buttonPlaceOrderHandleClick() {
        buttonPlaceOrder.setOnClickListener(v ->{
            String expireYear = editTextCardExpireDateYear.getText().toString().trim();
            String expireMonth = editTextCardExpireDateMonth.getText().toString().trim();
            String cardHolderName = editTextCardHolderName.getText().toString().trim();
            String cvv = editTextCardCVV.getText().toString().trim();
            String cardNumber = editTextCardNumber.getText().toString().trim();

            Validator validator = new Validator((AppCompatActivity) getActivity());
            if(expireYear.trim().isEmpty()){
                showEmptyFieldToastMessage();
                return;
            }
            if(expireMonth.trim().isEmpty()){
                showEmptyFieldToastMessage();
                return;
            }
            if(cardNumber.trim().isEmpty()){
                showEmptyFieldToastMessage();
                return;
            }
            if(cardHolderName.trim().isEmpty()){
                showEmptyFieldToastMessage();
                return;
            }
            if(cvv.trim().isEmpty()){
                showEmptyFieldToastMessage();
                return;
            }

            if(!validator.isValidCardYear(expireYear)) return;
            if(!validator.isValidCardMonth(expireMonth)) return;
            if(!validator.isValidCardHolderName(cardHolderName)) return;
            if(!validator.isValidCardNumber(cardNumber)) return;
            if(!validator.isValidCardCVV(cvv)) return;

            hideKeyboardFrom(getContext(),this.getView());
            Toast.makeText(getActivity(), "Order placed successfully!", Toast.LENGTH_LONG).show();
            startMainActivity();
        });
    }

    private void initRecyclerView(){
        rv = rootView.findViewById(R.id.recyclerViewPlaceOrder);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CheckoutAdapter(items,getActivity());
        rv.setAdapter(adapter);
    }

    private void startMainActivity() {
        Gson gson = new Gson();
        Order order = gson.fromJson(prefs.getString(ORDER,null),Order.class);
        if(order == null) order = new Order();
        order.getCarts().add(cart);
        cart.setDate(new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
        String jsonOrder = gson.toJson(order);
        editor.putString(ORDER,jsonOrder);
        editor.putString(CART,null);
        editor.commit();
        openFragment(new FragmentMain(),true);
    }

    private void showEmptyFieldToastMessage(){
        Toast.makeText(getActivity(), "No fields can be empty", Toast.LENGTH_SHORT).show();
    }
}
