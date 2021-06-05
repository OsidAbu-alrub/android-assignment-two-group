package com.abualrub.androidassignmenttwogroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.abualrub.androidassignmenttwogroup.utils.ITags;
import com.google.android.material.navigation.NavigationView;

// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ITags {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        FragmentRoot root = new FragmentRoot();
        boolean isLoggedIn = prefs.getBoolean(IS_LOGGED_IN, false);
        if (!isLoggedIn) {
            root.loggedOutFilter(navigationView);
        }
        openFragment(new FragmentMain(), true);
    }

    private void init() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        initToolbar();
        initDrawer();
        initNavigationView();
    }

    private void initDrawer() {
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.osid_navigationDrawerOpen,
                R.string.osid_navigationDrawerClose);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }

    public void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sports IO");
    }

    private void initNavigationView() {
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_login:
                openFragment(new FragmentLogin(), true);
                break;
            case R.id.nav_logout:
                openFragment(new FragmentLogin(), true);
                break;
            case R.id.nav_home:
                openFragment(new FragmentMain(), true);
                break;
            case R.id.nav_profile:
                openFragment(new FragmentProfile(), true);
                break;
            case R.id.nav_cart:
                openFragment(new FragmentCart(), true);
                break;
            case R.id.nav_order:
                openFragment(new FragmentOrders(), true);
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openFragment(Fragment frag, boolean replace) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_right,
                R.anim.enter_from_right,
                R.anim.exit_to_right);
        transaction.add(R.id.fragmentContainer, frag);
        if (!replace) transaction.addToBackStack(null);
        transaction.commit();
    }
}