package com.marvedie.servicesmarketapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choose extends AppCompatActivity implements View.OnClickListener {

    Button btnhires;
    Button btngetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        btngetwork = findViewById(R.id.btnWork);
        btnhires = findViewById(R.id.btnHire);


        btnhires.setOnClickListener(this);
        btngetwork.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnHire:
                finish();
                startActivity(new Intent(this, ProfileHire.class));
                break;
            case R.id.btnWork:
                finish();
                startActivity(new Intent(this, ProfileWork.class));
                break;
        }
    }
}
