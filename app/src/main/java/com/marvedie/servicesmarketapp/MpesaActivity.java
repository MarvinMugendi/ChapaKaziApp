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

import com.marvedie.servicesmarketapp.daraja.Daraja;
import com.marvedie.servicesmarketapp.daraja.DarajaListener;
import com.marvedie.servicesmarketapp.daraja.model.AccessToken;
import com.marvedie.servicesmarketapp.daraja.model.LNMExpress;
import com.marvedie.servicesmarketapp.daraja.model.LNMResult;
import com.marvedie.servicesmarketapp.daraja.util.TransactionType;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import timber.log.Timber;

public class MpesaActivity extends AppCompatActivity {


    //Declare Daraja :: Global Variable
    Daraja daraja;
    String phonenumber;
    // String amount;

    @BindView(R.id.editTextPhoneNumber)
    EditText editTextphone;
    @BindView(R.id.sendButton)
    Button buttonSend;
    @BindView(R.id.editTextAmount)
    EditText editTextAmount;
    @BindView(R.id.btnBack)
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mpesa);
        ButterKnife.bind(this);

        //editTextphone = findViewById(R.id.editTextPhoneNumber);
        //editTextAmount = findViewById(R.id.editTextAmount);
        //buttonSend = findViewById(R.id.sendButton);
        // buttonBack = findViewById(R.id.btnBack);


        daraja = Daraja.with("qXvE0PUsMWbArEqW2vzFO32kPwzE1cIv", "iCUgVUTUo0NCsWjb", new DarajaListener<AccessToken>() {
            @Override
            public void onResult(@NonNull AccessToken accessToken) {
                Timber.i(accessToken.getAccess_token());
                Toast.makeText(MpesaActivity.this, "TOKEN : " + accessToken.getAccess_token(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                Timber.e(error);
            }
        });

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

                //Get Phone Number from User Input
                phonenumber = editTextphone.getText().toString().trim();
                //Get amount
                // amount = editTextAmount.getText().toString().trim();
                if (TextUtils.isEmpty(phonenumber)) {
                    editTextphone.setError("Please Provide a Phone Number");
                    return;
                }


                LNMExpress lnmExpress = new LNMExpress(
                        "174379",
                        "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919",
                        TransactionType.CustomerPayBillOnline,
                        "100",
                        "254701919020",
                        "174379",
                        phonenumber,
                        "https://endkk25p2k0gn.x.pipedream.net",
                        "FreelancerPayment",
                        "TRANSACTION"
                );

                daraja.requestMPESAExpress(lnmExpress,
                        new DarajaListener<LNMResult>() {
                            @Override
                            public void onResult(@NonNull LNMResult lnmResult) {

                                Timber.i(lnmResult.ResponseDescription);
                                //Toast.makeText(getApplicationContext(), "Confirmed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(String error) {

                                Timber.i(error);
                                Toast.makeText(MpesaActivity.this, "Error. Please Check Safaricom Network Signal and Ensure Internet Connectivity", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}








