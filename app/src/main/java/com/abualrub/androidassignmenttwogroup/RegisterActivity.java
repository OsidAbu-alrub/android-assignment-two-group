package com.abualrub.androidassignmenttwogroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

import com.abualrub.androidassignmenttwogroup.domain.User;
import com.abualrub.androidassignmenttwogroup.utils.ITags;
import com.abualrub.androidassignmenttwogroup.utils.Validator;
import com.google.gson.Gson;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements ITags {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private EditText editTextPhoneNumber;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    public void buttonRegisterHandleClick(View view) {
        String userName = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();

        Validator validator = new Validator(this);
        if(!validator.isValidUserName(userName)) return;
        if(!validator.isValidPassword(password)) return;
        if(!validator.isValidName(firstName)) return;
        if(!validator.isValidName(lastName)) return;
        if(!validator.isValidEmail(email)) return;
        if(!validator.isValidPhoneNumber(phoneNumber)) return;

        setUserData();
        startLoginActivity();
    }

    public void textViewLoginHandleClick(View view) {
        Intent i = new Intent(this,SignInActivity.class);
        finish();
        startActivity(i);
    }

    private void init(){
        editTextUserName = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        getSupportActionBar().setTitle(R.string.osid_textViewRegisterToolBarText);
    }

    private void startLoginActivity(){
        String jsonStudent = new Gson().toJson(user);

        // save to shared prefs
        editor.putString(USER,jsonStudent);
        editor.putBoolean(IS_CREATED_USER,true);
        editor.commit();

        // start main activity
        Intent i = new Intent(this,SignInActivity.class);
        finish();
        startActivity(i);
    }

    private void setUserData(){
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
    }


}