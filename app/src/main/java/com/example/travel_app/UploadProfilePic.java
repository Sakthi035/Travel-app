package com.example.travel_app;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Objects;


public class UploadProfilePic extends AppCompatActivity {

    private ImageView ProfileUploadImg;
    private MaterialCardView ChoosePic;
    private View UploadPic;
    private FirebaseAuth authProfile;
    private StorageReference storageReference;
    private FirebaseUser firebaseUser;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri uriImage;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile_pic);
        authProfile = FirebaseAuth.getInstance();
        firebaseUser = authProfile.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference("DisplayProfilePics");

        ChoosePic = findViewById(R.id.choose_profile_pic_btn);
        UploadPic = findViewById(R.id.upload_profile_pic_btn);
        ProfileUploadImg = findViewById(R.id.imageview_profile_pic);

        ButtonActivities UploadButton = new ButtonActivities(UploadProfilePic.this, UploadPic);

        Uri uri = firebaseUser.getPhotoUrl();

        //set user's image for the web(which is not in the app,out side the app) so we use picasso for load the image from outside

        Picasso.get().load(uri).into(ProfileUploadImg);

        //Choosing Image to Upload and display on the Image View
        ChoosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        //Upload Image to the Datebase
        UploadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadButton.buttonActivated();
                UploadPicture();
            }
        });
    }

    private void UploadPicture() {
        ButtonActivities UploadButton = new ButtonActivities(UploadProfilePic.this, UploadPic);
        if (uriImage != null) {
            //save the Image with the Current User Logged in
            StorageReference fileReference = storageReference.child(authProfile.getCurrentUser().getUid() + "."
                    + getFileExtension(uriImage));

            //upload image to storage
            fileReference.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            UploadButton.buttonFinishedCorrect();
                            Uri downloadUri = uri;
                            firebaseUser = authProfile.getCurrentUser();

                            UserProfileChangeRequest profileupdates = new UserProfileChangeRequest.Builder()
                                    .setPhotoUri(downloadUri).build();
                            firebaseUser.updateProfile(profileupdates);
                        }
                    });
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UploadProfilePic.this, "Picture Uploaded Successfully", Toast.LENGTH_SHORT).show();
                            UploadButton.buttonNormal();
                        }
                    },2000);

                }
            });

        }
        else {
            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    UploadButton.buttonFinishedWrong();

                    Handler handler3 = new Handler();
                    handler3.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            UploadButton.buttonNormal();
                            Toast.makeText(UploadProfilePic.this, "SOMETHING WENT WRONG.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    },1000);

                }
            },3000);
        }
    }

    private String getFileExtension(Uri uri) {

        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void openFileChooser() {

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                uriImage = data.getData();
                ProfileUploadImg.setImageURI(uriImage);
            }
       }
}