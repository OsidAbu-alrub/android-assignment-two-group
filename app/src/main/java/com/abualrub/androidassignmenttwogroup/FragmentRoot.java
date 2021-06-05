package com.abualrub.androidassignmenttwogroup;

import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.abualrub.androidassignmenttwogroup.utils.ITags;
import com.google.android.material.navigation.NavigationView;

// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public class FragmentRoot extends Fragment implements ITags {
    public void openFragment(Fragment frag,boolean replace){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_right,
                R.anim.enter_from_right,
                R.anim.exit_to_right);
        if(replace) transaction.remove(this);
        transaction.add(R.id.fragmentContainer,frag);
        if(!replace) transaction.addToBackStack(null);
        transaction.commit();
    }

    public void openFragment(FragmentActivity activity, Fragment frag, boolean replace){
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_right,
                R.anim.enter_from_right,
                R.anim.exit_to_right);
        if(replace) transaction.remove(this);
        transaction.add(R.id.fragmentContainer,frag);
        if(!replace) transaction.addToBackStack(null);
        transaction.commit();
    }

    public void removeCurrentFragment(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_right,
                R.anim.enter_from_right,
                R.anim.exit_to_right);
        transaction.remove(this);
        transaction.commit();
        fragmentManager.popBackStackImmediate();
    }

    public void loggedInFilter(){
        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        if(navigationView == null) return;
        Menu menuNav = navigationView.getMenu();
        MenuItem logoutItem = menuNav.findItem(R.id.nav_logout);
        MenuItem profileItem = menuNav.findItem(R.id.nav_profile);
        MenuItem cartItem = menuNav.findItem(R.id.nav_cart);
        MenuItem loginItem = menuNav.findItem(R.id.nav_login);
        MenuItem orderItem = menuNav.findItem(R.id.nav_order);
        logoutItem.setVisible(true);
        profileItem.setVisible(true);
        cartItem.setVisible(true);
        orderItem.setVisible(true);
        loginItem.setVisible(false);
    }

    public void loggedOutFilter(){
        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        if(navigationView == null) return;
        Menu menuNav = navigationView.getMenu();
        MenuItem logoutItem = menuNav.findItem(R.id.nav_logout);
        MenuItem profileItem = menuNav.findItem(R.id.nav_profile);
        MenuItem cartItem = menuNav.findItem(R.id.nav_cart);
        MenuItem loginItem = menuNav.findItem(R.id.nav_login);
        MenuItem orderItem = menuNav.findItem(R.id.nav_order);
        logoutItem.setVisible(false);
        profileItem.setVisible(false);
        cartItem.setVisible(false);
        orderItem.setVisible(false);
        loginItem.setVisible(true);
    }

    public void loggedOutFilter(NavigationView navigationView){
        if(navigationView == null) return;
        Menu menuNav = navigationView.getMenu();
        MenuItem logoutItem = menuNav.findItem(R.id.nav_logout);
        MenuItem profileItem = menuNav.findItem(R.id.nav_profile);
        MenuItem cartItem = menuNav.findItem(R.id.nav_cart);
        MenuItem loginItem = menuNav.findItem(R.id.nav_login);
        MenuItem orderItem = menuNav.findItem(R.id.nav_order);
        logoutItem.setVisible(false);
        profileItem.setVisible(false);
        cartItem.setVisible(false);
        orderItem.setVisible(false);
        loginItem.setVisible(true);
    }
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
