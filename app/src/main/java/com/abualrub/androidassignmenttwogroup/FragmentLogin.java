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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.abualrub.androidassignmenttwogroup.domain.User;
import com.abualrub.androidassignmenttwogroup.utils.ITags;
import com.abualrub.androidassignmenttwogroup.utils.Validator;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public class FragmentLogin extends FragmentRoot implements ITags {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewCreateAccountHandleClick;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private View rootView;
    private boolean isCreatedUser;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        init();
        textViewCreateAccountHandleClick();
        buttonLoginHandleClick();
        return rootView;
    }

    // we get data from shared intent in onStart
    // to ensure it stays up to date when user backs out of
    // main activity to login activity
    @Override
    public void onStart() {
        super.onStart();
        editor.putBoolean(IS_LOGGED_IN, false);
        editor.commit();
        loggedOutFilter();
        isCreatedUser = prefs.getBoolean(IS_CREATED_USER, false);
        if (!isCreatedUser) return;
        user = new Gson().fromJson(prefs.getString(USER, null), User.class);
        editTextUserName.setText(user.getUsername());
        editTextPassword.setText(user.getPassword());
    }

    public void buttonLoginHandleClick() {
        buttonLogin.setOnClickListener(e -> {
            String userName = editTextUserName.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            Validator validator = new Validator((AppCompatActivity) getActivity());
            if (!validator.isValidUserName(userName)) return;
            if (!validator.isValidPassword(password)) return;
            if (!isCreatedUser) {
                Toast.makeText(getActivity(), "Invalid username/password", Toast.LENGTH_SHORT).show();
                return;
            }
            loggedInFilter();
            startMainActivity();
        });
    }

    public void textViewCreateAccountHandleClick() {
        textViewCreateAccountHandleClick.setOnClickListener(e ->
                openFragment(new FragmentRegister(),true)
        );
    }

    private void init() {
        editTextUserName = rootView.findViewById(R.id.editTextUsername);
        editTextPassword = rootView.findViewById(R.id.editTextPassword);
        textViewCreateAccountHandleClick = rootView.findViewById(R.id.textViewCreateAccount);
        buttonLogin = rootView.findViewById(R.id.buttonLogin);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = prefs.edit();
    }

    private void startMainActivity() {
        String jsonUser = new Gson().toJson(user);

        // save to shared prefs
        editor.putString(USER, jsonUser);
        editor.putBoolean(IS_CREATED_USER, true);
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.commit();
        openFragment(new FragmentMain(),true);
    }
}
