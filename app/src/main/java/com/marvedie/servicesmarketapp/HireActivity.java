package com.marvedie.servicesmarketapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;


public class HireActivity extends AppCompatActivity implements View.OnClickListener {
    //Variables
    ListView mListView;
    List<String> mList = new ArrayList<>();
    ArrayAdapter<String> mAdapter;

    private Button Backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire);

        //Initialise the Variables defined above
        mListView = findViewById(R.id.lvCategories);
        Backbtn = findViewById(R.id.btnBack);

        mList.add("Laundry and Cleaning Services");
        mList.add("Delivery Services");
        mList.add("Design and Media");
        mList.add("Data Entry");
        mList.add("Website and Software");
        mList.add("Engineering and Science");
        mList.add("Writing");
        mList.add("Translation and Languages");
        mList.add("View All Freelancers");


        //Initialise the Array Adapter
        mAdapter = new ArrayAdapter<>(HireActivity.this, android.R.layout.simple_list_item_1, mList);
        mListView.setAdapter(mAdapter);

        Backbtn.setOnClickListener(this);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent myintent = new Intent(view.getContext(), LaundryandCleaning.class);
                    startActivityForResult(myintent, 0);
                }
                if (position == 1) {
                    Intent myintent = new Intent(view.getContext(), DeliveryServices.class);
                    startActivityForResult(myintent, 1);
                }
                if (position == 2) {
                    Intent myintent = new Intent(view.getContext(), DesignandMedia.class);
                    startActivityForResult(myintent, 2);
                }
                if (position == 3) {
                    Intent myintent = new Intent(view.getContext(), DataEntry.class);
                    startActivityForResult(myintent, 3);
                }
                if (position == 4) {
                    Intent myintent = new Intent(view.getContext(), WebsiteandSoftware.class);
                    startActivityForResult(myintent, 4);
                }
                if (position == 5) {
                    Intent myintent = new Intent(view.getContext(), EngineeringandScience.class);
                    startActivityForResult(myintent, 5);
                }
                if (position == 6) {
                    Intent myintent = new Intent(view.getContext(), WritersActivity.class);
                    startActivityForResult(myintent, 6);
                }
                if (position == 7) {
                    Intent myintent = new Intent(view.getContext(), Translation.class);
                    startActivityForResult(myintent, 7);
                }
                if (position == 8) {
                    Intent myintent = new Intent(view.getContext(), AllFreelancers.class);
                    startActivityForResult(myintent, 8);
                }

            }
        });
    }




    @Override
    public void onClick(View view) {
       switch (view.getId()){
            case R.id.btnBack:
                finish();
                startActivity(new Intent(this, ProfileHire.class));
                break;
        }
    }
}

