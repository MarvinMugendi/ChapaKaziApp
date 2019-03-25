package com.marvedie.servicesmarketapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddServiceActivity extends AppCompatActivity {

    TextView textViewFreelancerName;
    EditText editTextServiceDescription;
    Spinner spinnerCategories;
    //Button buttonAddService;
   // Button Backbtn;

    ListView listViewServices;

    DatabaseReference databaseServices;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_service);
        //Instantiate items
        textViewFreelancerName = findViewById(R.id.textViewFreelancerName);
        editTextServiceDescription = findViewById(R.id.editTextServiceDescription);
        spinnerCategories = findViewById(R.id.spinnerCategories);
        // buttonAddService = findViewById(R.id.buttonAddFreelancer);
        //Backbtn = findViewById(R.id.btnBack);

        listViewServices = findViewById(R.id.listViewServices);

        //  Backbtn.setOnClickListener(this);

        //Display Selected Freelancer Name  in the TextView For the Freelancer Name

        Intent intent = getIntent();

        String id = intent.getStringExtra(DatabaseSave.FREELANCER_ID);
        String name = intent.getStringExtra(DatabaseSave.FREELANCER_NAME);

        textViewFreelancerName.setText(name);
    }}

        //create a new node whereby new node will match with freelancer id
     //   databaseServices = FirebaseDatabase.getInstance().getReference("freelancer").child(id);

        //click Listener for the Button
       // buttonAddService.setOnClickListener(new View.OnClickListener() {
           // @Override
            //public void onClick(View view) {
                //call method
                //saveService();
           // }
       // });
   // }
/*
        private void saveService(){

            //Get Values from edit Text and Spinner
            String serviceName = editTextServiceDescription.getText().toString().trim();
            String freelancerCategory = spinnerCategories.getSelectedItem().toString();

            //Check if Service Description is Empty or Not
            if (!TextUtils.isEmpty(serviceName)){
                //Save the Service using an id

                String id = databaseServices.push().getKey();

                Service service = new Service( id, serviceName ,freelancerCategory);


                databaseServices.child(id).setValue(service);

                Toast.makeText(this, "Your Service has been Saved Successfully",Toast.LENGTH_SHORT).show();


            }else{
                editTextServiceDescription.setError("Service Description Cannot be Empty. Please Include the Service Charge");
                editTextServiceDescription.requestFocus();
            }
        }
*/
   /*@Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                finish();
                startActivity(new Intent(this, ProfileActivity.class));
                break;
        }

    }
}

*/