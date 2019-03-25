package com.marvedie.servicesmarketapp;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;

public class UpdateInformation extends AppCompatActivity implements View.OnClickListener{


    private static final int CHOOSE_IMAGE =100 ;
    //Define Views
    ImageView image;
    EditText editText;

    Uri FilePathUri;
    // Image request code for onActivityResult() .
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;

    Button btnBack,btnUpdate,btnChoose;
    ProgressBar progressBar;
    MaterialEditText edtPhone,edtPassword,edtTown;

    String profileImageUrl;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    // Folder path for Firebase Storage.
    String Storage_Path = "Good_Conduct_Images/";
    String Database_Path = "Register_Freelancer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_information);

        //Instantiate views
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        edtTown = findViewById(R.id.edtTown);
        image = findViewById(R.id.imageView);
        btnBack = findViewById(R.id.btnBack);
        progressBar = findViewById(R.id.progressbar);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnChoose = findViewById(R.id.btnChoose);


        btnBack.setOnClickListener(this);
        progressDialog = new ProgressDialog(UpdateInformation.this);
        // Assign FirebaseStorage instance to storageReference.
        storageReference = FirebaseStorage.getInstance().getReference();

        // Assign FirebaseDatabase instance with root database name.
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);


        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creating intent.
                Intent intent = new Intent();

                // Setting intent type as image to select image from phone storage.
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);

            }
        });
        //listener for upload
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInformation();

            }
        });

    }

    private String GetFileExtension(Uri filePathUri) {
        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(filePathUri)) ;

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {

                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);

                // Setting up bitmap selected image into ImageView.
                image.setImageBitmap(bitmap);

                // After selecting image change choose button above text.
                btnChoose.setText("Image Selected");
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    private void saveUserInformation() {

        // Checking whether FilePathUri Is empty or not.
        if (FilePathUri != null) {

            // Setting progressDialog Title.
            progressDialog.setTitle("Image is Uploading...");

            // Showing progressDialog.
            progressDialog.show();

            // Creating second StorageReference.
            StorageReference storageReference2nd = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // Getting image name from EditText and store into string variable.
                            String phone = edtPhone.getText().toString();
                            String password = edtPassword.getText().toString();
                            String town = edtTown.getText().toString();


                            // Hiding the progressDialog after done uploading.
                            progressDialog.dismiss();
                            // Showing toast message after done uploading.
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();

                            @SuppressWarnings("VisibleForTests")
                            GoodConductUploadClass goodConductUploadClass = new GoodConductUploadClass(phone,password,town,taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());

                            // Getting image upload ID.
                            String ImageUploadId = databaseReference.push().getKey();
                            // Adding image upload id s child element into databaseReference.
                            databaseReference.child(ImageUploadId).setValue(goodConductUploadClass);
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.
                            progressDialog.dismiss();

                            // Showing exception erro message.
                            Toast.makeText(UpdateInformation.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    // On progress change upload time.

                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            // Setting progressDialog Title.
                            progressDialog.setTitle("Image is Uploading...");

                        }
                    });
        }
        else {

            Toast.makeText(UpdateInformation.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                finish();
                startActivity(new Intent(this, ProfileWork.class));

        }

    }
}