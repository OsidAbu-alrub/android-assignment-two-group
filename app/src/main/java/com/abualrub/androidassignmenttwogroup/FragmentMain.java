package com.abualrub.androidassignmenttwogroup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abualrub.androidassignmenttwogroup.utils.Category;
import com.abualrub.androidassignmenttwogroup.utils.HomeAdapter;
import com.abualrub.androidassignmenttwogroup.utils.IConstants;
import com.abualrub.androidassignmenttwogroup.utils.ITags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// ************************************** //
//    MADE BY OSID ABU-ALRUB (1183096)    //
// ************************************** //
public class FragmentMain extends FragmentRoot implements ITags, IConstants {

    private View rootView;
    private RecyclerView rv;
    private SharedPreferences prefs;
    private Spinner spinnerCategories;
    private HomeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        init();
        return rootView;
    }

    private void init(){
        initSpinner();
        initRecyclerView();

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean isLoggedIn = prefs.getBoolean(IS_LOGGED_IN,false);
        if(isLoggedIn){
            loggedInFilter();
            return;
        }
        loggedOutFilter();
    }

    private void initRecyclerView(){
        rv = rootView.findViewById(R.id.recyclerViewHome);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeAdapter(db,getActivity());
        rv.setAdapter(adapter);
    }

    private void initSpinner(){
        ArrayList<String> categories = new ArrayList<>();
        for(Category category : Category.values()){
            categories.add(category.AS_STRING);
        }

        spinnerCategories = rootView.findViewById(R.id.spinnerCategories);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        categories
                );

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinnerCategories.setAdapter(arrayAdapter);

        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category = parent.getItemAtPosition(position).toString();
                adapter.filter(category);
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {

            }
        });
    }
}
