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

    private TextView companyName, productDetails, ask, equity, companyValuation, lastYearSales, priorInvestorName, priorInvestorInvestment, priorInvestorStake, netProfit;
    private void init(){
        companyName = findViewById(R.id.upTVcompanyName);
        productDetails = findViewById(R.id.upTVproductDetails);
        ask = findViewById(R.id.upTVASK);
        equity = findViewById(R.id.upTVEquity);
        companyValuation = findViewById(R.id.upTVvaluation);
        lastYearSales = findViewById(R.id.upTVlastYearSales);
        priorInvestorName = findViewById(R.id.upTVpriorInvestorName);
        priorInvestorInvestment = findViewById(R.id.upTVpriorInvestorInvestment);
        priorInvestorStake = findViewById(R.id.upTVpriorInvestorStake);
        netProfit = findViewById(R.id.upTVnetProfit);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        init();

        database = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();


        DocumentReference docRef = database.collection("UserProfiles").document(fAuth.getCurrentUser().getEmail());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                UserProfile userProfile = documentSnapshot.toObject(UserProfile.class);
                companyName.setText(userProfile.getCompanyName());
                productDetails.setText(userProfile.getProductDetails());
                ask.setText(userProfile.getAsk());
                equity.setText(userProfile.getEquity());
                companyValuation.setText(userProfile.getCompanyValuation());
                lastYearSales.setText(userProfile.getLastYearSales());
                priorInvestorName.setText(userProfile.getPriorInvestorName());
                priorInvestorInvestment.setText(userProfile.getPriorInvestorInvestment());
                priorInvestorStake.setText(userProfile.getPriorInvestorStake());
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