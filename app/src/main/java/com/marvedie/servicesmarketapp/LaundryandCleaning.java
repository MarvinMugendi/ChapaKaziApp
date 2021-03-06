package com.marvedie.servicesmarketapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import android.support.v7.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class LaundryandCleaning extends AppCompatActivity {

    DatabaseReference dbfreelancers;
    private RecyclerView recyclerView;
    private List<Freelancer> freelancerList;
    private FreelancersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundryand_cleaning);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        freelancerList = new ArrayList<>();
        adapter = new FreelancersAdapter(this, freelancerList);
        recyclerView.setAdapter(adapter);


        //(SQL equivalent) SELECT * FROM Freelancer WHERE service = "Laundry and Cleaning services"
       Query query = FirebaseDatabase.getInstance().getReference("freelancer")
                .orderByChild("freelancerGenre")
                .equalTo("Laundry and Cleaning");
        query.addListenerForSingleValueEvent(valueEventListener);



        /*
         * You just need to attach the value event listener to read the values
         * for example
         * query6.addListenerForSingleValueEvent(valueEventListener)
         * */
    }



    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            freelancerList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Freelancer freelancer = snapshot.getValue(Freelancer.class);
                    freelancerList.add(freelancer);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
