package com.abualrub.androidassignmenttwogroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Spinner;

import com.abualrub.androidassignmenttwogroup.domain.Item;
import com.abualrub.androidassignmenttwogroup.domain.ListViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Spinner mySpinner;

    String[] title;
    double[] Rate;
    double[] price;
    int[] icon= new int[]{R.drawable.tshirt,R.drawable.adidasshoes,R.drawable.ball};

    //choosing categories
    String [] categories={"All","Clothes","Shoes","Sport equipments"};
    ArrayList<Item> arrayList=new ArrayList<Item>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView =findViewById(R.id.recyclerView);

        String[] titles=new String[Item.items.length];
        double[] Rates=new double[Item.items.length];
        double[] prices=new double[Item.items.length];
        int [] imagesId=new int[Item.items.length];

        for (int i=0; i<titles.length;i++){
            titles[i]=Item.items[i].getTitle();
            Rates[i]=Item.items[i].getRating();
            prices[i]=Item.items[i].getPrice();
            imagesId[i]=Item.items[i].getIcon();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ListViewAdapter adapter=new ListViewAdapter(titles,prices,Rates,imagesId);
        recyclerView.setAdapter(adapter);

        //spinner
     /*   mySpinner=findViewById(R.id.mySpinner);
        mySpinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,categories));

        recyclerView = findViewById(R.id.recyclerView);
        for(int i=0;i<title.length;i++){
            Item item = new Item(title[i],Rate[i],price[i],icon[i]);
            arrayList.add(item);
        }*/

      //  ListViewAdapter adapter;

      //  adapter=new ListViewAdapter(this, title,price,Rate,icon);


      /*  mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0 && position < categories.length) {

                      getSelectedCategoryData(position);
                } else {
                    Toast.makeText(MainActivity.this,"Selected Category Does not Exit!",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }
   /* private ArrayList<Item> getItems(){

        ArrayList<Item> items=new ArrayList<>();
        items.clear();

        items.add(new Item("T-Shirt",4.5,15,R.drawable.tshirt,1));
        items.add(new Item("Adidas shoe",3,10,R.drawable.adidasshoes,2));
        items.add(new Item("Ball",4.8,43,R.drawable.ball,3));

        return items;

    }*/
   /* private void getSelectedCategoryData(int categoryID){
        ArrayList<Item> items=new ArrayList<>();
        if (categoryID==0){
            adapter=new ListViewAdapter(this, getItems());
        }
        else{
            for (Item item: getItems()){
                if(item.getCategoryID()==categoryID){
                    items.add(item);
                }
            }
            adapter=new ListViewAdapter(this, items);
        }

        recyclerView.setAdapter(adapter);
    }*/

}