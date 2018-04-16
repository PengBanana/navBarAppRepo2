package com.example.hp.navbarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addRestaurantActivty extends AppCompatActivity {
    private EditText etName, etDescription, etWeight;
    private int mode =0;
    private int position =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        Intent restaurantIntent = getIntent();
        setupUI();
        if(restaurantIntent.getExtras() !=null){
            Log.d("editButton", "clicked");
            Bundle bundle = restaurantIntent.getExtras();
            etName.setText(bundle.getString("name"));
            etDescription.setText(bundle.getString("description"));
            etWeight.setText(bundle.getString("weight"));
            position = bundle.getInt("position");
            mode=1;
        }


        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        //TODO: Insert code of checking if a content was passed (FOR EDIT)
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare
                Intent returnIntent = new Intent();
                //get data from form
                String Name = etName.getText().toString();
                String Description = etDescription.getText().toString();
                int Weight = Integer.parseInt(etWeight.getText().toString());
                returnIntent.putExtra(Restaurant.NAME, Name);
                returnIntent.putExtra(Restaurant.DESCRIPTION, Description);
                returnIntent.putExtra(Restaurant.WEIGHT, Weight);
                if(mode==1){
                    returnIntent.putExtra("position", position);
                }
                //TODO: INSERT IF ELSE STATEMENT TO KNOW IF EDITED
                setResult(RESULT_OK, returnIntent);
                finish();
                //submit data
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private void setupUI() {
        etName = (EditText) findViewById(R.id.etName);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etWeight = (EditText) findViewById(R.id.etWeight);
    }
}
