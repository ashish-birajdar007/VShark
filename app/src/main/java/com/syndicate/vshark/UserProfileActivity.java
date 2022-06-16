package com.syndicate.vshark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserProfileActivity extends AppCompatActivity {

    private TextView userProfile;
    private FirebaseFirestore database;
    private FirebaseAuth fAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        userProfile = findViewById(R.id.user_profile);
        progressBar = findViewById(R.id.progress_bar_user_profile);

        database = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();


        DocumentReference docRef = database.collection("UserProfiles").document(fAuth.getCurrentUser().getEmail());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                UserProfile user = documentSnapshot.toObject(UserProfile.class);
                progressBar.setVisibility(View.GONE);
                userProfile.setText(user.toString());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UserProfileActivity.this, "User Profile Either Not Entered\n Or Failed To Retrieve", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UserProfileActivity.this, AddUserProfileActivity.class);
                startActivity(i);
            }
        });
    }
}