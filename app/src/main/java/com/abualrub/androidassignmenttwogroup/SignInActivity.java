package com.abualrub.androidassignmenttwogroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.abualrub.androidassignmenttwogroup.domain.User;
import com.abualrub.androidassignmenttwogroup.utils.ITags;
import com.abualrub.androidassignmenttwogroup.utils.Validator;
import com.google.gson.Gson;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity implements ITags {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private boolean isCreatedUser;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
    }

    // we get data from shared intent in onStart
    // to ensure it stays up to date when user backs out of
    // main activity to login activity
    @Override
    protected void onStart() {
        super.onStart();
        isCreatedUser = prefs.getBoolean(IS_CREATED_USER,false);
        if(!isCreatedUser) return;
        user = new Gson().fromJson(prefs.getString(USER,null),User.class);
        editTextUserName.setText(user.getUsername());
        editTextPassword.setText(user.getPassword());
    }

    public void buttonLoginHandleClick(View view) {
        String userName = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        Validator validator = new Validator(this);
        if(!validator.isValidUserName(userName)) return;
        if(!validator.isValidPassword(password)) return;
        if(!isCreatedUser){
            Toast.makeText(this, "Invalid username/password", Toast.LENGTH_SHORT).show();
            return;
        }
        startMainActivity();
    }

    public void textViewCreateAccountHandleClick(View view) {
        Intent i = new Intent(this,RegisterActivity.class);
        finish();
        startActivity(i);
    }

    private void init(){
        editTextUserName = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        getSupportActionBar().setTitle(R.string.osid_textViewToolBarText);
    }

    private void startMainActivity(){
        String jsonUser = new Gson().toJson(user);

        // save to shared prefs
        editor.putString(USER,jsonUser);
        editor.putBoolean(IS_CREATED_USER,true);
        editor.commit();

        // start main activity
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}