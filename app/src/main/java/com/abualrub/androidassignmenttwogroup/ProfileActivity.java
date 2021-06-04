package com.abualrub.androidassignmenttwogroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.abualrub.androidassignmenttwogroup.domain.Payment;
import com.abualrub.androidassignmenttwogroup.domain.User;
import com.abualrub.androidassignmenttwogroup.utils.ITags;
import com.abualrub.androidassignmenttwogroup.utils.Validator;
import com.google.gson.Gson;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity implements ITags {
    private EditText editTextUserName, editTextPassword,editTextEmail,editTextPhoneNumber,
            editTextFirstName,editTextLastName,editTextCardExpireDateYear,editTextCardExpireDateMonth,editTextCardHolderName,
            editTextCardCVV,editTextCardNumber;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        getUserData();
    }

    private void init(){
        editTextUserName = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextCardExpireDateYear = findViewById(R.id.editTextCardExpireDateYear);
        editTextCardExpireDateMonth = findViewById(R.id.editTextCardExpireDateMonth);
        editTextCardHolderName = findViewById(R.id.editTextCardHolderName);
        editTextCardCVV = findViewById(R.id.editTextCardCVV);
        editTextCardNumber = findViewById(R.id.editTextCardNumber);
        prefs =PreferenceManager.getDefaultSharedPreferences(this);
        editor =  prefs.edit();
        getSupportActionBar().setTitle(R.string.osid_textViewToolBarMyProfile);
    }

    private void getUserData(){
        Gson gson = new Gson();
        user = gson.fromJson(prefs.getString(USER,null),User.class);
        if(user == null) {
            Toast.makeText(this, "Fatal error. Account not found!", Toast.LENGTH_SHORT).show();
            findViewById(R.id.buttonSave).setEnabled(false);
            return;
        }

        editTextUserName.setText(user.getUsername());
        editTextEmail.setText(user.getEmail());
        editTextFirstName.setText(user.getFirstName());
        editTextLastName.setText(user.getLastName());
        editTextPassword.setText(user.getPassword());
        editTextPhoneNumber.setText(user.getPhoneNumber());

        if(user.getPayment() == null) return;
        editTextCardExpireDateYear.setText(user.getPayment().getExpireYear());
        editTextCardExpireDateMonth.setText(user.getPayment().getExpireMonth());
        editTextCardHolderName.setText(user.getPayment().getCardHolderName());
        editTextCardCVV.setText(user.getPayment().getCvv());
        editTextCardNumber.setText(user.getPayment().getCardNumber());
    }

    public void buttonSaveHandleClick(View view) {
        String userName = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String expireYear = editTextCardExpireDateYear.getText().toString().trim();
        String expireMonth = editTextCardExpireDateMonth.getText().toString().trim();
        String cardHolderName = editTextCardHolderName.getText().toString().trim();
        String cvv = editTextCardCVV.getText().toString().trim();
        String cardNumber = editTextCardNumber.getText().toString().trim();

        Validator validator = new Validator(this);
        if(!validator.isValidUserName(userName)) return;
        if(!validator.isValidPassword(password)) return;
        if(!validator.isValidName(firstName)) return;
        if(!validator.isValidName(lastName)) return;
        if(!validator.isValidEmail(email)) return;
        if(!validator.isValidPhoneNumber(phoneNumber)) return;
        if(!expireYear.isEmpty() && !validator.isValidCardYear(expireYear)) return;
        if(!expireMonth.isEmpty() && !validator.isValidCardMonth(expireMonth)) return;
        if(!cardHolderName.isEmpty() && !validator.isValidCardHolderName(cardHolderName)) return;
        if(!cardNumber.isEmpty() && !validator.isValidCardNumber(cardNumber)) return;
        if(!cvv.isEmpty() && !validator.isValidCardCVV(cvv)) return;

        setUserData();
        backToMainActivity();
    }

    private void backToMainActivity(){
        String jsonUser = new Gson().toJson(user);

        // save to shared prefs
        editor.putString(USER,jsonUser);
        editor.commit();

        // start main activity
        finish();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    private void setUserData(){
        String userName = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String expireYear = editTextCardExpireDateYear.getText().toString().trim();
        String expireMonth = editTextCardExpireDateMonth.getText().toString().trim();
        String cardHolderName = editTextCardHolderName.getText().toString().trim();
        String cvv = editTextCardCVV.getText().toString().trim();
        String cardNumber = editTextCardNumber.getText().toString().trim();

        Payment payment = new Payment();
        payment.setCardHolderName(cardHolderName);
        payment.setExpireYear(expireYear);
        payment.setExpireMonth(expireMonth);
        payment.setCvv(cvv);
        payment.setCardNumber(cardNumber);

        user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPayment(payment);
    }

}