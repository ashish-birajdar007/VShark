package com.syndicate.vshark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddUserProfileActivity extends AppCompatActivity {

    private static final String TAG = "HOLA";
    private FirebaseFirestore database;
    private FirebaseAuth fAuth;
    private Button submit;
    private EditText companyName, productDetails, ask, equity, companyValuation, lastYearSales, priorInvestorName, priorInvestorInvestment, priorInvestorStake, netProfit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_profile);
        init();
        database = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        submit = findViewById(R.id.company_profile_submit);

        UserProfile user = new UserProfile();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user.setEmail(fAuth.getCurrentUser().getEmail());
                user.setCompanyName(companyName.getText().toString());
                user.setProductDetails(productDetails.getText().toString());
                user.setAsk(ask.getText().toString());
                user.setEquity(equity.getText().toString());
                user.setCompanyValuation(companyValuation.getText().toString());
                user.setLastYearSales(lastYearSales.getText().toString());
                user.setPriorInvestorName(priorInvestorName.getText().toString());
                user.setPriorInvestorInvestment(priorInvestorInvestment.getText().toString());
                user.setPriorInvestorStake(priorInvestorStake.getText().toString());
                user.setNetProfit(netProfit.getText().toString());

                //New Collection Code
                Map<String, Object> mUser = new HashMap<>();
                mUser.put("Email",fAuth.getCurrentUser().getEmail());
                mUser.put("Company Name",companyName.getText().toString());
                mUser.put("Product Details",productDetails.getText().toString());
                mUser.put("Ask",ask.getText().toString());
                mUser.put("Equity",equity.getText().toString());
                mUser.put("Company Valuation",companyValuation.getText().toString());
                mUser.put("Last Year Sales",lastYearSales.getText().toString());
                mUser.put("Prior Investor Name",priorInvestorName.getText().toString());
                mUser.put("Prior Investor Investment",priorInvestorInvestment.getText().toString());
                mUser.put("Net Profit",netProfit.getText().toString());

                database.collection("OUserProfiles").document(fAuth.getCurrentUser().getEmail()).set(mUser);
                //New Collection Code Ended

                //Rushikesh's Code
                database.collection("UserProfiles")
                        .document(fAuth.getCurrentUser().getEmail()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(AddUserProfileActivity.this, "User Profile Updated...", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddUserProfileActivity.this, MainActivity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddUserProfileActivity.this, "Error Updating", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddUserProfileActivity.this, MainActivity.class));
                            }
                        });
            }
        });

    }

    private void init(){
        companyName = findViewById(R.id.company_name);
        productDetails = findViewById(R.id.product_details);
        ask = findViewById(R.id.ask);
        equity = findViewById(R.id.equity);
        companyValuation = findViewById(R.id.company_valuation);
        lastYearSales = findViewById(R.id.last_year_sales);
        priorInvestorName = findViewById(R.id.prior_investor_name);
        priorInvestorInvestment = findViewById(R.id.prior_investor_investment);
        priorInvestorStake = findViewById(R.id.prior_investor_stake);
        netProfit = findViewById(R.id.net_profit);

    }
}