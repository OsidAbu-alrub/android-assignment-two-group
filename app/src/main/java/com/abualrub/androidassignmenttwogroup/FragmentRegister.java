package com.abualrub.androidassignmenttwogroup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.abualrub.androidassignmenttwogroup.domain.User;
import com.abualrub.androidassignmenttwogroup.utils.ITags;
import com.abualrub.androidassignmenttwogroup.utils.Validator;
import com.google.gson.Gson;

// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public class FragmentRegister extends FragmentRoot implements ITags {
    private EditText editTextUserName;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private EditText editTextPhoneNumber;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private TextView textViewLoginHandleClick;
    private Button buttonRegister;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private User user;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_register, container, false);
        init();
        textViewLoginHandleClick();
        buttonRegisterHandleClick();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        editor.putBoolean(IS_LOGGED_IN, false);
        editor.commit();
        loggedOutFilter();
    }

    private void init() {
        editTextUserName = rootView.findViewById(R.id.editTextUsername);
        editTextPassword = rootView.findViewById(R.id.editTextPassword);
        editTextPhoneNumber = rootView.findViewById(R.id.editTextPhoneNumber);
        editTextFirstName = rootView.findViewById(R.id.editTextFirstName);
        editTextLastName = rootView.findViewById(R.id.editTextLastName);
        editTextEmail = rootView.findViewById(R.id.editTextEmail);
        textViewLoginHandleClick = rootView.findViewById(R.id.textViewCreateAccount);
        buttonRegister = rootView.findViewById(R.id.buttonRegister);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = prefs.edit();
    }

    public void buttonRegisterHandleClick() {
        buttonRegister.setOnClickListener(v ->{
            String userName = editTextUserName.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String phoneNumber = editTextPhoneNumber.getText().toString().trim();
            String firstName = editTextFirstName.getText().toString().trim();
            String lastName = editTextLastName.getText().toString().trim();

            Validator validator = new Validator((AppCompatActivity) getActivity());
            if (!validator.isValidUserName(userName)) return;
            if (!validator.isValidPassword(password)) return;
            if (!validator.isValidName(firstName)) return;
            if (!validator.isValidName(lastName)) return;
            if (!validator.isValidEmail(email)) return;
            if (!validator.isValidPhoneNumber(phoneNumber)) return;

            hideKeyboardFrom(getContext(),this.getView());
            setUserData();
            startLoginActivity();
        });
    }

    public void textViewLoginHandleClick() {
        textViewLoginHandleClick.setOnClickListener(v ->
                openFragment(new FragmentLogin(),true)
        );
    }

    private void startLoginActivity() {
        String jsonStudent = new Gson().toJson(user);

        // save to shared prefs
        editor.putString(USER, jsonStudent);
        editor.putBoolean(IS_CREATED_USER, true);
        editor.putString(ORDER,null);
        editor.putString(CART,null);
        editor.commit();

        openFragment(new FragmentLogin(),false);
    }

    private void setUserData() {
        String userName = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();

        user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        editor.putString(USER,null);
        editor.putString(CART,null);
        editor.putBoolean(IS_CREATED_USER,false);
        editor.commit();
    }
}
