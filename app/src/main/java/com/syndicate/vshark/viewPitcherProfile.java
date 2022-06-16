package com.syndicate.vshark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class viewPitcherProfile extends AppCompatActivity {

    private final String TAG = "VIEW PITCHER PROFILE";

    private TextInputEditText TIETpitcherEmail;
    private Button BTNview;
    private FirebaseFirestore PPdatabase;
    private FirebaseAuth PPfAuth;

    private TextView companyName, productDetails, ask, equity, companyValuation, lastYearSales, priorInvestorName, priorInvestorInvestment, priorInvestorStake, netProfit;
    private void init(){
        companyName = findViewById(R.id.TVcompanyName);
        productDetails = findViewById(R.id.TVproductDetails);
        ask = findViewById(R.id.TVASK);
        equity = findViewById(R.id.TVEquity);
        companyValuation = findViewById(R.id.TVvaluation);
        lastYearSales = findViewById(R.id.TVlastYearSales);
        priorInvestorName = findViewById(R.id.TVpriorInvestorName);
        priorInvestorInvestment = findViewById(R.id.TVpriorInvestorInvestment);
        priorInvestorStake = findViewById(R.id.TVpriorInvestorStake);
        netProfit = findViewById(R.id.TVnetProfit);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pitcher_profile);
        init();
        TIETpitcherEmail = findViewById(R.id.idEditPitcherEmail);
        BTNview = findViewById(R.id.idBtnViewProfile);
        PPdatabase = FirebaseFirestore.getInstance();
        PPfAuth = FirebaseAuth.getInstance();

        BTNview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                PPdatabase.collection("OUserProfiles").
//                        whereEqualTo("Email",TIETpitcherEmail.getText().toString()).
//                        get().addOnSuccessListener(
//                                new OnSuccessListener<QuerySnapshot>() {
//                                    @Override
//                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                                        Toast.makeText(viewPitcherProfile.this, "OUserProfile Retreival Succesful", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                        ).addOnFailureListener(
//                                new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(viewPitcherProfile.this, "Error Occured", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                        );

                DocumentReference documentReference =  PPdatabase.collection("UserProfiles").document(TIETpitcherEmail.getText().toString());
                documentReference.get().addOnSuccessListener(
                        new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                UserProfile userProfile = documentSnapshot.toObject(UserProfile.class);
                                Toast.makeText(viewPitcherProfile.this, "Pitcher Profile Found", Toast.LENGTH_SHORT).show();
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
                        }
                ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(viewPitcherProfile.this, "Pitcher Profile Not Found", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}