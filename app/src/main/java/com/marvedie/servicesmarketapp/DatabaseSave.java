package com.marvedie.servicesmarketapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSave extends AppCompatActivity implements View.OnClickListener {

    //Define Objects
    EditText editTextName,editTextService,editTextChat;
    Button buttonAdd;
    Spinner spinnerGenres;
     Button backbtn;
    //Constants to put Freelancer id and name in new Intent
    public static final String FREELANCER_NAME = "com.marvedie.servicesmarketapp.freelancername";
    public static final String FREELANCER_ID = "com.marvedie.servicesmarketapp.freelancerid";
    public  static final String FREELANCER_SERVICE = "com.marvedie.servicesmarketapp.freelancerservice";
    public static final String FREELANCER_CHATEMAIL ="com.marvedie.servicesmarketapp.freelancerchat";
    // database reference Object
    DatabaseReference databaseFreelancers;

    ListView listViewFreelancers;

    //define list to store all our Freelancers
    List<Freelancer> freelancerList;
    List<Freelancer> databaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_save);

        //Instantiate Objects
        editTextName = findViewById(R.id.editTextName);
        editTextChat = findViewById(R.id.editTextChatEmail);
        editTextService = findViewById(R.id.editTextService);
        buttonAdd = findViewById(R.id.buttonAddProject);
        spinnerGenres = findViewById(R.id.spinnerGenres);
        listViewFreelancers = findViewById(R.id.listViewFreelancers);
        backbtn = findViewById(R.id.btnBack);

        backbtn.setOnClickListener(this);

        freelancerList = new ArrayList<>();
        databaseList = new ArrayList<>();
        //Get the Database Reference
        //NB. Failure to pass parameter under get reference gives reference of root node of your Json tree
        //We need Freelancer node hence we pass parameter Freelancer
        databaseFreelancers = FirebaseDatabase.getInstance().getReference("freelancer");

        //Attach Onclick Listener to the Button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the Add Freelancer Method
                addFreelancer();
            }
        });

        listViewFreelancers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Get selected Freelancer from the List
                Freelancer freelancer = freelancerList.get(i);

                Intent intent = new Intent(getApplicationContext(),AddServiceActivity.class);

                //Put Freelancer Id and FreelancerName in the Intent...Define two Constants
                intent.putExtra(FREELANCER_ID,freelancer.getFreelancerid());
                intent.putExtra(FREELANCER_NAME,freelancer.getFreelancerName());
                intent.putExtra(FREELANCER_SERVICE,freelancer.getFreelancerService());
                intent.putExtra(FREELANCER_CHATEMAIL,freelancer.getFreelancerChat());

                startActivity(intent);

            }
        });
    }


    //Override onStart Method
    @Override
    protected void onStart() {
        super.onStart();

        //Attach our value Event listener to our database reference Object

        databaseFreelancers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                //this method will be executed everytime we change a value in the database
                //can read value of our database
                // clear Freelancer list if it contains any previous Freelancer.

                freelancerList.clear();

                //iterating through all the nodes
                for(DataSnapshot freelancerSnapshot : dataSnapshot.getChildren()){
                    //use our model class Freelancer
                    Freelancer freelancer = freelancerSnapshot.getValue(Freelancer.class);
                    //Add the Freelancer to FreelancerList
                    freelancerList.add(freelancer);
                }

                //Create an Arrayadapter
                FreelancerList adapter = new FreelancerList(DatabaseSave.this,freelancerList);

                //Attach the adapter to out freelancer ListVew
                listViewFreelancers.setAdapter(adapter);


            }

            @Override
            public void onCancelled( DatabaseError databaseError) {
                //will be executed if there is an error

            }
        });
    }

    //Define New Method to get Freelancer Name and Other Details
    private void addFreelancer() {

        //Fetch and convert User values to String
        String service = editTextService.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String genre = spinnerGenres.getSelectedItem().toString();
        String chatemail = editTextChat.getText().toString().trim();

        if (service.isEmpty())
        {
            editTextService.setError("Service cannot be Blank");
            editTextService.requestFocus();
            return;
        }
        if (chatemail.isEmpty())
        {
            editTextChat.setError("Please Register as a chat User first then Proceed with email used");
            editTextChat.requestFocus();
            return;
        }
        //Check if name is filled
        if (!TextUtils.isEmpty(name)) {

            //Create a unique Id for freelancers and store it in a String

            String id = databaseFreelancers.push().getKey();

            //Create a New Freelancer

            Freelancer freelancer = new Freelancer(id,name,genre,service,chatemail);

            //Use SetValue Method To store New Value into Firebase Database

            databaseFreelancers.child(id).setValue(freelancer); /*Everytime a freelancer is created
             it will generate a unique id in which it will store the freelancer*/

            Toast.makeText(this, "You have been added to our Freelancers.", Toast.LENGTH_SHORT).show();

        }else{

            editTextName.setError("Please Enter DisplayName");
            editTextName.requestFocus();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                finish();
                startActivity(new Intent(this, ProfileWork.class));
                break;
        }
    }
/*Ensure the database Rules have been changed to "True" since default values only allow
  authenticated users to make changes If no authentication has been done on your app*/


}

