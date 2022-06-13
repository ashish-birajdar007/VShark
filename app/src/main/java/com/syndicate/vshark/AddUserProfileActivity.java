package com.syndicate.vshark;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

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

                database.collection("UserProfiles")
                        .document(fAuth.getCurrentUser().getEmail()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(AddUserProfileActivity.this, "User Registered...", Toast.LENGTH_SHORT).show();
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