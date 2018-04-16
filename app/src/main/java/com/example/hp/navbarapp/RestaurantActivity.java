package com.example.hp.navbarapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RestaurantActivity extends AppCompatActivity {
    private List<Restaurant> restaurantList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RestaurantAdapter rAdapter;
    public final static int ADD_RESTAURANT_CODE = 0;
    public final static int EDIT_RESTAURANT_CODE = 1;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        recyclerView = (RecyclerView) findViewById(R.id.restaurantRecyclerView);
        prepareMovieData();
        this.rAdapter = new RestaurantAdapter(restaurantList, new MyAdapterlistener() {
            @Override
            public void editButtonOnClick(View view, int adapterPosition) {
                Intent editIntent = new Intent(RestaurantActivity.this, addRestaurantActivty.class);
                Bundle editBundle = new Bundle();
                editBundle.putString("name", restaurantList.get(adapterPosition).getName());
                editBundle.putString("description", restaurantList.get(adapterPosition).getDescription());
                editBundle.putString("weight", restaurantList.get(adapterPosition).getWeight()+"");
                editBundle.putInt("position", adapterPosition);
                editIntent.putExtras(editBundle);
                startActivityForResult(editIntent, EDIT_RESTAURANT_CODE);
            }

            @Override
            public void deleteButtonOnClick(View view, int adapterPosition) {
                restaurantList.remove(adapterPosition);
                rAdapter.notifyDataSetChanged();
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rAdapter);

        Button btnSurprise = (Button) findViewById(R.id.btnSurprise);
        Button btnAddRestaurant = (Button) findViewById(R.id.btnCreate);
        Button btndelAll = (Button) findViewById(R.id.btn_deleteAll);
        btnSurprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int max=restaurantList.size();
                Random randomx = new Random();
                int randomNum;
                //RandomNumber Generator
                randomNum=randomx.nextInt(max);
                String RestoName=restaurantList.get(randomNum).getName();
                if(RestoName.isEmpty()){
                    RestoName="Don't Eat";
                }
                Snackbar.make(view, RestoName, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnAddRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addRestaurantIntent = new Intent(RestaurantActivity.this, addRestaurantActivty.class );
                startActivityForResult(addRestaurantIntent, ADD_RESTAURANT_CODE);
            }
        });
        btndelAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurantList.clear();
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Log.i("naghahanap ng error",resultCode + " " + requestCode);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_RESTAURANT_CODE:
                        restaurantList.add(new Restaurant(data.getStringExtra(Restaurant.NAME),data.getStringExtra(Restaurant.DESCRIPTION), data.getIntExtra(Restaurant.WEIGHT,0)));
                        recyclerView.getAdapter().notifyDataSetChanged();
                    //Log.i("naghahanap ng error",restaurantList.size()+ "");
                    break;
                case EDIT_RESTAURANT_CODE:
                    int editPosition= data.getIntExtra("position",0);
                    restaurantList.get(editPosition).setName(data.getStringExtra(Restaurant.NAME));
                    restaurantList.get(editPosition).setDescription(data.getStringExtra(Restaurant.DESCRIPTION));
                    restaurantList.get(editPosition).setWeight(data.getIntExtra(Restaurant.WEIGHT,0));
                    recyclerView.getAdapter().notifyDataSetChanged();
                    break;
            }
        }
    }




    private void prepareMovieData() {
        Restaurant restaurant = new Restaurant("Bon Chon", "Korean Chicken", 25);
        restaurantList.add(restaurant);

        restaurant = new Restaurant("Mcdo", "Fast Food", 24);
        restaurantList.add(restaurant);

        restaurant = new Restaurant("Jollibee", "Chicken Spaghetti", 23);
        restaurantList.add(restaurant);

        restaurant = new Restaurant("KFC", "12 Spices Chicken", 22);
        restaurantList.add(restaurant);

        restaurant = new Restaurant("Shakeys", "Thin Crust Pizza", 21);
        restaurantList.add(restaurant);

        restaurant = new Restaurant("Pizza Hut", "Thick Crust Pizza", 20);
        restaurantList.add(restaurant);



        //rAdapter.notifyDataSetChanged();
    }
}
