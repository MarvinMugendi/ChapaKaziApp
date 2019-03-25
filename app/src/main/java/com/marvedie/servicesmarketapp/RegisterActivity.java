package com.marvedie.servicesmarketapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    // Defining objects
    Button btnRegister;
    TextView tvSignIn;
    MaterialEditText edtName,edtId,edtPhone;
    MaterialEditText editTextEmail, editTextPassword;

    private ProgressDialog progressDialog;
    //firebase authentication object
    private FirebaseAuth firebaseAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //initializing firebase authentication object
       // firebaseAuth = FirebaseAuth.getInstance();
        //Initialise Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");


        progressDialog = new ProgressDialog(this);

        btnRegister= findViewById(R.id.btnRegister);
        editTextEmail =  findViewById(R.id.editTextEmail);
        editTextPassword =  findViewById(R.id.editTextPassword);
        tvSignIn = findViewById(R.id.textViewSignIn);
        edtId = findViewById(R.id.edtId);
        edtPhone = findViewById(R.id.edtPhone);
        edtName = findViewById(R.id.edtName);


        btnRegister.setOnClickListener(this);
        tvSignIn.setOnClickListener(this);



    btnRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            final ProgressDialog mDialog = new ProgressDialog(RegisterActivity.this);
            mDialog.setMessage("Please Wait ....");
            mDialog.show();
            table_user.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if(dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                        mDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "User Already Registered", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        User user = new User(edtName.getText().toString(),editTextPassword.getText().toString(),editTextEmail.getText().toString(),
                                edtId.getText().toString());
                        table_user.child(edtPhone.getText().toString()).setValue(user);
                        Toast.makeText(RegisterActivity.this, "Sign Up Sucessful!", Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(RegisterActivity.this,Choose.class);
                        startActivity(homeIntent);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    });
}

    // register user method
    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            //email is empty
            editTextEmail.setError("Email is Required");
            editTextEmail.requestFocus();
            //stopping the function execution further
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("Password is Required");
            editTextPassword.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please Enter a Valid Email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.length() < 6) {

            editTextPassword.setError("Minimum password length is 6");
            editTextPassword.requestFocus();
            return;
        }
        //if validation is okay we will show progress bar here because it is an internet activity
        //taking time
        progressDialog.setMessage("Registering User");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            /*user is successfully registered and logged in
                            we will start the profile activity here
                            right now lets display a toast */
                            Toast.makeText(getApplicationContext(),"User Register Successful",Toast.LENGTH_SHORT).show();
                            //open new activity.
                            finish();
                            startActivity(new Intent(getApplicationContext(), Choose.class));

                        } else {
                            //to check if email is already register
                            if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(), "You are already Registered", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Error Occured, Check Credentials and Internet connectivity then Try Again",Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });

  }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.textViewSignIn:
                startActivity(new Intent(this, LoginActivity.class));
                break;
                /* Direct Firebase register method without user table that is not being used currently
                case R.id.btnRegister:
                registerUser();
                break;
                */
        }
    }
}