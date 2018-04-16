package com.example.hp.navbarapp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hp on 09/02/2018.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {

    private List<Restaurant> restaurantList;

    private MyAdapterlistener listener;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description, weight;
        public Button btn_edit, btn_delete;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            description = (TextView) view.findViewById(R.id.description);
            weight = (TextView) view.findViewById(R.id.weight);
            btn_edit = (Button) view.findViewById(R.id.btn_Edit);
            btn_delete = (Button) view.findViewById(R.id.btn_Delete);
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.editButtonOnClick(view, getAdapterPosition());
                }
            });
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.deleteButtonOnClick(view, getAdapterPosition());
                }
            });
        }

    }

    public RestaurantAdapter(List<Restaurant> RestaurantList, MyAdapterlistener listener) {
        this.restaurantList = RestaurantList;
        this.listener=listener;
    }
    //TODO: addEditButton method here
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.name.setText(restaurant.getName());
        holder.description.setText(restaurant.getDescription());
        holder.weight.setText(String.valueOf(restaurant.getWeight()));

    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }
}
