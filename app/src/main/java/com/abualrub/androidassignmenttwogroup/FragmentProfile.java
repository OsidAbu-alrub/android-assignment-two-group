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

import com.abualrub.androidassignmenttwogroup.domain.Payment;
import com.abualrub.androidassignmenttwogroup.domain.User;
import com.abualrub.androidassignmenttwogroup.utils.ITags;
import com.abualrub.androidassignmenttwogroup.utils.Validator;
import com.google.gson.Gson;

// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public class FragmentProfile extends FragmentRoot implements ITags {
    private EditText editTextUserName;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private EditText editTextPhoneNumber;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextCardExpireDateYear,editTextCardExpireDateMonth,editTextCardHolderName,
            editTextCardCVV,editTextCardNumber;
    private Button buttonSave;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private User user;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        init();
        getUserData();
        buttonSaveHandleClick();
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
        buttonSave = rootView.findViewById(R.id.buttonSave);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = prefs.edit();
    }

    private void getUserData(){
        Gson gson = new Gson();
        user = gson.fromJson(prefs.getString(USER,null),User.class);
        if(user == null) {
            Toast.makeText(getActivity(), "Fatal error. Account not found!", Toast.LENGTH_SHORT).show();
            rootView.findViewById(R.id.buttonSave).setEnabled(false);
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

    public void buttonSaveHandleClick() {
        buttonSave.setOnClickListener(v ->{
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

            Validator validator = new Validator((AppCompatActivity) getActivity());
            if (!validator.isValidUserName(userName)) return;
            if (!validator.isValidPassword(password)) return;
            if (!validator.isValidName(firstName)) return;
            if (!validator.isValidName(lastName)) return;
            if (!validator.isValidEmail(email)) return;
            if (!validator.isValidPhoneNumber(phoneNumber)) return;
            if(!expireYear.isEmpty() && !validator.isValidCardYear(expireYear)) return;
            if(!expireMonth.isEmpty() && !validator.isValidCardMonth(expireMonth)) return;
            if(!cardHolderName.isEmpty() && !validator.isValidCardHolderName(cardHolderName)) return;
            if(!cardNumber.isEmpty() && !validator.isValidCardNumber(cardNumber)) return;
            if(!cvv.isEmpty() && !validator.isValidCardCVV(cvv)) return;

            hideKeyboardFrom(getContext(),this.getView());
            Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
            setUserData();
            startMainActivity();
        });
    }

    private void startMainActivity() {
        String jsonStudent = new Gson().toJson(user);

        // save to shared prefs
        editor.putString(USER, jsonStudent);
        editor.commit();
        removeCurrentFragment();
    }

    private void setUserData() {
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
