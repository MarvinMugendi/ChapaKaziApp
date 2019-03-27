package com.marvedie.servicesmarketapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.marvedie.servicesmarketapp.Common.Common;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    MaterialEditText edtPhone, editTextPassword;
    TextView textViewSignup;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");



        edtPhone = findViewById(R.id.edtPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        textViewSignup = findViewById(R.id.textViewSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        progressDialog = new ProgressDialog(this);

        textViewSignup.setOnClickListener(this);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = edtPhone.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (phone.isEmpty()){
                    //email is empty
                    edtPhone.setError("Phone is Required");
                    edtPhone.requestFocus();
                    //stopping the function execution further
                    return;
                }
                if (phone.length() < 10) {

                    edtPhone.setError("Minimum phone length is 10");
                    edtPhone.requestFocus();
                    return;
                }
                if (password.isEmpty()){
                    editTextPassword.setError("Password is Required");
                    editTextPassword.requestFocus();
                    return;
                }
                if (password.length() < 6) {

                    editTextPassword.setError("Minimum password length is 6");
                    editTextPassword.requestFocus();
                    return;
                }
                //After Validation is complete
                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Please Wait ....Ensure Internet Is On");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NotNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            mDialog.dismiss();
                            //Get User Information
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            user.setPhone(edtPhone.getText().toString());
                            if (user.getPassword().equals(editTextPassword.getText().toString())) {
                                Intent homeIntent = new Intent(LoginActivity.this, Choose.class);
                                Common.currentUser = user;
                                startActivity(homeIntent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Wrong Password!!!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "User Does Not Exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
        @Override
        public void onClick (View view){
            switch (view.getId()) {
                case R.id.textViewSignUp:
                    finish();
                    startActivity(new Intent(this, RegisterActivity.class));
            }
        }
}
