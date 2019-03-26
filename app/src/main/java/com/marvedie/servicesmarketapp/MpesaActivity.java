package com.marvedie.servicesmarketapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.twigafoods.daraja.Daraja;
import com.twigafoods.daraja.DarajaListener;
import com.twigafoods.daraja.model.AccessToken;
import com.twigafoods.daraja.model.LNMExpress;
import com.twigafoods.daraja.model.LNMResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MpesaActivity extends AppCompatActivity {


    //Declare Daraja :: Global Variable
    Daraja daraja;
    String phonenumber;
    String amount;
    Button buttonSend,buttonBack;
    EditText editTextphone,editTextAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpesa);
        {
            editTextphone = findViewById(R.id.editTextPhoneNumber);
            editTextAmount = findViewById(R.id.editTextAmount);
            buttonSend = findViewById(R.id.sendButton);
            buttonBack = findViewById(R.id.btnBack);

            buttonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), ProfileHire.class));

                }
            });

            buttonSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                    makePayment();
                }
            });
        }}


        public void makePayment(){

        Daraja daraja = Daraja.with("qXvE0PUsMWbArEqW2vzFO32kPwzE1cIv", "iCUgVUTUo0NCsWjb", new DarajaListener<AccessToken>() {
                @Override
                public void onResult(@NonNull AccessToken accessToken) {
                    Log.i(MpesaActivity.this.getClass().getSimpleName(), accessToken.getAccess_token());
                    Toast.makeText(MpesaActivity.this, "TOKEN : " + accessToken.getAccess_token(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(String error) {
                    Log.e(MpesaActivity.this.getClass().getSimpleName(), error);
                }
            });

        //Get Phone Number from User Input
            phonenumber = editTextphone.getText().toString().trim();
            //Get amount
            amount = editTextAmount.getText().toString().trim();

            LNMExpress lnmExpress = new LNMExpress(
                    "174379",
                    "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919",
                    amount,
                    "254701919020",
                    "174379",
                    phonenumber,
                    "http://requestbin.fullcontact.com/1e1zm4m1",
                    "FreelancerPayment",
                    "TRANSACTION"
            );

            daraja.requestMPESAExpress(lnmExpress,
                    new DarajaListener<LNMResult>()
                    {
                        @Override
                        public void onResult(@NonNull LNMResult lnmResult) {
                            Toast.makeText(getApplicationContext(), "Confirmed", Toast.LENGTH_SHORT).show();
                           Log.i(MpesaActivity.this.getClass().getSimpleName(), lnmResult.ResponseDescription);
                        }

                        @Override
                        public void onError(String error) {
                            Toast.makeText(MpesaActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                            Log.i(MpesaActivity.this.getClass().getSimpleName(), error);
                        }
                    });
    }
}






