package com.abualrub.androidassignmenttwogroup.utils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public class Validator {
    private static final String NAME_REGEX = "[a-zA-Z]+";
    private static final int NAME_MIN_LENGTH = 2;
    private static final int NAME_MAX_LENGTH = 15;
    private static final int USERNAME_MIN_LENGTH = 3;
    private static final int USERNAME_MAX_LENGTH = 30;
    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,20}$";
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";
    private static final int PHONE_NUMBER_LENGTH = 10;
    private static final int CARD_NUMBER_LENGTH = 16;
    private static final int CARD_CVV_LENGTH = 3;
    private static final int CARD_EXPIRE_MONTH_LENGTH = 2;
    private static final int CARD_EXPIRE_YEAR_LENGTH = 4;

    public static final String CARD_HOLDER_NAME_REGEX = "[a-zA-Z ]+";

    private AppCompatActivity activity;
    public Validator(AppCompatActivity activity){
        this.activity = activity;
    }

    public boolean isValidName(String name){
        name = name.trim();
        if(name.matches(NAME_REGEX) && (name.length() >= NAME_MIN_LENGTH && name.length() <= NAME_MAX_LENGTH)){
            return true;
        }
        showToast("Invalid name");
        return false;
    }

    public boolean isValidUserName(String userName){
        userName = userName.trim();
        if((userName.length() >= USERNAME_MIN_LENGTH && userName.length() <= USERNAME_MAX_LENGTH)) {
            return true;
        }
        showToast("Invalid username");
        return false;
    }

    public boolean isValidPassword(String password){
        password = password.trim();
        if(password.matches(PASSWORD_REGEX)){
            return true;
        }
        showToast("Invalid password");
        return false;
    }

    public boolean isValidEmail(String email){
        email = email.trim();
        if(!email.isEmpty() && email.matches(EMAIL_REGEX)){
            return true;
        }
        showToast("Invalid email");
        return false;
    }

    public boolean isValidPhoneNumber(String phoneNumber){
        phoneNumber.trim();
        if(phoneNumber.length() == PHONE_NUMBER_LENGTH){
            return true;
        }
        showToast("Invalid phone number");
        return false;
    }

    public boolean isValidCardNumber(String cardNumber){
        if(cardNumber.trim().length() == CARD_NUMBER_LENGTH) return true;
        showToast("Invalid card number");
        return false;
    }

    public boolean isValidCardCVV(String cvv){
        if(cvv.trim().length() == CARD_CVV_LENGTH) return true;
        showToast("Invalid cvv");
        return false;
    }
    public boolean isValidCardMonth(String month){
        int monthAsNumber = Integer.parseInt(month);
        if(month.trim().length() == CARD_EXPIRE_MONTH_LENGTH
            && monthAsNumber <= 12
            && monthAsNumber >= 1) return true;
        showToast("Invalid month");
        return false;
    }

    public boolean isValidCardYear(String year){
        String currentYear = Calendar.getInstance().get(Calendar.YEAR)+"";
        if (year.trim().length() == CARD_EXPIRE_YEAR_LENGTH &&
                year.compareTo(currentYear) >= 0) return true;
        showToast("Invalid year");
        return false;
    }

    public boolean isValidCardHolderName(String cardHolderName){
        if(cardHolderName.trim().matches(CARD_HOLDER_NAME_REGEX)) return true;
        showToast("Invalid card holder name");
        return false;
    }

    private void showToast(String str){
        Toast.makeText(this.activity, str, Toast.LENGTH_LONG).show();
    }
}
