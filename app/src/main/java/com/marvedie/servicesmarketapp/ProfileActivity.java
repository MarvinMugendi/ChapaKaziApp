package com.marvedie.servicesmarketapp;
/*
By
Marvin Eddie Mugendi
Email: marvedie254@gmail.com
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.marvedie.servicesmarketapp.ui.activities.SplashActivity;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth firebaseAuth;
    //views
    private TextView textViewUserEmail;
    private Button buttonLogout,Mpesa;
    private Button buttonHire;
    private Button buttonWork;
    private Button btnChat;
    private Button buttonUpdate;
    private ImageView updateimage,hireimage,workimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        textViewUserEmail = findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome");

        buttonLogout = findViewById(R.id.buttonLogout);
        buttonHire = findViewById(R.id.btnHire);
        buttonWork = findViewById(R.id.btnWork);
        buttonUpdate = findViewById(R.id.UpdateInformation);
        hireimage= findViewById(R.id.hire_image);
        workimage = findViewById(R.id.image_work);
        updateimage = findViewById(R.id.image_update);
        btnChat = findViewById(R.id.ChatBtn);
        Mpesa = findViewById(R.id.btn_Mpesa);


        buttonLogout.setOnClickListener(this);
        buttonWork.setOnClickListener(this);
        buttonHire.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
        hireimage.setOnClickListener(this);
        workimage.setOnClickListener(this);
        updateimage.setOnClickListener(this);
        btnChat.setOnClickListener(this);
        Mpesa.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //using switch
        switch (view.getId()) {
            case R.id.buttonLogout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case  R.id.btnHire:
                finish();
                startActivity(new Intent(this, HireActivity.class));
                break;
            case R.id.btnWork:
                finish();
                startActivity(new Intent(this, DatabaseSave.class));
                break;
            case R.id.UpdateInformation:
                finish();
                startActivity(new Intent(this,UpdateInformation.class));
                break;
            case R.id.image_update:
                finish();
                startActivity(new Intent(this,UpdateInformation.class));
                break;
            case R.id.hire_image:
                finish();
                startActivity(new Intent(this, HireActivity.class));
                break;
            case R.id.image_work:
                finish();
                startActivity(new Intent(this, DatabaseSave.class));
                break;
            case R.id.ChatBtn:
                finish();
                startActivity(new Intent(this, SplashActivity.class));
                break;
            case R.id.btn_Mpesa:

                startActivity(new Intent(this, MpesaActivity.class));
                break;
        }
    }
}
